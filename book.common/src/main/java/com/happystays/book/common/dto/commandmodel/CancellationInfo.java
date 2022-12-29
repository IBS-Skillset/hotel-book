package com.happystays.book.common.dto.commandmodel;

import lombok.Data;

@Data
public class CancellationInfo {
    private boolean cancellable;
    private String cancellationDate;
    private String cancellationPolicy;
}
