/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.utils;

import com.trip_planner.app.models.User;
import com.trip_planner.app.models.dtos.ErrorDetail;
import com.trip_planner.app.models.enums.ErrorType;
import com.trip_planner.app.models.enums.Severity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Slf4j
@Configuration
public class JwtUtil {

  @Value("${JWT_SECRET}")
  private String JWT_SECRET;

  public String generateToken(User user) {
    var tokenExpirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60);

    return Jwts.builder()
        .setIssuer("trip-planner-app")
        .setSubject(user.getId().toString())
        .claim("email", user.getEmail())
        .claim("fullName", user.getFullName())
        .setExpiration(tokenExpirationDate)
        .signWith(SignatureAlgorithm.HS256, JWT_SECRET.getBytes())
        .setIssuedAt(new Date())
        .compact();
  }

  public boolean isTokenExpired(String token) {
    if (!StringUtils.hasLength(token)) {
      log.debug(
          ErrorDetail.builder()
              .errorType(ErrorType.TOKEN_NOT_PROVIDED)
              .severity(Severity.FATAL)
              .build()
              .toString());
      return true;
    }

    var claims = parseToken(token);
    var isExpired = claims.getExpiration().before(new Date());
    log.debug(
        "{}",
        isExpired
            ? ErrorDetail.builder()
                .errorType(ErrorType.TOKEN_EXPIRED)
                .severity(Severity.FATAL)
                .build()
                .toString()
            : "");
    return isExpired;
  }

  private Claims parseToken(String token) {
    return Jwts.parser().setSigningKey(JWT_SECRET.getBytes()).parseClaimsJws(token).getBody();
  }
}
