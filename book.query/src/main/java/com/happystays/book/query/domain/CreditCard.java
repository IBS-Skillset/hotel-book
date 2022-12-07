package com.happystays.book.query.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CreditCard {
    @Id
    private int creditCardId;
    private String maskedCardNumber ;
    private String cardHolderFirstName;
    private String cardHolderLastName;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "creditCard")
    private  PaymentMethod paymentMethod;
}
