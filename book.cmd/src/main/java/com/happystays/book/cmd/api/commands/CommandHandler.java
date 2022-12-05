package com.happystays.book.cmd.api.commands;

import com.happystays.cqrs.core.dto.BookResponse;

public interface CommandHandler {
    BookResponse handle(BookCommand command);

}
