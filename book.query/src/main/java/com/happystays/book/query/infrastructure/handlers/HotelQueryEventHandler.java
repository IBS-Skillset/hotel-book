package com.happystays.book.query.infrastructure.handlers;

import com.happystays.book.common.events.BookingSuccessEvent;
import com.happystays.book.common.utils.CommonUtils;
import com.happystays.book.query.domain.*;
import com.happystays.book.query.repository.SupplierRepository;
import com.happystays.book.query.repository.TripRepository;
import com.happystays.book.query.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelQueryEventHandler implements EventHandler {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public void on(BookingSuccessEvent bookingSuccessEvent) {
        List<Pnr> pnrList = bookingSuccessEvent.getTrip().getPnrList().stream().map(p -> {
            List<HotelInfo> hotelInfoList = p.getHotelInfoList().stream().map(info -> {
                List<HotelSegment> hotelSegmentList = info.getHotelSegmentList().stream().map(segment -> {
                    HotelRoomInfo hotelRoomInfo = getHotelRoomInfo(segment);
                    HotelCancellationInfo hotelCancellationInfo = getHotelCancellationInfo(segment);
                    HotelSegment hotelSegment = getSegment(segment);
                    hotelSegment.addRoomInfoDetails(hotelRoomInfo);
                    hotelSegment.addCancellationDetails(hotelCancellationInfo);
                    return hotelSegment;
                }).collect(Collectors.toList());
                CreditCard creditCard = getCreditCard(info);
                PaymentMethod paymentMethod = getPaymentMethod(info);
                paymentMethod.addCreditCard(creditCard);
                HotelInfo hotelInfo = getHotelInfo(info);
                hotelInfo.addHotelSegmentList(hotelSegmentList);
                hotelInfo.addPaymentDetails(paymentMethod);
                return hotelInfo;
            }).collect(Collectors.toList());

            var users = userRepository.findAllById(p.getUserIdList());
            var supplier = supplierRepository.findById(p.getSupplierId());
            return getPnr(p, hotelInfoList, users, supplier.get());
        }).collect(Collectors.toList());

        Trip trip = new Trip();
        trip.setBeginDate(CommonUtils.formatDate(bookingSuccessEvent.getTrip().getBeginDate(), CommonUtils.DATE_YYYY_MM_DD));
        trip.setEndDate(CommonUtils.formatDate(bookingSuccessEvent.getTrip().getEndDate(), CommonUtils.DATE_YYYY_MM_DD));
        trip.setCurrencyCode(bookingSuccessEvent.getTrip().getCurrencyCode());
        trip.setTotalPrice(bookingSuccessEvent.getTrip().getTotalPrice());
        trip.addPnrList(pnrList);
        trip.setPnr(pnrList);
        tripRepository.save(trip);
    }

    private Pnr getPnr(com.happystays.book.common.dto.successeventmodel.Pnr p, List<HotelInfo> hotelInfoList, List<User> users, Supplier supplier) {
        Pnr pnr = Pnr.builder().
                supplier(supplier).
                creationDate(p.getCreationDate()).
                status(p.getStatus()).
                hotelInfo(new ArrayList<>()).
                user(users).build();
        pnr.addHotelInfoList(hotelInfoList);
        return pnr;
    }

    private HotelInfo getHotelInfo(com.happystays.book.common.dto.successeventmodel.HotelInfo info) {
        return HotelInfo.builder().
                totalPrice(info.getTotalPrice()).
                currencyCode(info.getCurrencyCode()).
                propertyCode(info.getPropertyCode()).
                propertyName(info.getPropertyName()).
                hotelAddress(info.getHotelAddress()).
                hotelPhone(info.getHotelPhone()).
                countryCode(info.getCountryCode()).
                segment(new ArrayList<>()).
                locationCode(info.getLocationCode()).
                guaranteedIndicator(info.isGuaranteedIndicator())
                .build();
    }

    private PaymentMethod getPaymentMethod(com.happystays.book.common.dto.successeventmodel.HotelInfo info) {
        return PaymentMethod.builder().
                paymentType(info.getPaymentMethod().getPaymentType())
                .build();
    }

    private CreditCard getCreditCard(com.happystays.book.common.dto.successeventmodel.HotelInfo info) {
        return CreditCard.builder().
                maskedCardNumber(info.getPaymentMethod().getCreditCard().getMaskedCardNumber()).
                cardHolderName(info.getPaymentMethod().getCreditCard().getCardHolderName()).build();
    }

    private HotelRoomInfo getHotelRoomInfo(com.happystays.book.common.dto.successeventmodel.HotelSegment segment) {
        return HotelRoomInfo.builder().
                nightlyPrice(segment.getHotelRoomInfo().getNightlyPrice()).
                rateDescription(segment.getHotelRoomInfo().getRateDescription()).
                breakfastIncluded(segment.getHotelRoomInfo().isBreakfastIncluded()).build();
    }

    private HotelSegment getSegment(com.happystays.book.common.dto.successeventmodel.HotelSegment segment) {
        return HotelSegment.builder().
                hotelPrice(segment.getHotelPrice()).
                currencyCode(segment.getCurrencyCode()).
                confirmationNumber(segment.getConfirmationNumber()).
                occupancy(segment.getOccupancy()).
                checkInDate(CommonUtils.formatDate(segment.getCheckInDate(), CommonUtils.DATE_YYYY_MM_DD)).
                checkOutDate(CommonUtils.formatDate(segment.getCheckOutDate(), CommonUtils.DATE_YYYY_MM_DD)).
                build();
    }

    private HotelCancellationInfo getHotelCancellationInfo(com.happystays.book.common.dto.successeventmodel.HotelSegment segment) {
        return HotelCancellationInfo.builder().
                cancellationDeadline(CommonUtils.formatDate(segment.getCancellationInfo().getCancellationDeadline(), CommonUtils.DATE_YYYY_MM_DD_HH_MM_SS)).
                isCancellable(segment.getCancellationInfo().getIsCancellable()).
                cancellationPolicy(segment.getCancellationInfo().getCancellationPolicy()).
                cancellationDate(CommonUtils.formatDate(segment.getCancellationInfo().getCancellationDate(), CommonUtils.DATE_YYYY_MM_DD_HH_MM_SS)).
                build();
    }
}