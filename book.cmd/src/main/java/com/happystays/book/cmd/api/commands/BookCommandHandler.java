package com.happystays.book.cmd.api.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BookCommandHandler implements CommandHandler {

    private final String uri = "http://localhost:8085/hotel-search-service/api";

    //ToDo: Aggregate and eventSourceHandler

    @Override
    public void handle(BookCommand command) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(uri,command,String.class);
        log.info(response.getBody());

    }
}
