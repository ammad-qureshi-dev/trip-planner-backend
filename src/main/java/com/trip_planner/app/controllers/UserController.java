/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.controllers;

import static com.trip_planner.app.utils.Constants.Controllers.BASE_URI;

import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BASE_URI + "/user")
public class UserController {

  @GetMapping("/{userId}")
  public ResponseEntity<ServiceResponse> getUserById(UUID userID) {
    return null;
  }
}
