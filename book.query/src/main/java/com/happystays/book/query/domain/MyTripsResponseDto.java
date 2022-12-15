package com.happystays.book.query.domain;

import com.happystays.cqrs.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyTripsResponseDto extends BaseEntity {
    private String bookingId;
    private String bookingDate;
    private String hotelName;
    private String checkInDate;
    private String checkOutDate;
    private int totalPrice;
    private String currencyCode;
    private String freeCancellationUntil;
}
