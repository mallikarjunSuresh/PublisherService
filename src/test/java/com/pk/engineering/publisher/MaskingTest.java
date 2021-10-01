package com.pk.engineering.publisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.pk.engineering.publisher.model.CustomerRequest;
import com.pk.engineering.publisher.service.CustomerMaskingService;
import com.pk.engineering.publisher.service.MaskingService;

class MaskingTest {


  private MaskingService maskService = new CustomerMaskingService();

  @Test
  void testdoMaskingWhencustomerObjectPassedWithEmailLengthGreaterThanFourShoudlMaskValuesProperly() {

    // Given
    CustomerRequest customerInputReq = new CustomerRequest();
    customerInputReq.setCustomerNumber("C000000005");
    customerInputReq.setEmail("arjun123s@gmail.com");

    // When
    CustomerRequest customerDetailOut = maskService.doMasking(customerInputReq);

    // Then
    assertEquals("C00000****", customerDetailOut.getCustomerNumber());
    assertEquals("****n123s@gmail.com", customerDetailOut.getEmail());

  }

  @Test
  void testdoMaskingWhencustomerObjectPassedWithEmailLengthLessThanFourShoudlMaskValuesProperly() {

    // Given
    CustomerRequest customerInputReq = new CustomerRequest();
    customerInputReq.setCustomerNumber("C000000005");
    customerInputReq.setEmail("mai");

    // When
    CustomerRequest customerDetailOut = maskService.doMasking(customerInputReq);

    // Then
    assertEquals("C00000****", customerDetailOut.getCustomerNumber());
    assertEquals("***", customerDetailOut.getEmail());

  }


}
