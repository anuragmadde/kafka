package com.example.demo;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import postgrescdc.cdc.intrusion_details.Key;
import postgrescdc.cdc.intrusion_details.Value;


@Service
public class KafkaReceiver {
	
	@Autowired
	public KafkaSender kafkaSender;
	
	@Autowired
	public IntrusionData intrusionData;
	
	@Autowired
	public Location location;	

	@Autowired
	public Address address;

	@KafkaListener(topics = "${topic}")
    public void listen(@Payload ConsumerRecord<Key,Value> message) throws InterruptedException, ExecutionException  {
        System.out.println("Message paylaod : " + message.toString());
        //System.out.println("Value - "+ message.value().toString());
        System.out.println("key - "+ message.key().getReferenceId());
        System.out.println("Name - "+ message.value().getName());
 
        intrusionData.setReference_id(message.key().getReferenceId());
        intrusionData.setProvider(message.value().getProvider());
        intrusionData.setVideo_url(message.value().getVideoUrl());
        intrusionData.setImage_url(message.value().getImageUrl());
        intrusionData.setStatus(message.value().getStatus());
        intrusionData.setType(message.value().getType());
        
        address.setName(message.value().getName());
        address.setPostcode(message.value().getPostcode());
        address.setState(message.value().getState());
        address.setStreet_address(message.value().getAddressLine());
        address.setSuburb(message.value().getSuburb());
        
        intrusionData.setAddress(address);
        
        
        location.setLatitude(message.value().getLocationLatitude());
        location.setLongitude(message.value().getLocationLongitude());
        
        intrusionData.setLatitude(location);
        
        Gson gson = new Gson();
        String intrusionJson = gson.toJson(intrusionData);
        
        System.out.println("Intrusion Data - "+ intrusionJson);
        
        //Json json = new Json();
        
        
        
        kafkaSender.send("mongoIntrusionTopic", message.key().toString() ,intrusionJson);
        
        System.out.println("Data sent to Topic");
        
    }
	
}
