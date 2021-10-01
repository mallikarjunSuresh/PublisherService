package com.pk.engineering.publisher.model;

import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-08-30T16:48:56.008Z[GMT]")


public class CustomerRequest {
  @JsonProperty("customerNumber")
  private String customerNumber = null;

  @JsonProperty("fistName")
  private String fistName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("birthDate")
  private String birthDate = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("countryCode")
  private Integer countryCode = null;

  @JsonProperty("mobileNumber")
  private Long mobileNumber = null;

  @JsonProperty("email")
  private String email = null;

  /**
   * Gets or Sets customerStatus
   */
  public enum CustomerStatusEnum {
    OPEN("O"),

    CLOSE("C"),

    SUSPENDED("S"),

    RESTORED("R");

    private String value;

    CustomerStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CustomerStatusEnum fromValue(String text) {
      for (CustomerStatusEnum b : CustomerStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("customerStatus")
  private CustomerStatusEnum customerStatus = null;

  @JsonProperty("address")
  private CustomerAddress address = null;

  public CustomerRequest customerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
    return this;
  }

  /**
   * Get customerNumber
   * 
   * @return customerNumber
   **/
  @Schema(required = true, description = "")
  @NotNull

  @Pattern(regexp = "^C[0-9]+$")
  @Size(min = 10, max = 10)
  public String getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
  }

  public CustomerRequest fistName(String fistName) {
    this.fistName = fistName;
    return this;
  }

  /**
   * Get fistName
   * 
   * @return fistName
   **/
  @Schema(description = "")

  @Size(min = 10, max = 50)
  public String getFistName() {
    return fistName;
  }

  public void setFistName(String fistName) {
    this.fistName = fistName;
  }

  public CustomerRequest lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * 
   * @return lastName
   **/
  @Schema(required = true, description = "")
  @NotNull

  @Size(min = 10, max = 50)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public CustomerRequest birthDate(String birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  /**
   * Get birthDate
   * 
   * @return birthDate
   **/
  @Schema(required = true, description = "")
  @NotNull

  @Valid
  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public CustomerRequest country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * 
   * @return country
   **/
  @Schema(required = true, description = "")
  @NotNull

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public CustomerRequest countryCode(Integer countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * Get countryCode
   * 
   * @return countryCode
   **/
  @Schema(required = true, description = "")
  @NotNull

  public Integer getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(Integer countryCode) {
    this.countryCode = countryCode;
  }

  public CustomerRequest mobileNumber(Long mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  /**
   * Get mobileNumber
   * 
   * @return mobileNumber
   **/
  @Schema(required = true, description = "")
  @NotNull

  public Long getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(Long mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public CustomerRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * 
   * @return email
   **/
  @Schema(required = true, description = "")
  @NotNull

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public CustomerRequest customerStatus(CustomerStatusEnum customerStatus) {
    this.customerStatus = customerStatus;
    return this;
  }

  /**
   * Get customerStatus
   * 
   * @return customerStatus
   **/
  @Schema(required = true, description = "")
  @NotNull

  public CustomerStatusEnum getCustomerStatus() {
    return customerStatus;
  }

  public void setCustomerStatus(CustomerStatusEnum customerStatus) {
    this.customerStatus = customerStatus;
  }

  public CustomerRequest address(CustomerAddress address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * 
   * @return address
   **/
  @Schema(description = "")

  @Valid
  public CustomerAddress getAddress() {
    return address;
  }

  public void setAddress(CustomerAddress address) {
    this.address = address;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerRequest request = (CustomerRequest) o;
    return Objects.equals(this.customerNumber, request.customerNumber)
        && Objects.equals(this.fistName, request.fistName)
        && Objects.equals(this.lastName, request.lastName)
        && Objects.equals(this.birthDate, request.birthDate)
        && Objects.equals(this.country, request.country)
        && Objects.equals(this.countryCode, request.countryCode)
        && Objects.equals(this.mobileNumber, request.mobileNumber)
        && Objects.equals(this.email, request.email)
        && Objects.equals(this.customerStatus, request.customerStatus)
        && Objects.equals(this.address, request.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerNumber, fistName, lastName, birthDate, country, countryCode,
        mobileNumber, email, customerStatus, address);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Request {\n");

    sb.append("    customerNumber: ").append(toIndentedString(customerNumber)).append("\n");
    sb.append("    fistName: ").append(toIndentedString(fistName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    mobileNumber: ").append(toIndentedString(mobileNumber)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    customerStatus: ").append(toIndentedString(customerStatus)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
