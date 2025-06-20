/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.services;

import com.trip_planner.app.respositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
