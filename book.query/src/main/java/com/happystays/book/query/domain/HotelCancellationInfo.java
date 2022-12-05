package com.happystays.book.query.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HotelCancellationInfo {
    @Id
    private int cancellationInfoId;
    private Date cancellationDeadline;
    private Boolean isCancellable;
    private String cancellationPolicy;
    private Date cancellationDate;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hotelCancellationInfo")
    private HotelSegment segment;

}

