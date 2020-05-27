package com.hackathon.kafka;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import postgres.cdc.intrusion_details.Key;
import postgres.cdc.intrusion_details.Value;


@Service
public class KafkaPostgresReceiver {
	
	@Autowired
	public KafkaSender kafkaSender;
	
	@Autowired
	public IntrusionData intrusionData;
	
	@Autowired
	public Location location;	

	@Autowired
	public Address address;
	
	/*
	 * Listener Class for Postgresql Topic.
	 *
	 */
	

	@KafkaListener(topics = "${postgres_topic}")
    public void listenPostgresTopic(@Payload ConsumerRecord<Key,Value> message) throws InterruptedException, ExecutionException, ParseException  {
       
        System.out.println("POSTGRES key - "+ message.key());
        System.out.println("POSTGRES Value - "+ message.value());
        
        // Map the message data to intrusion data
 
        intrusionData.setUuid(message.key().getReferenceId());
        intrusionData.setProvider(message.value().getProvider());
        intrusionData.setVideo_url(message.value().getVideoUrl());
        intrusionData.setImage_url(message.value().getImageUrl());
        intrusionData.setStatus(message.value().getStatus());
        intrusionData.setAlert_type(message.value().getAlertType());
        intrusionData.setIntrusion_type(message.value().getIntrusionType());
        
        address.setName(message.value().getName());
        address.setPostcode(message.value().getPostcode());
        address.setState(message.value().getState());
        address.setStreet_address(message.value().getAddressLine());
        address.setSuburb(message.value().getSuburb());
        
        intrusionData.setAddress(address);
        
        
        location.setLatitude(message.value().getLocationLatitude());
        location.setLongitude(message.value().getLocationLongitude());
        
        intrusionData.setLatitude(location);

        OffsetDateTime odt = OffsetDateTime.parse(message.value().getAlertTime());
        Instant instant = odt.toInstant();
        Timestamp timestamp = Timestamp.from(instant);
        
        intrusionData.setAlerted(timestamp);
        
        //convert to json and send to mongodb topic
        
        Gson gson = new Gson();
        String intrusionJson = gson.toJson(intrusionData);
        
        System.out.println("Intrusion Data - "+ intrusionJson);
              
        kafkaSender.send("mongoIntrusionTopic", message.key().toString() ,intrusionJson);
        
        System.out.println("Data sent to mongoIntrusionTopic from Postgres Source");
        
    }
		
}
