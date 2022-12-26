package com.happystays.book.cmd.error;

import com.happystays.book.common.dto.errormodel.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponseEntityBuilder {
    public static ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus status, String errorMessage) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(-1);
        errorResponse.setErrorCode(status.value());
        errorResponse.setErrorText(errorMessage);
        return new ResponseEntity<>(errorResponse, status);
    }
}
