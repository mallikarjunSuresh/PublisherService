package com.pk.engineering.publisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import com.pk.engineering.publisher.model.CustomerPayload;
import com.pk.engineering.publisher.model.KafkaQueuePayload;
import com.pk.engineering.publisher.model.Request;
import com.pk.engineering.publisher.model.KafkaQueuePayload.Level;
import com.pk.engineering.publisher.service.KafkaSuccessPayloadServiceImpl;
import com.pk.engineering.publisher.util.ObjectMapperUtil;


class KafkaSuccessPayloadServiceTest {

  private KafkaSuccessPayloadServiceImpl payloadService = new KafkaSuccessPayloadServiceImpl();

  @Test
  void testgeneratePayLoadWhenCalledWithValidArgumentShouldReturnJsonStringOfCustomerQueuePayLoadModel() {

    // Given
    UUID ActivityId = UUID.randomUUID();
    UUID TransactionalId = UUID.randomUUID();
    Request customerResponse = new Request();
    CustomerPayload payload = new CustomerPayload(ActivityId, TransactionalId, customerResponse);

    // When
    String actual = payloadService.generatePayload(payload);

    // Then
    KafkaQueuePayload queuePayload = new KafkaQueuePayload();
    queuePayload.setLevel(Level.INFO);
    String expected = ObjectMapperUtil.writeValueAsString(queuePayload);

    assertEquals(expected, actual);


  }

}
