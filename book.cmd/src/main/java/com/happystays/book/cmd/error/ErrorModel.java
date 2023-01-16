package com.happystays.book.cmd.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModel {
    private String errorCode;
    private String errorMessage;
}
