package com.happystays.book.query.domain;

import com.happystays.cqrs.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pnr extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pnrId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private Date creationDate;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pnr")
    private List<HotelInfo> hotelInfo;

    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "pnrMembers", joinColumns = {
            @JoinColumn(name = "pnrId")}, inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<User> user;
}
