package com.trip_planner.app.models.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserFormDto {
    private String email;
    private String password;
    private String fullName;
}
