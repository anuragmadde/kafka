package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {

	@KafkaListener(topics = "${topic}")
    public void listen(@Payload ConsumerRecord<String,TestSchema> message) {
        System.out.println("Message paylaod : " + message.toString());
        System.out.println("Value - "+ message.value());
    }
	
}
