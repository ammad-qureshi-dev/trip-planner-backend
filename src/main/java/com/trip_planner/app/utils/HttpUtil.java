/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class HttpUtil {

  private static final List<String> UNAUTHORIZED_ENDPOINTS =
      List.of("/login", "/register", "/is-unique-email", "is-email-verified");

  public Optional<Object> getValueFromCookies(HttpServletRequest request, String key) {
    if (Objects.isNull(request)) {
      return Optional.empty();
    }

    var cookies = request.getCookies();

    for (var cookie : cookies) {
      if (key.equals(cookie.getName())) {
        return Optional.of(cookie.getValue());
      }
    }

    return Optional.empty();
  }

  public static void addCookieToResponse(String key, String value, HttpServletResponse response) {
    var cookie = new Cookie(key, value);
    cookie.setMaxAge(60 * 60);
    cookie.setPath("/");
    response.addCookie(cookie);
  }

  public static boolean isEndpointUnauthorized(HttpServletRequest request) {
    final var rawPath = request.getRequestURI();
    final var path = rawPath.substring(Constants.Controllers.BASE_URI.length());
    final var endpoint = path.substring(path.indexOf("/"));
    return UNAUTHORIZED_ENDPOINTS.contains(endpoint);
  }
}
