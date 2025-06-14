/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.controllers;

import static com.trip_planner.app.utils.Constants.Controllers.BASE_URI;

import com.trip_planner.app.models.context.ApplicationContext;
import com.trip_planner.app.models.dtos.UserFormDto;
import com.trip_planner.app.services.AuthorizationService;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BASE_URI + "/auth")
public class AuthorizationController {

  private final AuthorizationService authorizationService;
  private final ApplicationContext applicationContext;
  private final ServiceResponse serviceResponse;

  public AuthorizationController(
      AuthorizationService authorizationService, ApplicationContext applicationContext) {
    this.authorizationService = authorizationService;
    this.applicationContext = applicationContext;
    this.serviceResponse = new ServiceResponse(applicationContext);
  }

  @PostMapping("/login")
  public ResponseEntity<ServiceResponse> login(
      @RequestBody UserFormDto formDetails, HttpServletResponse response) {
    var isValidLogin = authorizationService.isValidLogin(formDetails, response);
    return new ResponseEntity<>(
        serviceResponse.getResponse(isValidLogin, null),
        isValidLogin ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
  }

  @PostMapping("/register")
  public ResponseEntity<ServiceResponse> register(
      @RequestBody UserFormDto formDetails, HttpServletResponse response) {
    var userId = authorizationService.registerUser(formDetails, response);
    return new ResponseEntity<>(
        serviceResponse.getResponse(Objects.isNull(userId), userId),
        Objects.isNull(userId) ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
  }
}
