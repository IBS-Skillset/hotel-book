package com.happystays.book.query.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;
    private String paymentType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creditcard_id")
    private CreditCard creditCard;

    @OneToOne(mappedBy = "paymentMethod")
    private HotelInfo hotelInfo;

    public void addCreditCard(CreditCard creditCard) {
        this.setCreditCard(creditCard);
        creditCard.setPaymentMethod(this);
    }
}
