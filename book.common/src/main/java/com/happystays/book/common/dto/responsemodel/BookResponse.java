package com.happystays.book.common.dto.responsemodel;

import com.happystays.book.common.dto.commandmodel.PnrInfo;
import com.happystays.book.common.dto.commonmodel.ResponseStatus;
import com.happystays.cqrs.core.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse extends BaseResponse {
    private ResponseStatus responseStatus;
    private PnrInfo pnrInfo;
    private String startDate;
    private String endDate;
    private String hotelCode;
    private String hotelName;
    private HotelAddress hotelAddress;
    private String ratePlanId;
    private RoomRate roomRate;
}
