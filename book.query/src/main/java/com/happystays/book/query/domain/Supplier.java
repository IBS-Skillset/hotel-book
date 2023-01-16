package com.happystays.book.query.domain;

import lombok.*;

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
    private long supplierId;
    private String supplierCode;
    private String supplierName;
    private String supplierUri;
}