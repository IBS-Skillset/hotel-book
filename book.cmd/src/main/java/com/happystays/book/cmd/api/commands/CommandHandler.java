package com.happystays.book.cmd.api.commands;

import com.happystays.book.common.dto.responsemodel.BookResponse;

public interface CommandHandler {
    BookResponse handle(BookCommand command);

}
