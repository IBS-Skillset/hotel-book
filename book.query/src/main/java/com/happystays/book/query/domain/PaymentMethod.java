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
    private int paymentId;
    private String paymentType;
    private int hotelInfoId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private  CreditCard creditCard;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "paymentMethod")
    private HotelInfo info;
}
