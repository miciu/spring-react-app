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
    http.antMatcher("/api/**/*").authorizeRequests()
        //TODO secure path: /api
        .antMatchers(GET, "/api/**/*").permitAll()
        .antMatchers(POST, "/api/**/*").permitAll()
        .antMatchers(PUT, "/api/**/*").permitAll()
        .anyRequest()
        .authenticated();
  }
}