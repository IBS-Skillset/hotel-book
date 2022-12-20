package com.happystays.book.cmd.domain;

import com.happystays.cqrs.core.events.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStoreRepository extends MongoRepository<EventModel, String> {
    public List<EventModel> findByAggregateId(String aggregateId);
}
