package com.happystays.book.query.domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Trip {
    @Id
    private int tripId;
    private double totalPrice;
    private String currencyCode;
    private Date beginDate;
    private Date endDate;

    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "trip")
    private Pnr pnr;
}
