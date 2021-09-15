package com.pk.engineering.publisher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.pk.engineering.publisher.model.CustomerQueuePayload;
import com.pk.engineering.publisher.model.Request;
import com.pk.engineering.publisher.service.CustomerQueuePayloadService;
import com.pk.engineering.publisher.util.ObjectMapperUtil;


class PayloadServiceTest{
	    
	private CustomerQueuePayloadService payloadService = new CustomerQueuePayloadService();
	
	@Test
	void testgeneratePayLoadWhenCalledWithValidArgumentShouldReturnJsonStringOfCustomerQueuePayLoadModel() {

	    // Given   
		UUID ActivityId = UUID.randomUUID();
		UUID TransactionalId = UUID.randomUUID();
		Request customerResponse = new Request();	
		
	    // When
		String actual =  payloadService.generatePayload(ActivityId, TransactionalId, customerResponse);
	    
	    // Then
		String expected = ObjectMapperUtil.writeValueAsString(new CustomerQueuePayload(ActivityId, TransactionalId, customerResponse));
	    assertEquals(expected,actual);

	    
	}

}
		