package com.happystays.book.query.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HotelInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int hotelInfoId;
    private Double totalPrice;
    private String currencyCode;
    private String propertyCode;
    private String propertyName;
    private String hotelAddress;
    private String hotelPhone;
    private String countryCode;
    private String locationCode;
    private boolean guaranteedIndicator;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "hotelInfo")
    private List<HotelSegment> segment=new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "pnr_id")
    private Pnr pnr;

    public void addHotelSegmentList(List<HotelSegment> segment) {
        for (HotelSegment x : segment) {
            this.segment.add(x);
            x.setHotelInfo(this);
        }
    }

    public void addPaymentDetails(PaymentMethod paymentMethod) {
            this.setPaymentMethod(paymentMethod);
                 paymentMethod.setHotelInfo(this);
        }
}
