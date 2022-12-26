package com.happystays.book.cmd.handler;

import com.happystays.book.cmd.error.ErrorResponseEntityBuilder;
import com.happystays.book.common.dto.errormodel.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception){
        log.error("Exception occurred from book-cmd service" ,exception);
        return ErrorResponseEntityBuilder.buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
