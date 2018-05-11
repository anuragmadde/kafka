package com.example.demo;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaSpringBootController {

	@Autowired
	KafkaSender kafkaSender;

	@RequestMapping(value = "/producer")
	public String producer(@RequestParam("key") String key,@RequestParam("message") String message) throws InterruptedException, ExecutionException {
		kafkaSender.send(key,message);

		return "Message sent to the Kafka Topic Successfully";
	}
	
}
