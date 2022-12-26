package com.happystays.book.cmd.infrastructure;

import com.happystays.book.cmd.domain.BookAggregate;
import com.happystays.book.cmd.domain.EventStoreRepository;
import com.happystays.book.common.events.PnrEvent;
import com.happystays.book.common.dto.successeventmodel.Trip;
import com.happystays.book.common.events.BookingSuccessEvent;
import com.happystays.cqrs.core.events.BaseEvent;
import com.happystays.cqrs.core.events.BaseEventStore;
import com.happystays.cqrs.core.exceptions.ConcurrencyException;
import com.happystays.cqrs.core.infrastucture.EventStore;
import com.happystays.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@Service
public class BookCommandEventStore implements EventStore {

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Autowired
    private EventProducer eventProducer;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> baseEvents, int expectedVersion) {
        List<BaseEventStore> eventsList = eventStoreRepository.findByAggregateId(aggregateId);
        for(BaseEvent baseEvent : baseEvents){
            eventProducer.produce(baseEvent.getClass().getSimpleName(), baseEvent);
            if (expectedVersion != -1 && eventsList.get(eventsList.size() - 1).getVersion() != expectedVersion) {
                throw new ConcurrencyException();
            }
            int version = expectedVersion;
            BookingSuccessEvent bookingSuccessEvent = (BookingSuccessEvent) baseEvent;
            version++;
            bookingSuccessEvent.setVersion(version);
            PnrEvent pnrEvent = setPnrEvent(bookingSuccessEvent);
            eventStoreRepository.save(pnrEvent);
        }
    }

    private PnrEvent setPnrEvent(BookingSuccessEvent bookingSuccessEvent) {
        List<String> confirmationNumbers = new ArrayList<>();
        bookingSuccessEvent.getTrip().getPnrList().forEach(pnr -> {
            pnr.getHotelInfoList().forEach(hotelInfo -> {
                hotelInfo.getHotelSegmentList().forEach(hotelSegment -> {
                    confirmationNumbers.add(hotelSegment.getConfirmationNumber());
                });
            });
        });
        Trip trip = bookingSuccessEvent.getTrip();
        PnrEvent pnrEvent = PnrEvent.builder()
                .version(bookingSuccessEvent.getVersion())
                .aggregateId(bookingSuccessEvent.getId())
                .aggregateType(BookAggregate.class.getSimpleName())
                .id(bookingSuccessEvent.getId())
                .eventType(bookingSuccessEvent.getClass().getSimpleName())
                .creationDate(Date.from(Instant.now()))
                .bookingStatus(trip.getPnrList().get(0).getStatus())
                .confirmationNumber(confirmationNumbers)
                .beginDate(trip.getBeginDate())
                .endDate(trip.getEndDate())
                .totalAmount(trip.getTotalPrice())
                .build();
        return pnrEvent;
    }
}
