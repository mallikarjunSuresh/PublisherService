package com.pk.engineering.publisher.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.pk.engineering.publisher.model.CustomerRequest;

@Service
public class CustomerMaskingService implements MaskingService {

  @Override
  public CustomerRequest doMasking(CustomerRequest customerRequest) {

    CustomerRequest maskedCustomerRequest = new CustomerRequest();
    BeanUtils.copyProperties(customerRequest, maskedCustomerRequest);
    maskedCustomerRequest.setCustomerNumber(
    customerRequest.getCustomerNumber().replaceAll(CUSTOMERID_MASK, "*"));
    maskedCustomerRequest.setBirthDate(
    customerRequest.getBirthDate().replaceAll(BIRTHDATE_MASK, "**-**"));
    maskedCustomerRequest.setEmail(customerRequest.getEmail().replaceAll(EMAIL_MASK, "*"));
    return maskedCustomerRequest;
    
  }
}
