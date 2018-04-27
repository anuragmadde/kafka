package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {

	@KafkaListener(topics = "${topic}")
    public void listen(@Payload String message) {
        System.out.println("received message='{}'"+ message);
    }
	
}
