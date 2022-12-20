package com.happystays.book.query.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int creditCardId;
    private String maskedCardNumber ;
    private String cardHolderName;

    @OneToOne(mappedBy = "creditCard")
    private  PaymentMethod paymentMethod;
}
