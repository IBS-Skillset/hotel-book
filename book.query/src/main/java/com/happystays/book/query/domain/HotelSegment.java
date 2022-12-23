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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int hotelSegmentId;
    private double hotelPrice;
    private String currencyCode;
    private String confirmationNumber;
    private int occupancy;
    private Date checkInDate;
    private Date checkOutDate;

    @OneToOne(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name="room_info_id")
    private HotelRoomInfo hotelRoomInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cancellation_info_id")
    private HotelCancellationInfo hotelCancellationInfo;

    @ManyToOne
    @JoinColumn(name="hotel_info_id")
    private HotelInfo hotelInfo;

    public void addRoomInfoDetails(HotelRoomInfo hotelRoomInfo) {
        this.setHotelRoomInfo(hotelRoomInfo);
        hotelRoomInfo.setHotelsegment(this);
    }
    public void addCancellationDetails(HotelCancellationInfo hotelCancellationInfo) {
        this.setHotelCancellationInfo(hotelCancellationInfo);
        hotelCancellationInfo.setHotelsegment(this);
    }
}
