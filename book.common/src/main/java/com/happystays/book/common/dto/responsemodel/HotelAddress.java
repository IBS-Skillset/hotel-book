package com.happystays.book.common.dto.responsemodel;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = false)
public class HotelAddress {
    private String streetAddress;
    private String cityName;
    private String zipCode;
    private String countryName;
}
