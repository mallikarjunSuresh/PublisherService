package com.pk.engineering.Publisher.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FailureResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-30T16:48:56.008Z[GMT]")


public class FailureResponse   {
  @JsonProperty("status")
  private String status = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("errorType")
  private String errorType = null;

  public FailureResponse status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public FailureResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public FailureResponse errorType(String errorType) {
    this.errorType = errorType;
    return this;
  }

  /**
   * Get errorType
   * @return errorType
   **/
  @Schema(description = "")
  
    public String getErrorType() {
    return errorType;
  }

  public void setErrorType(String errorType) {
    this.errorType = errorType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FailureResponse failureResponse = (FailureResponse) o;
    return Objects.equals(this.status, failureResponse.status) &&
        Objects.equals(this.message, failureResponse.message) &&
        Objects.equals(this.errorType, failureResponse.errorType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, message, errorType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FailureResponse {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    errorType: ").append(toIndentedString(errorType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
