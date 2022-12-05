package com.happystays.book.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRoomInfo {
    private Double nightlyPrice;
    private String rateDescription;
    private boolean breakfastIncluded;
}
