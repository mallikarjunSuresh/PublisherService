package com.pk.engineering.publisher;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import com.pk.engineering.publisher.model.CustomerAddress;
import com.pk.engineering.publisher.model.CustomerRequest;
import com.pk.engineering.publisher.model.CustomerRequest.CustomerStatusEnum;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CustomerITest {

  private String oauth_token;

  @MockBean
  private KafkaTemplate<String, Object> kafkaTemplate;


  @BeforeEach
  public void setup() {
    oauth_token = RestAssured.given().auth().basic("clientId", "123456")
        .queryParams("grant_type", "client_credentials").when()
        .post("http://localhost:8080/oauth/token").then().extract().response().jsonPath()
        .getString("access_token");
  }

  @Test
  void testCustomerPostMethodWhenCalledWithValidRequestShouldReturnSucessResponse() {
    
    CustomerRequest customerRequest = createDefCustomerReq();

    ListenableFuture<SendResult<String, Object>> responseFuture = mock(ListenableFuture.class);
    when(kafkaTemplate.send(Mockito.any(), Mockito.any())).thenReturn(responseFuture);
    
    RestAssured.given().header("Authorization", "Bearer " + oauth_token)
        .header("Transaction-Id", "1979c1ce-0fc1-11ec-82a8-0242ac130003")
        .header("Activity-Id", "1ff79f94-0fc1-11ec-82a8-0242ac130003")
        .header("Content-Type", "application/json").header("Accept", "application/json").with()
        .body(customerRequest).when().request("POST", "v1/customer").then().assertThat()
        .statusCode(200).body("status", Matchers.equalTo("success"));

  }

  @Test
  void testCustomerPostMethodWhenCalledWithInvalidRequestShouldReturnInvalidRequestFailureResponse() {

    CustomerRequest customerRequest = createDefCustomerReq();
    customerRequest.setCustomerNumber("C00000000");
    
    ListenableFuture<SendResult<String, Object>> responseFuture = mock(ListenableFuture.class);
    when(kafkaTemplate.send(Mockito.any(), Mockito.any())).thenReturn(responseFuture);
    

    RestAssured.given().header("Authorization", "Bearer " + oauth_token)
        .header("Transaction-Id", "1979c1ce-0fc1-11ec-82a8-0242ac130003")
        .header("Activity-Id", "1ff79f94-0fc1-11ec-82a8-0242ac130003")
        .header("Content-Type", "application/json").header("Accept", "application/json").with()
        .body(customerRequest).when().request("POST", "v1/customer").then().assertThat()
        .statusCode(400).body("status", Matchers.equalTo("failed"))
        .body("errorType", Matchers.equalTo("Invalid Request Exception"));

  }

  @Test
  void testCustomerPostMethodWhenCalledWithInvalidHeaderShouldReturnInvalidHeaderFailureResponse() {

    CustomerRequest customerRequest = createDefCustomerReq();
    
    ListenableFuture<SendResult<String, Object>> responseFuture = mock(ListenableFuture.class);
    when(kafkaTemplate.send(Mockito.any(), Mockito.any())).thenReturn(responseFuture);

    RestAssured.given().header("Authorization", "Bearer " + oauth_token)
        .header("Activity-Id", "1ff79f94-0fc1-11ec-82a8-0242ac130003")
        .header("Content-Type", "application/json").header("Accept", "application/json").with()
        .body(customerRequest).when().request("POST", "v1/customer").then().assertThat()
        .statusCode(400).body("status", Matchers.equalTo("failed"))
        .body("errorType", Matchers.equalTo("Invalid Header Exception"));

  }

  @Test
  void testCustomerPostMethodWhenCalledWithInvalidPathShouldReturnInvalidHandlerFailureResponse() {

    CustomerRequest customerRequest = createDefCustomerReq();
    
    ListenableFuture<SendResult<String, Object>> responseFuture = mock(ListenableFuture.class);
    when(kafkaTemplate.send(Mockito.any(), Mockito.any())).thenReturn(responseFuture);

    RestAssured.given().header("Authorization", "Bearer " + oauth_token)
        .header("Transaction-Id", "1979c1ce-0fc1-11ec-82a8-0242ac130003")
        .header("Activity-Id", "1ff79f94-0fc1-11ec-82a8-0242ac130003")
        .header("Content-Type", "application/json").header("Accept", "application/json").with()
        .body(customerRequest).when().request("POST", "v1/wrongMapping").then().assertThat()
        .statusCode(404).body("status", Matchers.equalTo("failed"))
        .body("errorType", Matchers.equalTo("Invalid Handler Exception"));

  }

  private CustomerRequest createDefCustomerReq() {

    CustomerRequest customerRequest = new CustomerRequest();
    CustomerAddress address = new CustomerAddress();
    address.setAddressLine1("Guindy");
    address.setPostalCode("12345");
    customerRequest.setCustomerNumber("C000000001");
    customerRequest.setAddress(address);
    customerRequest.setBirthDate("2015-05-16T05:50:06");
    customerRequest.setCountry("India");
    customerRequest.setCountryCode(21);
    customerRequest.setCustomerStatus(CustomerStatusEnum.OPEN);
    customerRequest.setEmail("arjun@gmail.com");
    customerRequest.setFistName("Mallikarjun");
    customerRequest.setLastName("SureshKumar");
    customerRequest.setMobileNumber(1234567899L);

    return customerRequest;
  }

}
