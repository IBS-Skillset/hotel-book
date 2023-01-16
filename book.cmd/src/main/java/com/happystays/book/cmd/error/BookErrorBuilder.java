package com.happystays.book.cmd.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.happystays.book.common.dto.commonmodel.ResponseStatus;
import com.happystays.book.common.dto.responsemodel.BookResponse;
import com.happystays.book.common.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Slf4j
@Component
public class BookErrorBuilder {


    private final ErrorMappings errorMappings;

    public BookErrorBuilder(ErrorMappings errorMappings){
        this.errorMappings = errorMappings;
    }

    public BookResponse errorBuilder(String errorMessage) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        BookResponse bookResponse = new BookResponse();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatus(Objects.requireNonNull(errorMappings).getStatus());
        responseStatus.setErrorMessage(errorMessage);
        responseStatus.setErrorCode(Objects.requireNonNull(errorMappings).getErrorMapping().get(Constants.SUPPLIER).getErrorCode());
        bookResponse.setResponseStatus(responseStatus);
        bookResponse.setMessage(Objects.requireNonNull(errorMappings).getMessage());
        return bookResponse;
    }
}
