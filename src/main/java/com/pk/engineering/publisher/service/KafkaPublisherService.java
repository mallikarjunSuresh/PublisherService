package com.pk.engineering.publisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherService implements MQPublisherService {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    
	@Override
	public void publishMessage(String topic, String message) {
        kafkaTemplate.send(topic,message);
		kafkaTemplate.flush();
	}

}
