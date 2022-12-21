package com.happystays.book.common.events;

import com.happystays.book.common.dto.successeventmodel.Pnr;
import com.happystays.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookingSuccessEvent extends BaseEvent {
    private List<Pnr> pnrList;
}
