package org.acme.filters;


public class ErrorResponse {
  private int code; // 400
  private String message; // example "Invalid value at foobar"
  private String status; // "INVALID_ARGUMENT"

  public ErrorResponse(int code, String message, String status) {
    this.code = code;
    this.message = message;
    this.status = status;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}