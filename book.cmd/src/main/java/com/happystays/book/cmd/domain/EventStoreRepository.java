package com.happystays.book.cmd.domain;

import com.happystays.cqrs.core.events.BaseEventStore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStoreRepository extends MongoRepository<BaseEventStore, String> {
    List<BaseEventStore> findByAggregateId(String aggregateId);
}
