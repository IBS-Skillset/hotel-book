package com.happystays.book.common.dto.commonmodel;

import lombok.Data;

@Data
public class ResponseStatus {
    private int status;
    private String errorCode;
    private String errorMessage;
}
