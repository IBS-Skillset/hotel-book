package com.happystays.book.query.infrastructure.handlers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.happystays.book.common.dto.successeventmodel.Pnr;
import com.happystays.book.common.dto.successeventmodel.Trip;
import com.happystays.book.common.events.BookingSuccessEvent;
import com.happystays.book.query.repository.SupplierRepository;
import com.happystays.book.query.repository.TripRepository;
import com.happystays.book.query.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {HotelQueryEventHandler.class})
@ExtendWith(SpringExtension.class)
class HotelQueryEventHandlerTest {
    @Autowired
    private HotelQueryEventHandler hotelQueryEventHandler;

    @MockBean
    private SupplierRepository supplierRepository;

    @MockBean
    private TripRepository tripRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testEventHandlerOn1() {
        com.happystays.book.query.domain.Trip trip = new com.happystays.book.query.domain.Trip();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        trip.setBeginDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        trip.setCurrencyCode("GBP");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        trip.setEndDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        trip.setPnr(new ArrayList<>());
        trip.setTotalPrice(10.0d);
        trip.setTripId(123L);
        when(tripRepository.save((com.happystays.book.query.domain.Trip) any())).thenReturn(trip);

        ArrayList<com.happystays.book.common.dto.successeventmodel.Pnr> pnrList = new ArrayList<>();
        pnrList.add(new com.happystays.book.common.dto.successeventmodel.Pnr());

        com.happystays.book.common.dto.successeventmodel.Trip trip1 = new com.happystays.book.common.dto.successeventmodel.Trip();
        trip1.setPnrList(pnrList);

        ArrayList<com.happystays.book.common.dto.successeventmodel.Pnr> pnrList1 = new ArrayList<>();
        pnrList1.addAll(new ArrayList<>());

        com.happystays.book.common.dto.successeventmodel.Trip trip2 = new com.happystays.book.common.dto.successeventmodel.Trip();
        trip2.setPnrList(pnrList1);
        BookingSuccessEvent bookingSuccessEvent = mock(BookingSuccessEvent.class);
        when(bookingSuccessEvent.getTrip()).thenReturn(trip2);
        doNothing().when(bookingSuccessEvent).setTrip((com.happystays.book.common.dto.successeventmodel.Trip) any());
        bookingSuccessEvent.setTrip(trip1);
        hotelQueryEventHandler.on(bookingSuccessEvent);
        verify(tripRepository).save((com.happystays.book.query.domain.Trip) any());
        verify(bookingSuccessEvent, atLeast(1)).getTrip();
        verify(bookingSuccessEvent).setTrip((com.happystays.book.common.dto.successeventmodel.Trip) any());
    }

    @Test
    void testEventHandlerOn2() {
        com.happystays.book.query.domain.Trip trip = new com.happystays.book.query.domain.Trip();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        trip.setBeginDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        trip.setCurrencyCode("GBP");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        trip.setEndDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        trip.setPnr(new ArrayList<>());
        trip.setTotalPrice(10.0d);
        trip.setTripId(123L);
        when(tripRepository.save((com.happystays.book.query.domain.Trip) any())).thenReturn(trip);

        ArrayList<com.happystays.book.common.dto.successeventmodel.Pnr> pnrList = new ArrayList<>();
        pnrList.add(new com.happystays.book.common.dto.successeventmodel.Pnr());

        com.happystays.book.common.dto.successeventmodel.Trip trip1 = new com.happystays.book.common.dto.successeventmodel.Trip();
        trip1.setPnrList(pnrList);

        ArrayList<com.happystays.book.common.dto.successeventmodel.Pnr> pnrList1 = new ArrayList<>();
        pnrList1.addAll(new ArrayList<>());

        com.happystays.book.common.dto.successeventmodel.Trip trip2 = new com.happystays.book.common.dto.successeventmodel.Trip(
                10.0d, "GBP", "2020-03-01", "2020-03-01", new ArrayList<>());
        trip2.setPnrList(pnrList1);
        BookingSuccessEvent bookingSuccessEvent = mock(BookingSuccessEvent.class);
        when(bookingSuccessEvent.getTrip()).thenReturn(trip2);
        doNothing().when(bookingSuccessEvent).setTrip((com.happystays.book.common.dto.successeventmodel.Trip) any());
        bookingSuccessEvent.setTrip(trip1);
        hotelQueryEventHandler.on(bookingSuccessEvent);
        verify(tripRepository).save((com.happystays.book.query.domain.Trip) any());
        verify(bookingSuccessEvent, atLeast(1)).getTrip();
        verify(bookingSuccessEvent).setTrip((com.happystays.book.common.dto.successeventmodel.Trip) any());
    }
}

