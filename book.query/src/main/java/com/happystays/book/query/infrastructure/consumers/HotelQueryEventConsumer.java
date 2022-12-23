package com.happystays.book.query.infrastructure.consumers;

import com.happystays.book.common.events.BookingSuccessEvent;
import com.happystays.book.query.infrastructure.handlers.EventHandler;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HotelQueryEventConsumer implements EventConsumer{
    private EventHandler eventHandler;

    @KafkaListener(topics = "BookingSuccessEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(BookingSuccessEvent bookingSuccessEvent, Acknowledgment ack) {
        eventHandler.on(bookingSuccessEvent);
        ack.acknowledge();;
    }
}
