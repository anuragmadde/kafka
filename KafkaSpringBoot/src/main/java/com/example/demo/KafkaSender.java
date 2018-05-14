package com.example.demo;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${topic}")
	String kafkaTopic = "myTopic";
	
	@Autowired
	TwitterStreamProducer twitterStreamProducer;
	
	public void send(String key, String message) throws InterruptedException, ExecutionException {
	    
		//Simple Send
		kafkaTemplate.send(kafkaTopic,key, message);
		//twitterStreamProducer.getTwitterMessage(kafkaTemplate);
		
		
		//Synchronous Send
//	    SendResult<String, String> recordMetaData = kafkaTemplate.send(kafkaTopic,key, message).get();
//	    System.out.println("Received response back from broker-- partition :" 
//	    + recordMetaData.getRecordMetadata().partition() + "-- offser no :"+ recordMetaData.getRecordMetadata().offset());
	    
	    //Async Send
	    //kafkaTemplate.send(kafkaTopic,key, message);
	 
	}
	
public void send(String topic,String key, String message) throws InterruptedException, ExecutionException {
	    
		//Simple Send
		kafkaTemplate.send(topic, message);
		//twitterStreamProducer.getTwitterMessage(kafkaTemplate);
		
		
		//Synchronous Send
//	    SendResult<String, String> recordMetaData = kafkaTemplate.send(kafkaTopic,key, message).get();
//	    System.out.println("Received response back from broker-- partition :" 
//	    + recordMetaData.getRecordMetadata().partition() + "-- offser no :"+ recordMetaData.getRecordMetadata().offset());
	    
	    //Async Send
	    //kafkaTemplate.send(kafkaTopic,key, message);
	 
	}
	
}


