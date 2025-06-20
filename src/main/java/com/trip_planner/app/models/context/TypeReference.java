/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.models.context;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeReference<T> {
  private final Type type;

  protected TypeReference() {
    Type superclass = getClass().getGenericSuperclass();
    if (superclass instanceof ParameterizedType) {
      this.type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
    } else {
      throw new RuntimeException("Missing type parameter.");
    }
  }

  public Type getType() {
    return this.type;
  }
}
