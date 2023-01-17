package com.happystays.book.cmd.api.commands;

import com.happystays.cqrs.core.response.BaseResponse;

public interface CommandHandler {
    BaseResponse handle(BookCommand command);

}
