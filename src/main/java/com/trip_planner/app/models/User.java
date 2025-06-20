/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.models;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "TRIP_PLANNER")
public class User extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String fullName;
  private String email;
  private String password;
  private boolean isVerified;
}
