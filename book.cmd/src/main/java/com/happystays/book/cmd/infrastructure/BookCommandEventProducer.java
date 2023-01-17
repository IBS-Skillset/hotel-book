package com.happystays.book.cmd.infrastructure;

import com.happystays.cqrs.core.events.BaseEvent;
import com.happystays.cqrs.core.producers.EventProducer;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookCommandEventProducer implements EventProducer {

    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topic, BaseEvent event) {
        this.kafkaTemplate.send(topic, event);
    }
}
