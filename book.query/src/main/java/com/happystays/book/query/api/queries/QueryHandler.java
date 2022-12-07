package com.happystays.book.query.api.queries;

import com.happystays.cqrs.core.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindMyTrips query);
}
