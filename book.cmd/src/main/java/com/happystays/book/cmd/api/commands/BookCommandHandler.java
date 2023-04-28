package com.happystays.book.cmd.api.commands;

import com.happystays.book.cmd.config.RequestContext;
import com.happystays.book.cmd.domain.BookAggregate;
import com.happystays.book.common.dto.responsemodel.BookResponse;
import com.happystays.cqrs.core.handlers.EventSourcingHandler;
import com.happystays.cqrs.core.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

import static com.happystays.book.common.utils.Constants.BOOK_URL;
import static com.happystays.book.common.utils.Constants.CONFIRMED;

import org.springframework.beans.factory.annotation.Value;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookCommandHandler implements CommandHandler {


    @Value("${book.service.url}")
    private String bookService;

    private EventSourcingHandler<BookAggregate> eventSourcingHandler;

    @Override
    public BaseResponse handle(BookCommand command) {
        HttpEntity<BookCommand> entityReq = new HttpEntity<>(command, getHeader());
        RestTemplate template = new RestTemplate();
        ResponseEntity<BookResponse> bookResponse = template
                .exchange(bookService+BOOK_URL, HttpMethod.POST, entityReq, BookResponse.class);
        log.info(String.valueOf(command));
        log.info(String.valueOf(bookResponse));
        if(Objects.nonNull(bookResponse.getBody()) && Objects.nonNull(bookResponse.getBody().getPnrInfo())
                && Objects.nonNull(bookResponse.getBody().getPnrInfo().getBookingDescription())
                && bookResponse.getBody().getPnrInfo().getBookingDescription().equalsIgnoreCase(CONFIRMED)){
            bookResponse.getBody().setMessage("Hotel booked successfully");
            BookAggregate bookAggregate = new BookAggregate(bookResponse.getBody() , command);
            eventSourcingHandler.saveEvents(bookAggregate);
        }
        return bookResponse.getBody();
    }

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, RequestContext.getContext().getToken());
        return headers;
    }
}