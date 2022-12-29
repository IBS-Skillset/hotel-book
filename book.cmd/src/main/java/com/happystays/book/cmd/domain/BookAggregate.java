package com.happystays.book.cmd.domain;

import com.happystays.book.cmd.api.commands.BookCommand;
import com.happystays.book.common.dto.responsemodel.BookResponse;
import com.happystays.book.common.dto.successeventmodel.Trip;
import com.happystays.book.common.dto.successeventmodel.HotelInfo;
import com.happystays.book.common.dto.successeventmodel.Pnr;
import com.happystays.book.common.dto.successeventmodel.HotelSegment;
import com.happystays.book.common.dto.successeventmodel.HotelRoomInfo;
import com.happystays.book.common.dto.successeventmodel.PaymentMethod;
import com.happystays.book.common.dto.successeventmodel.CreditCard;
import com.happystays.book.common.dto.successeventmodel.HotelCancellationInfo;
import com.happystays.cqrs.core.domain.AggregateRoot;
import com.happystays.book.common.events.BookingSuccessEvent;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.time.Instant;
import java.util.List;

@NoArgsConstructor
public class BookAggregate extends AggregateRoot {

    //ToDo: userId needs to be passed by fetching from OAuth token (currently sending as empty list)

    public BookAggregate (BookResponse bookResponse , BookCommand command) {
        raiseEvent(BookingSuccessEvent.builder().id(command.getId())
                .trip(new Trip(bookResponse.getRoomRate().getTotalAmount(), bookResponse.getRoomRate().getCurrencyCode(), bookResponse.getStartDate(), bookResponse.getEndDate() ,
                        List.of(new Pnr(1L, Date.from(Instant.now()), bookResponse.getPnrInfo().getBookingDescription(),
                                List.of(new HotelInfo(bookResponse.getRoomRate().getTotalAmount(), bookResponse.getRoomRate().getCurrencyCode(), bookResponse.getHotelCode(),
                                        bookResponse.getHotelName(), bookResponse.getHotelAddress().toString(), command.getHotelPhone(), command.getCountryCode(), command.getCountryCode(), true,
                                        List.of(new HotelSegment(bookResponse.getRoomRate().getTotalAmount(), bookResponse.getRoomRate().getCurrencyCode(), bookResponse.getPnrInfo().getConfirmationNumber(), command.getGuestCount(), bookResponse.getStartDate(), bookResponse.getEndDate(),
                                                new HotelRoomInfo(command.getNightlyPrice(), bookResponse.getRoomRate().getRateDescription(), command.isBreakfastIncluded()), new HotelCancellationInfo(command.getCancellationInfo().getCancellationDate(), command.getCancellationInfo().isCancellable(), command.getCancellationInfo().getCancellationPolicy(), command.getCancellationInfo().getCancellationDate()))),
                                        new PaymentMethod(command.getPaymentInfo().getPaymentType(), new CreditCard(command.getPaymentInfo().getCardNumber(), command.getPaymentInfo().getCardHolderName())))), List.of()))))
                .build());

    }

    public void apply (BookingSuccessEvent event) {
        this.id = event.getId();
    }
}
