package com.happystays.book.cmd.api.dto;

import com.happystays.book.common.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse extends BaseResponse {
    private String id;

    public BookResponse(String message, String id) {
        super((message));
        this.id = id;
    }
}
