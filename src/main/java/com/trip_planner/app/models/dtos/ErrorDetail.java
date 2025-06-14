package com.trip_planner.app.models.dtos;

import com.trip_planner.app.models.enums.ErrorType;
import com.trip_planner.app.models.enums.Severity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorDetail {
    private ErrorType errorType;
    private String message;
    private Severity severity;

    @Override
    public String toString() {
        return "[" + getSeverity() + "] " + getErrorType() + ": " + getMessage();
    }

}
