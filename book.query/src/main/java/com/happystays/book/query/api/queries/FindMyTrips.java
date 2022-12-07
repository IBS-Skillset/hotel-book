package com.happystays.book.query.api.queries;

import com.happystays.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindMyTrips extends BaseQuery {
    private String id;
}
