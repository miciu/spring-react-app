package com.miciu.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;


@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .antMatcher("/api/**/*")
        .authorizeRequests()
        .antMatchers(GET, "/api/**/*").access("#oauth2.hasScope('read')")
        .antMatchers(POST, "/api/**/*").access("#oauth2.hasScope('write')")
        .antMatchers(PUT, "/api/**/*").access("#oauth2.hasScope('write')")
        .anyRequest().authenticated();
  }
}