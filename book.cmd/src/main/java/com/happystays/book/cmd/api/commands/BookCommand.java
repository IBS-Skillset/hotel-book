package com.happystays.book.cmd.api.commands;

import com.happystays.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class BookCommand extends BaseCommand {
    private int numberOfRooms;
    private String ratePlanId;
    private int guestCount;
    private String startDate;
    private String endDate;
    private PaymentInfo paymentInfo;
    private String hotelCode;
    private UserInfo userInfo;
    private CancellationInfo cancellationInfo;
    private boolean breakfastIncluded;
    private String hotelPhone;
}
