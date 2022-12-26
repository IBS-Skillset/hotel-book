package com.happystays.book.common.dto.successeventmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelInfo {
    private double totalPrice;
    private String currencyCode;
    private String propertyCode;
    private String propertyName;
    private String hotelAddress;
    private String hotelPhone;
    private String countryCode;
    private String locationCode;
    private boolean guaranteedIndicator;
    private List<HotelSegment> hotelSegmentList;
    private PaymentMethod paymentMethod;
}
