package com.trip_planner.app.exceptions;

public class HttpException extends RuntimeException {
    public HttpException(String message) {
        super(message);
    }
}