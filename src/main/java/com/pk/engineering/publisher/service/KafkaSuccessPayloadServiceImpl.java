package com.pk.engineering.publisher.service;

import org.springframework.stereotype.Service;
import com.pk.engineering.publisher.model.CustomerPayload;
import com.pk.engineering.publisher.model.KafkaQueuePayload;
import com.pk.engineering.publisher.model.KafkaQueuePayload.Level;
import com.pk.engineering.publisher.util.ObjectMapperUtil;

@Service
public class KafkaSuccessPayloadServiceImpl implements SuccessPayloadService {

  @Override
  public String generatePayload(CustomerPayload payload) {

    KafkaQueuePayload queuePayload = new KafkaQueuePayload();
    queuePayload.setLevel(Level.INFO);

    return ObjectMapperUtil.writeValueAsString(queuePayload);
  }

}
