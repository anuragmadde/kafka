package com.hackathon.kafka;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

@Component
public class TwitterStreamProducer {
	
	@Value("${topic}")
	String kafkaTopic = "myTopic";
	
	
	public void getTwitterMessage(KafkaTemplate producer) {
		
		String consumerKey="4ME3AqSVQ8SCh4zK5E0dFQfAa";
		String consumerSecret="krofflYk9oHlyTuUr6lWjzX6bDK3hEXwe75ODRvEmIkYuqrB9M";
		String token="152245066-FzpgKb4GU8KZmnVUHqsRDYlX6L4afmkhNSnWnKB9";
		String secret="SoHlFAgzC91KhoXql8jGj6reypIN592A0gAmMqJzlBbT7";
		ProducerRecord<String, String> message=null;
		
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);
		StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
		
		endpoint.trackTerms(Lists.newArrayList("twitterapi","#Brexit"));
		
		Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
		
		Client client = new ClientBuilder().hosts(Constants.STREAM_HOST).endpoint(endpoint)
				.authentication(auth).processor(new StringDelimitedProcessor(queue)).build();
		
		
		client.connect();
		
		for(int msgread = 0 ; msgread < 10000; msgread++) {
			try {
				String msg = queue.take();
				System.out.println("Twitter msg - "+msg);
				
				message=new ProducerRecord<>(kafkaTopic, queue.take());
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			producer.send(message);
		}
		
		
		client.stop();
		
	}

}
