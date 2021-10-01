package com.pk.engineering.publisher.service;

import com.pk.engineering.publisher.model.GenericKafkaEvent;

public interface KafkaPayloadService<T> {

  GenericKafkaEvent<T> generateSucessPayload(T payload);

}
