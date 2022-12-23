package com.happystays.book.query.infrastructure.consumers;

import com.happystays.book.common.events.BookingSuccessEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {

    void consume(@Payload BookingSuccessEvent bookingSuccessEvent, Acknowledgment ack);
}
