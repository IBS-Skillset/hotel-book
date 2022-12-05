package com.happystays.book.query.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HotelSegment {
    @Id
    private int hotelSegmentId;
    private int hotelPrice;
    private String currencyCode;
    private String confirmationNumber;
    private int occupancy;
    private Date checkInDate;
    private Date checkOutDate;

    @OneToOne(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private HotelRoomInfo hotelRoomInfo;

    @OneToOne(cascade = CascadeType.ALL)
    private HotelCancellationInfo hotelCancellationInfo;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private HotelInfo info;
}
