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
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.sabre.kafkaconsumerproducer.model.Demo;

@Configuration
public class KafkaProducerConfig {
     
    @Value(value = "${kafka.bootstrapServer}")
    private String bootstrapServer;
 
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
}
