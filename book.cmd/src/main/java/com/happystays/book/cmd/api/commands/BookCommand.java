package com.happystays.book.cmd.api.commands;

import com.happystays.cqrs.core.commands.BaseCommand;
import com.happystays.cqrs.core.dto.request.CancellationInfo;
import com.happystays.cqrs.core.dto.request.PaymentInfo;
import com.happystays.cqrs.core.dto.request.UserInfo;
import lombok.Data;

@Data
public class BookCommand extends BaseCommand {
    private String languageCode;
    private String hotelCode;
    private String countryCode;
    private String ratePlanId;
    private int numberOfRooms;
    private int guestCount;
    private String startDate;
    private String endDate;
    private PaymentInfo paymentInfo;
    private UserInfo userInfo;
    private boolean breakfastIncluded;
    private String hotelPhone;
    private double nightlyPrice;
    private CancellationInfo cancellationInfo;
}
