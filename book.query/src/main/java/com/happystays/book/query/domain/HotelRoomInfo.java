package com.happystays.book.query.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HotelRoomInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomInfoId;
    private Double nightlyPrice;
    private String rateDescription;
    private boolean breakfastIncluded;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotelRoomInfo")
    private HotelSegment segment;

}
