package com.happystays.book.cmd.infrastructure;

import com.happystays.book.cmd.domain.BookAggregate;
import com.happystays.cqrs.core.domain.AggregateRoot;
import com.happystays.cqrs.core.handlers.EventSourcingHandler;
import com.happystays.cqrs.core.infrastucture.EventStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookCommandEventHandler implements EventSourcingHandler<BookAggregate> {

    private EventStore eventStore;

    @Override
    public void saveEvents(AggregateRoot aggregateRoot) {
        eventStore.saveEvents(aggregateRoot.getId(),aggregateRoot.getUncommittedChanges(),aggregateRoot.getVersion());
        aggregateRoot.markChangesAsCommitted();
    }
}
