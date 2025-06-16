/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.models;

import com.trip_planner.app.models.enums.AlertType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alert")
public class Alert {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private AlertType alertType;
  private String description;

  @ManyToOne private User user;

  private Object formData;
  private LocalDateTime dateSent;
}
