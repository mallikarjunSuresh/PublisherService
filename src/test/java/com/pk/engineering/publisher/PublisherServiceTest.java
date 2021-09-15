package com.pk.engineering.publisher;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import com.pk.engineering.publisher.service.KafkaPublisherService;

@SpringBootTest
class PublisherServiceTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private KafkaPublisherService publisherService;
    
	@Test
	void testPublishMessageWhenTopicAndMessageIsPassedShouldSendMessageToKafka() {

	    // When
	    publisherService.publishMessage("customer","payload");
	    
	    // Then
	    verify(kafkaTemplate).send("customer","payload");
	}
}
