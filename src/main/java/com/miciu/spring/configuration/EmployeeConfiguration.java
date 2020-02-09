package com.miciu.spring.configuration;

import com.miciu.spring.app.services.DefaultEmployeeService;
import com.miciu.spring.app.services.EmployeeRepository;
import com.miciu.spring.app.services.EmployeeService;
import com.miciu.spring.app.services.EmployeeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class EmployeeConfiguration {

  @Bean
  @Profile("local")
  public EmployeeService defaultEmployeeService() {
    return new DefaultEmployeeService();
  }

  @Bean
  @Profile("prod")
  public EmployeeService employeeServiceImpl(EmployeeRepository repository) {
    return new EmployeeServiceImpl(repository);
  }

}
