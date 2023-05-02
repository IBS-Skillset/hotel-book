package com.happystays.book.cmd.api.controllers;

import com.happystays.book.cmd.error.BookErrorBuilder;
import com.happystays.book.common.dto.responsemodel.BookResponse;
import com.happystays.cqrs.core.infrastucture.CommandDispatcher;
import com.happystays.book.cmd.api.commands.BookCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/book")
public class BookController {

    private final CommandDispatcher commandDispatcher;
    private final BookErrorBuilder errorBuilder;

    @PostMapping
    public BookResponse hotelBook(@RequestBody BookCommand command) {
        BookResponse bookResponse = null;
        var id = UUID.randomUUID().toString();
        command.setId(id);
        log.error("Book Command", command);
        try {
             bookResponse = (BookResponse) commandDispatcher.send(command);
        } catch (Exception e) {
            log.error("Bad request", e.getMessage());
            bookResponse = errorBuilder.errorBuilder(e.getMessage());

        }
        return bookResponse;
    }
}

