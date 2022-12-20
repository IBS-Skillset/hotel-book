package com.happystays.book.cmd.infrastructure;

import com.happystays.book.cmd.domain.BookAggregate;
import com.happystays.book.cmd.domain.EventStoreRepository;
import com.happystays.cqrs.core.dto.events.successevent.HotelSegment;
import com.happystays.cqrs.core.dto.events.successevent.Pnr;
import com.happystays.cqrs.core.dto.events.eventmodel.PnrEventModel;
import com.happystays.cqrs.core.events.BookingSuccessEvent;
import com.happystays.cqrs.core.events.EventModel;
import com.happystays.cqrs.core.exceptions.ConcurrencyException;
import com.happystays.cqrs.core.infrastucture.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCommandEventStore implements EventStore {

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Override
    public void saveEvents(String aggregateId, Iterable<BookingSuccessEvent> events, int expectedVersion) {
        List<EventModel> eventsList = eventStoreRepository.findByAggregateId(aggregateId);
        if(expectedVersion != -1 && eventsList.get(eventsList.size() - 1).getVersion() != expectedVersion){
            throw new ConcurrencyException();
        }
        int version = expectedVersion;
        for(BookingSuccessEvent event : events){
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
}
