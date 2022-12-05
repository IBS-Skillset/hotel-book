package com.happystays.book.common.dto;

import com.happystays.cqrs.core.dto.HotelAddress;
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
    private HotelAddress hotelAddress;
    private String hotelPhone;
    private String countryCode;
    private String locationCode;
    private boolean guaranteedIndicator;
    private List<HotelSegment> hotelSegmentList;
    private PaymentMethod paymentMethod;
}
