/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.models.enums;

public enum ErrorType {
  USER_ALREADY_EXISTS,
  USER_DOES_NOT_EXIST,
  REQUIRED_FIELDS_MISSING,
  EMAIL_PASSWORD_NOT_PROVIDED,
  INCORRECT_EMAIL_PASSWORD_COMBINATION,
  TOKEN_NOT_PROVIDED,
  TOKEN_EXPIRED;
}
