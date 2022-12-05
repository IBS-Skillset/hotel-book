package com.happystays.book.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    private double totalPrice;

    private String currencyCode;

    private String beginDate;

    private String endDate;

}
