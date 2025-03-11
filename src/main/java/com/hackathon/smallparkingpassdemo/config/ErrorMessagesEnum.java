package com.hackathon.smallparkingpassdemo.config;

public enum ErrorMessagesEnum {
    AUTHENTICATION_USER_NOT_FOUND("Error S0090: Authentication failed."), // User not authenticated in userDetailsService
    REQUEST_PROHIBITED("Error S0100: Request is prohibited."), // Missing header for HeaderFilter, not authenticated
    REQUEST_NOT_FROM_ROBOT("Error S0110: Request is prohibited."); // Missing header for ActuatorRobotFilter, not a robot

 private final String message;

 ErrorMessagesEnum(String message) {
     this.message = message;
 }

 public String getMessage() {
     return message;
 }
}
