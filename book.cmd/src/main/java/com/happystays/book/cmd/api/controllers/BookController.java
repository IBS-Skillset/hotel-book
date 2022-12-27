package com.happystays.book.cmd.api.controllers;

import com.happystays.book.common.dto.errormodel.ErrorResponse;
import com.happystays.book.common.dto.responsemodel.BookResponse;
import com.happystays.cqrs.core.infrastucture.CommandDispatcher;
import com.happystays.book.cmd.api.commands.BookCommand;
import com.happystays.cqrs.core.response.BaseResponse;
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
    public BookResponse hotelBook(@RequestBody BookCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            BookResponse bookResponse = (BookResponse) commandDispatcher.send(command);
            return bookResponse;

        } catch (Exception e) {
            log.warn("Bad request", e.getMessage());
            //ToDo: Error mappings will handled in upcoming jira
//            return new ResponseEntity<>(new ErrorResponse(-1 , 999, e.getMessage()), HttpStatus.BAD_REQUEST);

        }
        return null;
    }
}

