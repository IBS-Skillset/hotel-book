package com.happystays.book.cmd.api.controllers;

import com.happystays.cqrs.core.dto.response.BaseResponse;
import com.happystays.cqrs.core.dto.response.BookResponse;
import com.happystays.cqrs.core.infrastucture.CommandDispatcher;
import com.happystays.book.cmd.api.commands.BookCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/api/book")
public class BookController {
    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> hotelBook(@RequestBody BookCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            BookResponse bookResponse = commandDispatcher.send(command);
            bookResponse.setId(id);
            return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);

        } catch (IllegalStateException  e) {
            log.warn("Bad request", e.getMessage());
            return new ResponseEntity<>(new BaseResponse(e.getMessage()), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.warn("Bad request", e.getMessage());
            return new ResponseEntity<>(new BaseResponse(e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }
}

