package com.happystays.book.common.dto.commandmodel;

import lombok.Data;

@Data
public class PaymentInfo {
    private String paymentType;
    private String cardType;
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private String cardHolderName;
}
