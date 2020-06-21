/**
 * @author D. Scott Sabre
 * @since 2020
 * All rights reserved. This software is proprietary property owned by the author. This software is fully
 * available for reuse, however if the contents remain wholly or substantially intact, the user agrees
 * to keep this statement in that content. This software is offered "as-is" and without warranty, and the
 * author assumes no liability for damages resulting from use. The user shall shall use this software only
 * in accordance with these terms.
 */

package com.sabre.kafkaconsumerproducer.message.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.sabre.kafkaconsumerproducer.model.Demo;

@Configuration
public class KafkaDemoConfig {
     
    @Value(value = "${kafka.bootstrapServer}")
    private String bootstrapServer;
    
    @Value(value = "$kafka.demoConsumerGroupId")
    private String demoGroupId;
 
    @Bean
    public KafkaAdmin kafkaAdminConfig() {
        Map<String, Object> bootstrapConfig = new HashMap<>();
        bootstrapConfig.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        return new KafkaAdmin(bootstrapConfig);
    }
    
    @Bean
    public ProducerFactory<String, Demo> producerFactory() {
        Map<String, Object> producerConfig = new HashMap<>();
        producerConfig.put(
          ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          bootstrapServer);
        producerConfig.put(
          ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        producerConfig.put(
          ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
          JsonSerializer.class);
        return new DefaultKafkaProducerFactory<String, Demo>(producerConfig);
    }
 
    @Bean
    public KafkaTemplate<String, Demo> kafkaTemplate() {
        return new KafkaTemplate<String, Demo>(producerFactory());
    }
    
    @Bean
    public ConsumerFactory<String, Demo> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put( ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<String, Demo>(props, 
        		new StringDeserializer(), 
        		new JsonDeserializer<Demo>(Demo.class));
    }
 
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Demo>  kafkaListenerContainerFactory() {
    
        ConcurrentKafkaListenerContainerFactory<String, Demo> factory =
          new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}

 
