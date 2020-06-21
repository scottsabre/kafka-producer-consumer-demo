/**
 * @author D. Scott Sabre
 * @since 2020
 * All rights reserved. This software is proprietary property owned by the author. This software is fully
 * available for reuse, however if the contents remain wholly or substantially intact, the user agrees
 * to keep this statement in that content. This software is offered "as-is" and without warranty, and the
 * author assumes no liability for damages resulting from use. The user shall shall use this software only
 * in accordance with these terms.
 */
package com.sabre.kafkaconsumerproducer.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sabre.kafkaconsumerproducer.model.Demo;
import com.sabre.kafkaconsumerproducer.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService{
	
	@KafkaListener(topics = "${kafka.demoTopic}", groupId = "${kafka.demoConsumerGroupId}")
	public Demo consumeDemoMessage(Demo consumedDemo) {
	    prettyPrint(consumedDemo);
	   
	    return consumedDemo;
	}
	
	private static void prettyPrint(Demo demo) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.err.println("Payload Consumed:");
		System.err.println(gson.toJson(demo));
	}
		
	
}
