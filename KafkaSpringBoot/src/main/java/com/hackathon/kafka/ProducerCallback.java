package com.hackathon.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerCallback implements Callback{

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		if(exception !=null){
			System.out.println("asynchrous callback exception - " +exception);
		}
		else{
			System.out.println("asynchronous callback successfull. Metadata : "+ metadata.toString());
		}
	}

}
