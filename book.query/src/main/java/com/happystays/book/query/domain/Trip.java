package com.happystays.book.query.domain;

import com.happystays.cqrs.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Trip extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tripId;
    private double totalPrice;
    private String currencyCode;
    private Date beginDate;
    private Date endDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trip",cascade = {CascadeType.ALL})
    private List<Pnr> pnr=new ArrayList<>();

    public void addPnrList(List<Pnr> pnrs) {
        for (Pnr x : pnrs) {
            this.pnr.add(x);
            x.setTrip(this);
        }
    }
}