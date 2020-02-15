package com.miciu.spring.configuration;

import com.miciu.spring.app.resolvers.CurrentUserResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(currentUserResolver());
  }

  @Bean
  public HandlerMethodArgumentResolver currentUserResolver() {
    return new CurrentUserResolver();
  }
}
