package com.happystays.book.common.dto.responsemodel;

import lombok.Data;

@Data
public class RoomRate {
    private String currencyCode;
    private double totalAmount;
    private String rateDescription;
}
