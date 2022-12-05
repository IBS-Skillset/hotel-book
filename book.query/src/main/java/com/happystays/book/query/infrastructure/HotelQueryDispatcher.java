package com.happystays.book.query.infrastructure;

import com.happystays.cqrs.core.domain.BaseEntity;
import com.happystays.cqrs.core.infrastucture.QueryDispatcher;
import com.happystays.cqrs.core.queries.BaseQuery;
import com.happystays.cqrs.core.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class HotelQueryDispatcher implements QueryDispatcher {
    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        var handlers = routes.get(query.getClass());
        if (handlers == null || handlers.size() == 0) {
            throw new RuntimeException("No query handler was registered!");
        }
        if (handlers.size() > 1) {
            throw new RuntimeException("Cannot send query to more than one handler!");
        }
        return handlers.get(0).handle(query);
    }
}
