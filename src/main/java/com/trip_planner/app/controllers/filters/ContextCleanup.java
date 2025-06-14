/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.controllers.filters;

import com.trip_planner.app.models.context.ApplicationContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class ContextCleanup extends OncePerRequestFilter {

  private final ApplicationContext applicationContext;

  public ContextCleanup(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } finally {
      log.debug("Clearing application context");
      applicationContext.clearContext();
    }
  }
}
