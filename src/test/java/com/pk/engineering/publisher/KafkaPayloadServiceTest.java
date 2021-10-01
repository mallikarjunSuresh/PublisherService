package com.pk.engineering.publisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import com.pk.engineering.publisher.model.CustomerPayload;
import com.pk.engineering.publisher.model.CustomerRequest;
import com.pk.engineering.publisher.model.GenericKafkaEvent;
import com.pk.engineering.publisher.service.KafkaPayloadServiceImpl;


class KafkaPayloadServiceTest {

  private KafkaPayloadServiceImpl<CustomerPayload> payloadService = new KafkaPayloadServiceImpl<>();

  @Test
  void testgeneratePayLoadWhenCalledWithValidArgumentShouldReturnJsonStringOfCustomerQueuePayLoadModel() {

    // Given
    String ActivityId = UUID.randomUUID().toString();
    String TransactionalId = UUID.randomUUID().toString();
    CustomerRequest customerResponse = new CustomerRequest();
    CustomerPayload payload = new CustomerPayload(ActivityId, TransactionalId, customerResponse);

    // When
    GenericKafkaEvent<CustomerPayload> actual = payloadService.generateSucessPayload(payload);

    // Then
    GenericKafkaEvent<CustomerPayload> queuePayload = new GenericKafkaEvent<>();
    queuePayload.setCustomerPayload(payload);

    GenericKafkaEvent<CustomerPayload> expected = queuePayload;
    assertEquals(expected, actual);


  }

}
