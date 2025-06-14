package com.trip_planner.app.services;

import com.trip_planner.app.models.User;
import com.trip_planner.app.models.context.ApplicationContext;
import com.trip_planner.app.models.dtos.ErrorDetail;
import com.trip_planner.app.models.dtos.UserFormDto;
import com.trip_planner.app.models.enums.Severity;
import com.trip_planner.app.respositories.AuthorizationRepository;
import com.trip_planner.app.respositories.UserRepository;
import com.trip_planner.app.utils.HashingUtil;
import com.trip_planner.app.utils.HttpUtil;
import com.trip_planner.app.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static com.trip_planner.app.models.context.ContextKey.General.APPLICATION_ERRORS;
import static com.trip_planner.app.models.enums.ErrorType.*;
import static com.trip_planner.app.utils.Constants.General.TOKEN;

@Component
public class AuthorizationService {
    private final AuthorizationRepository authorizationRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final ApplicationContext applicationContext;

    public AuthorizationService(AuthorizationRepository authorizationRepository, JwtUtil jwtUtil, UserRepository userRepository, ApplicationContext applicationContext) {
        this.authorizationRepository = authorizationRepository;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.applicationContext = applicationContext;
    }

    public boolean isValidLogin(UserFormDto formDetails, HttpServletResponse response) {
        var applicationErrors = new ArrayList<ErrorDetail>();

        if (!isFormFieldsValid(formDetails, true)) {
            applicationErrors.add(ErrorDetail.builder().errorType(EMAIL_PASSWORD_NOT_PROVIDED).message("Email or Password not provided").severity(Severity.WARNING).build());
            applicationContext.put(APPLICATION_ERRORS, applicationErrors);
            return false;
        }

        var hashedPassword = HashingUtil.generateHashedInput(formDetails.getPassword());
        var fetchedUser = userRepository.findByEmail(formDetails.getEmail());

        if (fetchedUser.isEmpty()) {
            applicationErrors.add(ErrorDetail.builder().errorType(USER_DOES_NOT_EXIST).message("User not found").severity(Severity.WARNING).build());
            applicationContext.put(APPLICATION_ERRORS, applicationErrors);
            return false;
        }

        var user = fetchedUser.get();
        if (!hashedPassword.equals(user.getPassword())) {
            applicationErrors.add(ErrorDetail.builder().errorType(INCORRECT_EMAIL_PASSWORD_COMBINATION).message("Incorrect Email and Password combination").severity(Severity.FATAL).build());
            applicationContext.put(APPLICATION_ERRORS, applicationErrors);
            return false;
        }

        var token = jwtUtil.generateToken(user);
        HttpUtil.addCookieToResponse(TOKEN, token, response);
        return true;
    }

    @Transactional
    public UUID registerUser(UserFormDto userFormDto, HttpServletResponse response) {
        final var applicationErrors = new ArrayList<ErrorDetail>();

        if (!isFormFieldsValid(userFormDto, false)) {
            applicationErrors.add(ErrorDetail.builder().errorType(REQUIRED_FIELDS_MISSING).message("Please fill in all required fields").severity(Severity.WARNING).build());
            applicationContext.put(APPLICATION_ERRORS, applicationErrors);
            return null;
        }

        var hashedPassword = HashingUtil.generateHashedInput(userFormDto.getPassword());
        var fetchedUser = userRepository.findByEmail(userFormDto.getEmail());

        if (fetchedUser.isPresent()) {
            applicationErrors.add(ErrorDetail.builder().errorType(USER_ALREADY_EXISTS).message("User not found").severity(Severity.WARNING).build());
            applicationContext.put(APPLICATION_ERRORS, applicationErrors);
            return null;
        }


        var user = User.builder().email(userFormDto.getEmail()).password(hashedPassword).fullName(userFormDto.getFullName()).build();
        userRepository.save(user);

        var token = jwtUtil.generateToken(user);
        HttpUtil.addCookieToResponse(TOKEN, token, response);
        return user.getId();
    }

    private boolean isFormFieldsValid(UserFormDto formDetails, boolean isLoginRequest) {
        var isAuthFieldsFilled = !(Objects.isNull(formDetails.getEmail()) && Objects.isNull(formDetails.getPassword()));
        if (isLoginRequest) {
            return isAuthFieldsFilled;
        }

        return isAuthFieldsFilled && !Objects.isNull(formDetails.getFullName());
    }
}
