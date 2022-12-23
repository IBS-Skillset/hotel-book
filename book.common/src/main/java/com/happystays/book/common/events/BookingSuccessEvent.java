package com.happystays.book.common.events;


import com.happystays.book.common.dto.successeventmodel.Trip;
import com.happystays.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookingSuccessEvent extends BaseEvent {

    private Trip trip;

}
