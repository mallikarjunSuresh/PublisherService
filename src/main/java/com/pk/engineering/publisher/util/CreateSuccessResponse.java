package com.pk.engineering.publisher.util;

import com.pk.engineering.publisher.model.SuccessResponse;

public class CreateSuccessResponse {
  
  private CreateSuccessResponse() {

  }

  public static SuccessResponse getSucessResponse(String serviceResponse) {
    SuccessResponse response = new SuccessResponse();
    response.setMessage(serviceResponse);
    response.setStatus("success");
    return response;
  }

}
