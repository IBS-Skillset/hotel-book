package com.happystays.book.query.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    @Column(length = 60)
    private String password;
    @Column(unique = true)
    private String email;
    private String role;
    private boolean enabled = true;
    private String phone;

    @LazyCollection(LazyCollectionOption.EXTRA)
    @ManyToMany(mappedBy = "user", cascade = { CascadeType.MERGE })
    private List<Pnr> pnr;
}