package com.miciu.spring.configuration;

import com.miciu.spring.app.filters.CsrfGrantingFilter;
import com.miciu.spring.app.filters.CsrfValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  public static final String FILTER_ALL = "/*";
  public static final String ENDPOINT_PATH = "/employee";
    
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("admin")
        .password("{noop}admin")
        .roles("ADMIN").and().withUser("user")
        .password("{noop}user")
        .roles("USER");
  }

  @Override
  public void configure(WebSecurity web) {
    web
        .ignoring()
        .antMatchers("/employee");
  }

  @Bean
  public FilterRegistrationBean csrfGrantingFilter() {
    FilterRegistrationBean frb = new FilterRegistrationBean();
    frb.setFilter(new CsrfGrantingFilter(CookieCsrfTokenRepository.withHttpOnlyFalse()));
    frb.addUrlPatterns(FILTER_ALL);
    return frb;
  }

  @Bean
  public FilterRegistrationBean csrfValidationFilter() {
    FilterRegistrationBean frb = new FilterRegistrationBean();
    frb.setFilter(new CsrfValidationFilter().setCookiePath("/"));
    frb.addUrlPatterns(ENDPOINT_PATH + FILTER_ALL);
    return frb;
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
