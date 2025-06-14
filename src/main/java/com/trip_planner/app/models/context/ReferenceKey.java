/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.models.context;

import java.lang.reflect.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ReferenceKey<T> {
  private final String name;
  private final Type type;
}
