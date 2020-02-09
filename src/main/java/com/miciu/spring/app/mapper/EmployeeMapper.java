package com.miciu.spring.app.mapper;

import com.miciu.spring.app.entity.EmployeeEntity;
import com.miciu.spring.app.model.EmployeeDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
  private final MapperFacade mapperFacade;

  @Autowired
  public EmployeeMapper(MapperFacade mapperFacade) {
    this.mapperFacade = mapperFacade;
  }

  public EmployeeDto map(EmployeeEntity employeeEntity) {
    return mapperFacade.map(employeeEntity, EmployeeDto.class);
  }

  public EmployeeEntity map(EmployeeDto employeeDto) {
    return mapperFacade.map(employeeDto, EmployeeEntity.class);
  }

  public static class Configurer {

    public static void configure(final MapperFactory mapperFactory) {
      mapperFactory.classMap(EmployeeEntity.class, EmployeeDto.class)
          .register();
    }
  }
}
