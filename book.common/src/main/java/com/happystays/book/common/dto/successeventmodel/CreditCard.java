package com.happystays.book.common.dto.successeventmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    private String maskedCardNumber;
    private String cardHolderName;
}
