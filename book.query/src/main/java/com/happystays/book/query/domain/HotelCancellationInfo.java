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
public class HotelCancellationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cancellationInfoId;
    private Date cancellationDeadline;
    private Boolean isCancellable;
    private String cancellationPolicy;
    private Date cancellationDate;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hotelCancellationInfo")
    private HotelSegment segment;

}

