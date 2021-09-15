package com.pk.engineering.publisher.service;

public interface MQPublisherService {
	
	void publishMessage(String topic,String message);

}
