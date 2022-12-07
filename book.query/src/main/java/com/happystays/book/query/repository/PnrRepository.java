package com.happystays.book.query.repository;

import com.happystays.book.query.domain.Pnr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PnrRepository extends JpaRepository<Pnr, Integer> {
    List<Pnr> findByUserIdAndCreationDateAfter(Long id, Date from);
}
