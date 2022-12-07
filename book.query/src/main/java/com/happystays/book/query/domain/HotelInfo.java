package com.happystays.book.query.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HotelInfo {
    @Id
    private int hotelInfoId;
    private int	totalPrice;
    private String currencyCode;
    private String propertyCode;
    private String propertyName;
    private String hotelAddress;
    private String hotelPhone;
    private String countryCode;
    private String locationCode;
    private boolean guaranteedIndicator;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="info")
    private List<HotelSegment> segment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PaymentMethod paymentMethod;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Pnr pnr;
}
