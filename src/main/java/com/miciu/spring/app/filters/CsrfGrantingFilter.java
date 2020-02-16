package com.miciu.spring.app.filters;

import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsrfGrantingFilter extends OncePerRequestFilter {

  static final String CSRF_COOKIE_NAME = "XSRF-SPRING-REACT-APP-TOKEN";
  static final String CSRF_HEADER_NAME = "X-XSRF-SPRING-REACT-APP-TOKEN";
  private static final String READ_METHOD = "GET";
  private static final AntPathRequestMatcher ENDPOINT_PATH_MATCHER = new AntPathRequestMatcher("/employee/**/*");
  private final CookieCsrfTokenRepository tokenRepository;

  public CsrfGrantingFilter(CookieCsrfTokenRepository tokenRepository) {
    Assert.notNull(tokenRepository, "csrfTokenRepository cannot be null");
    this.tokenRepository = tokenRepository;
    this.tokenRepository.setCookieName(CSRF_COOKIE_NAME);
    this.tokenRepository.setHeaderName(CSRF_HEADER_NAME);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    if (READ_METHOD.equals(request.getMethod()) && isNotEndpointPath(request)) {
      request.setAttribute(HttpServletResponse.class.getName(), response);

      CsrfToken csrfToken = this.tokenRepository.loadToken(request);
      final boolean missingToken = csrfToken == null;
      if (missingToken) {
        csrfToken = this.tokenRepository.generateToken(request);
        this.tokenRepository.saveToken(csrfToken, request, response);
      }
      request.setAttribute(CsrfToken.class.getName(), csrfToken);
      request.setAttribute(csrfToken.getParameterName(), csrfToken);
    }
    filterChain.doFilter(request, response);
  }

  private boolean isNotEndpointPath(HttpServletRequest request) {
    return !(ENDPOINT_PATH_MATCHER.matches(request) );
  }
}
