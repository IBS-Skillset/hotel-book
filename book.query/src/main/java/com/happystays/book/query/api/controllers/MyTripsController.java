package com.happystays.book.query.api.controllers;

import com.happystays.book.query.api.dto.MyTripsResponse;
import com.happystays.book.query.api.queries.FindMyTrips;
import com.happystays.book.query.domain.MyTripsResponseDto;
import com.happystays.book.query.domain.Pnr;
import com.happystays.cqrs.core.infrastucture.QueryDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class MyTripsController {
    private final Logger logger = Logger.getLogger(MyTripsController.class.getName());

    private final QueryDispatcher queryDispatcher;

    public MyTripsController(QueryDispatcher queryDispatcher) {
        this.queryDispatcher = queryDispatcher;
    }

    @GetMapping(path = "/myTrips/{id}")
    public ResponseEntity<MyTripsResponse> getMyTrips(@PathVariable(value = "id") String id) {
        try {
            List<MyTripsResponseDto> myTrips = queryDispatcher.send(new FindMyTrips(id));

            if (CollectionUtils.isEmpty(myTrips)) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            var response = MyTripsResponse.builder()
                    .myTrips(myTrips)
                    .message("Successfully returned trips!")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to get trips by ID request!";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new MyTripsResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}