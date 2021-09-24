package com.pk.engineering.publisher;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import com.pk.engineering.publisher.service.KafkaPublisherService;

@SpringBootTest
class PublisherServiceTest {

  @Mock
  private KafkaTemplate<String, Object> kafkaTemplate;

  @InjectMocks
  private KafkaPublisherService publisherService;

  @Test
  void testPublishMessageWhenTopicAndMessageIsPassedShouldSendMessageToKafka() {

    // Given
    ListenableFuture<SendResult<String, Object>> responseFuture = mock(ListenableFuture.class);
    when(kafkaTemplate.send("customer", "payload")).thenReturn(responseFuture);
    
    // When
    publisherService.publishMessage("customer", "payload");

    // Then
    verify(kafkaTemplate).send("customer", "payload");
  }
}
