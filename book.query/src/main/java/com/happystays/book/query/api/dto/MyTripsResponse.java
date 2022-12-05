package com.happystays.book.query.api.dto;

import com.happystays.cqrs.core.dto.BaseResponse;
import com.happystays.book.query.domain.MyTripsResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MyTripsResponse  extends BaseResponse {

    private List<MyTripsResponseDto> myTrips;
    public MyTripsResponse(String message) {
        super(message);
    }
}
