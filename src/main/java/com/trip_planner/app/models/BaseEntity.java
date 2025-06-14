/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.models;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

  @CreationTimestamp private LocalDateTime createdAt;

  @UpdateTimestamp private LocalDateTime lastUpdatedOn;
}
