package com.happystays.book.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelCancellationInfo {

    private String cancellationDeadline;
    private Boolean isCancellable;
    private String cancellationPolicy;
    private String cancellationDate;

}
