package com.happystays.book.cmd.infrastructure;

import com.happystays.book.cmd.domain.BookAggregate;
import com.happystays.cqrs.core.domain.AggregateRoot;
import com.happystays.cqrs.core.handlers.EventSourcingHandler;
import com.happystays.cqrs.core.infrastucture.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCommandEventHandler implements EventSourcingHandler<BookAggregate> {

    @Autowired
    private EventStore eventStore;

    @Override
    public void saveEvents(AggregateRoot aggregateRoot) {
        eventStore.saveEvents(aggregateRoot.getId(),aggregateRoot.getUncommittedChanges(),aggregateRoot.getVersion());
        aggregateRoot.markChangesAsCommitted();
    }
}
