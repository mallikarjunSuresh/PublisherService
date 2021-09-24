package com.pk.engineering.publisher.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaPublisherService implements MqPublisherService {

  private static final Logger log = LoggerFactory.getLogger(KafkaPublisherService.class);

  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public void publishMessage(String topic, Object message) {
    ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
    future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

      @Override
      public void onFailure(Throwable ex) {
        log.error("Unable to deliver message [{}]. {}", message, ex.getMessage());
      }

      @Override
      public void onSuccess(SendResult<String, Object> result) {
        log.info("Message [{}] delivered with offset {}", message,
            result.getRecordMetadata().offset());

      }
    });
  }


}
