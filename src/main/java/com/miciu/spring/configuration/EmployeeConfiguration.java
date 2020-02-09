package com.miciu.spring.configuration;

import com.miciu.spring.app.mapper.EmployeeMapper;
import com.miciu.spring.app.services.DefaultEmployeeService;
import com.miciu.spring.app.services.EmployeeRepository;
import com.miciu.spring.app.services.EmployeeService;
import com.miciu.spring.app.services.EmployeeServiceImpl;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
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
  public EmployeeService employeeServiceImpl(EmployeeMapper employeeMapper, EmployeeRepository repository) {
    return new EmployeeServiceImpl(employeeMapper, repository);
  }

  @Bean
  MapperFacade mapperFacade() {
    DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    EmployeeMapper.Configurer.configure(mapperFactory);

    return mapperFactory.getMapperFacade();
  }

}
