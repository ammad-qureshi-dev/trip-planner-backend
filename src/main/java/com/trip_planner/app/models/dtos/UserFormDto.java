/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.models.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserFormDto {
  @Setter private String password;
  private String email;
  private String fullName;
  private boolean isVerified;
}
