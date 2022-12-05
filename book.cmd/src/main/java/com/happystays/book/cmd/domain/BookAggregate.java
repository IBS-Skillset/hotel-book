package com.happystays.book.cmd.domain;

import com.happystays.book.cmd.api.commands.BookCommand;
import com.happystays.book.common.dto.Pnr;
import com.happystays.book.common.dto.HotelInfo;
import com.happystays.book.common.dto.HotelSegment;
import com.happystays.book.common.dto.HotelRoomInfo;
import com.happystays.book.common.dto.PaymentMethod;
import com.happystays.book.common.dto.CreditCard;
import com.happystays.book.common.dto.Trip;
import com.happystays.book.common.dto.HotelCancellationInfo;
import com.happystays.book.common.events.BookingSuccessEvent;
import com.happystays.cqrs.core.domain.AggregateRoot;
import com.happystays.cqrs.core.dto.BookResponse;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.time.Instant;
import java.util.List;

@NoArgsConstructor
public class BookAggregate extends AggregateRoot {

    //ToDo: userId needs to be passed by fetching from OAuth token (currently sending as empty list)

    public BookAggregate (BookResponse bookResponse , BookCommand command) {
        raiseEvent(BookingSuccessEvent.builder()
                .pnrList(List.of(new Pnr(1, Date.from(Instant.now()), bookResponse.getPnrInfo().getBookingState(), new Trip(bookResponse.getRoomRate().getTotalAmount(), bookResponse.getRoomRate().getCurrencyCode(), bookResponse.getStartDate(), bookResponse.getEndDate()),
                        List.of(new HotelInfo(bookResponse.getRoomRate().getTotalAmount(), bookResponse.getRoomRate().getCurrencyCode(), bookResponse.getHotelCode(),
                        bookResponse.getHotelName(), bookResponse.getHotelAddress(), command.getHotelPhone(), command.getCountryCode(), command.getCountryCode(), true,
                        List.of(new HotelSegment(bookResponse.getRoomRate().getTotalAmount(), bookResponse.getRoomRate().getCurrencyCode(), bookResponse.getPnrInfo().getConfirmationNumber(), command.getGuestCount(), bookResponse.getStartDate(), bookResponse.getEndDate(),
                                new HotelRoomInfo(command.getNightlyPrice(), bookResponse.getRoomRate().getRateDescription(), command.isBreakfastIncluded()), new HotelCancellationInfo(command.getCancellationInfo().getCancellationDate(), command.getCancellationInfo().isCancellable(), command.getCancellationInfo().getCancellationPolicy(), command.getCancellationInfo().getCancellationDate()))),
                        new PaymentMethod(command.getPaymentInfo().getPaymentType(), new CreditCard(command.getPaymentInfo().getCardNumber(), command.getPaymentInfo().getCardHolderName())))), List.of())))
                .build());
    }

    public void apply (BookingSuccessEvent event) {
        this.id = event.getId();
    }
}
