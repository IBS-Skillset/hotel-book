package com.happystays.book.cmd.api.commands;

import com.happystays.cqrs.core.dto.response.BookResponse;

public interface CommandHandler {
    BookResponse handle(BookCommand command);

}
