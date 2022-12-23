package com.happystays.book.query.infrastructure.handlers;

import com.happystays.book.common.events.BookingSuccessEvent;

public interface EventHandler {

    void on(BookingSuccessEvent bookingSuccessEvent);
}