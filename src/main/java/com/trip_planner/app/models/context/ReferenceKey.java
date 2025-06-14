package com.trip_planner.app.models.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Type;

@Data
@AllArgsConstructor
@Builder
public class ReferenceKey<T> {
    private final String name;
    private final Type type;
}
