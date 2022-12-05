package com.happystays.book.query;

import com.happystays.book.query.api.queries.FindMyTrips;
import com.happystays.book.query.api.queries.QueryHandler;
import com.happystays.cqrs.core.infrastucture.QueryDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@AllArgsConstructor
public class QueryApplication {

    private final QueryDispatcher queryDispatcher;

    private final QueryHandler queryHandler;

    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class, args);
    }

    @PostConstruct
    public void registerHandlers() {
        queryDispatcher.registerHandler(FindMyTrips.class, queryHandler::handle);
    }
}
