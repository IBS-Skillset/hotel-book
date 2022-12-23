package com.happystays.book.query.domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Supplier {
    @Id
    @Column(name = "supplier_id")
    private int supplierId;
    private String supplierCode;
    private String supplierName;
    private String supplierUri;
}