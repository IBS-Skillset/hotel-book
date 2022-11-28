package com.happystays.book.cmd.api.commands;

import lombok.Data;

@Data
public class CancellationInfo {
    private boolean isCancellable;
    private String cancellationDate;
    private String cancellationPolicy;

}
