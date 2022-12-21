package com.happystays.book.cmd.infrastructure;

import com.happystays.book.cmd.domain.BookAggregate;
import com.happystays.book.cmd.domain.EventStoreRepository;
import com.happystays.book.common.dto.successeventmodel.HotelSegment;
import com.happystays.book.common.dto.successeventmodel.Pnr;
import com.happystays.cqrs.core.events.eventstoremodel.PnrEventModel;
import com.happystays.book.common.events.BookingSuccessEvent;
import com.happystays.cqrs.core.events.BaseEvent;
import com.happystays.cqrs.core.events.eventstoremodel.EventModel;
import com.happystays.cqrs.core.exceptions.ConcurrencyException;
import com.happystays.cqrs.core.infrastucture.EventStore;
import com.happystays.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCommandEventStore implements EventStore {

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Autowired
    private EventProducer eventProducer;

    @Override
    public void saveEvents(String aggregateId, BaseEvent baseEvent, int expectedVersion) {
        eventProducer.produce(baseEvent.getClass().getSimpleName(), baseEvent);
        List<EventModel> eventsList = eventStoreRepository.findByAggregateId(aggregateId);
        if(expectedVersion != -1 && eventsList.get(eventsList.size() - 1).getVersion() != expectedVersion){
            throw new ConcurrencyException();
        }
        int version = expectedVersion;
        BookingSuccessEvent event = (BookingSuccessEvent) baseEvent;
        version ++;
        event.setVersion(version);
        Pnr pnrInformation = event.getPnrList().get(0);
        HotelSegment hotelSegmentInformation = pnrInformation.getHotelInfoList().get(0).getHotelSegmentList().get(0);
        EventModel eventModel = EventModel.builder().version(version)
                .aggregateId(aggregateId)
                .aggregateType(BookAggregate.class.getTypeName())
                .id(event.getId())
                .eventData(PnrEventModel.builder()
                        .bookingState(pnrInformation.getStatus())
                        .confirmationNumber(hotelSegmentInformation.getConfirmationNumber())
                        .beginDate(pnrInformation.getTrip().getBeginDate())
                        .endDate(pnrInformation.getTrip().getEndDate())
                        .occupancy(hotelSegmentInformation.getOccupancy())
                        .totalAmount(pnrInformation.getTrip().getTotalPrice())
                        .creationDate(pnrInformation.getCreationDate())
                        .build())
                .build();
            eventStoreRepository.save(eventModel);
    }
}
