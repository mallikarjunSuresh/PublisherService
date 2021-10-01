package com.pk.engineering.publisher.service;

public interface KafkaPublisherService {

  void publishMessage(String topic, Object message);

}
