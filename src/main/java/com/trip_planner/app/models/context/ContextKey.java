package com.trip_planner.app.models.context;

import com.trip_planner.app.models.dtos.ErrorDetail;

import java.util.List;

public class ContextKey {
    public static class General {
        public static final ReferenceKey<List<ErrorDetail>> APPLICATION_ERRORS =
                new ReferenceKey<>("APPLICATION_ERRORS", new TypeReference<List<ErrorDetail>>() {}.getType());

    }
}
