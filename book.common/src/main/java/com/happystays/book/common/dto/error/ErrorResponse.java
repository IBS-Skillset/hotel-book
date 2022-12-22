package com.happystays.book.common.dto.error;

import com.happystays.cqrs.core.dto.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse extends BaseResponse {
    private int status;
    private int errorCode;
    private String errorText;
}

