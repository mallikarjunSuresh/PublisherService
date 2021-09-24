package com.pk.engineering.publisher.service;

public interface MqPublisherService {

  void publishMessage(String topic, Object message);

}
