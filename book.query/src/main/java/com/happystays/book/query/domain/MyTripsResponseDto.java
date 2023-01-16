package com.happystays.book.query.domain;

import com.happystays.cqrs.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyTripsResponseDto extends BaseEntity {
    private String bookingId;
    private Date bookingDate;
    private String hotelName;
    private Date checkInDate;
    private Date checkOutDate;
    private Double totalPrice;
    private String currencyCode;
    private Date freeCancellationUntil;
}