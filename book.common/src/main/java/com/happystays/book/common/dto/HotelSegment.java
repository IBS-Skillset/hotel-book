package com.happystays.book.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelSegment {

    private double hotelPrice;
    private String currencyCode;
    private String confirmationNumber;
    private int occupancy;
    private String checkInDate;
    private String checkOutDate;
    private HotelRoomInfo hotelRoomInfo;
    private HotelCancellationInfo cancellationInfo;

}
