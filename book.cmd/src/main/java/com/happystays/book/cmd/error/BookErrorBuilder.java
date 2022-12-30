package com.happystays.book.cmd.error;

import com.happystays.book.common.dto.commonmodel.ResponseStatus;
import com.happystays.book.common.dto.responsemodel.BookResponse;
import org.springframework.stereotype.Component;

import static com.happystays.book.common.utils.Constants.FAILURE;
import static com.happystays.book.common.utils.Constants.FAILURE_ERROR;

@Component
public class BookErrorBuilder {
    public BookResponse errorBuilder(String errorMessage) {
        BookResponse bookResponse = new BookResponse();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatus(FAILURE);
        responseStatus.setErrorMessage(errorMessage);
        responseStatus.setErrorCode(FAILURE_ERROR);
        bookResponse.setResponseStatus(responseStatus);
        return bookResponse;
    }
}
