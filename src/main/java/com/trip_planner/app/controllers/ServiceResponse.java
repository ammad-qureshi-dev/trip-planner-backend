/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.controllers;

import static com.trip_planner.app.models.context.ContextKey.General.APPLICATION_ERRORS;

import com.trip_planner.app.models.context.ApplicationContext;
import com.trip_planner.app.models.dtos.ErrorDetail;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class ServiceResponse {
  private boolean success;
  private List<ErrorDetail> errors;
  private Object data;

  @Getter(AccessLevel.NONE)
  private ApplicationContext applicationContext;

  public ServiceResponse(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  public ServiceResponse getResponse(boolean success, Object data) {
    var applicationErrors = applicationContext.get(APPLICATION_ERRORS);

    return ServiceResponse.builder()
        .success(success)
        .errors(Objects.isNull(applicationErrors) ? Collections.emptyList() : applicationErrors)
        .data(data)
        .build();
  }

  public ServiceResponse getResponse(Object data) {
    var applicationErrors = applicationContext.get(APPLICATION_ERRORS);

    return ServiceResponse.builder()
        .success(applicationErrors.isEmpty())
        .errors(applicationErrors)
        .data(data)
        .build();
  }
}
