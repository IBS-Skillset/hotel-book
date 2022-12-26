package com.happystays.book.common.events;

import com.happystays.cqrs.core.events.BaseEventStore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PnrEvent extends BaseEventStore {
    private String bookingStatus;
    private List<String> confirmationNumber;
    private double totalAmount;
    private String beginDate;
    private String endDate;
}
