package com.trip_planner.app.models.context;

import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ApplicationContext {
    private final Map<ReferenceKey<?>, Object> context = new ConcurrentHashMap<>();

    public <T> void put(ReferenceKey<T> key, T object) {
        Type type = key.getType();

        // Only check type if it's a Class (non-generic)
        if (type instanceof Class<?>) {
            Class<?> clazz = (Class<?>) type;
            if (!clazz.isInstance(object)) {
                throw new IllegalArgumentException(
                        "Object of type " + object.getClass() + " is not of type " + clazz
                );
            }
        }

        context.put(key, object);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(ReferenceKey<T> key) {
        return (T) context.get(key);
    }

    public void clearContext() {
        context.clear();
    }

}
