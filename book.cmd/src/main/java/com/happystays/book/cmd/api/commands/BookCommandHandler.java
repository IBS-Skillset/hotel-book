package com.happystays.book.cmd.api.commands;

import com.happystays.book.cmd.domain.BookAggregate;
import com.happystays.book.common.dto.responsemodel.BookResponse;
import com.happystays.cqrs.core.handlers.EventSourcingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Slf4j
public class BookCommandHandler implements CommandHandler {

    private final String uri = "http://localhost:8087/hotel-book-service/api/book";

    @Autowired
    private EventSourcingHandler<BookAggregate> eventSourcingHandler;

    @Override
    public BookResponse handle(BookCommand command) {
        //ToDo: Remove after Oauth implementation
        String token = "";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization" ,"Bearer "+token);
        HttpEntity<BookCommand> entity = new HttpEntity<>(command, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BookResponse> bookResponse = restTemplate.
                postForEntity(uri, entity, BookResponse.class);
        log.info(String.valueOf(bookResponse));
        if(Objects.nonNull(bookResponse) && Objects.nonNull(bookResponse.getBody()) && Objects.nonNull(bookResponse.getBody().getPnrInfo()) && Objects.nonNull(bookResponse.getBody().getPnrInfo().getBookingDescription()) && bookResponse.getBody().getPnrInfo().getBookingDescription().equalsIgnoreCase("Confirmed")){
            bookResponse.getBody().setMessage("Hotel booked successfully");
            BookAggregate bookAggregate = new BookAggregate(bookResponse.getBody() , command);
            eventSourcingHandler.saveEvents(bookAggregate);
        }
        return bookResponse.getBody();


    }
}