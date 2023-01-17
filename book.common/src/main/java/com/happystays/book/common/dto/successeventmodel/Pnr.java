package com.happystays.book.common.dto.successeventmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pnr {
    private Long supplierId;
    private Date creationDate;
    private String status;
    private List<HotelInfo> hotelInfoList;
    private List<Long> userIdList;
}
