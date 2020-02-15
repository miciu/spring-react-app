package com.miciu.spring.app.mapper;

import com.miciu.spring.app.entity.EmployeeEntity;
import com.miciu.spring.app.mapper.converters.SectorToProfessionConverter;
import com.miciu.spring.app.model.EmployeeDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;

import static java.time.temporal.ChronoField.*;

@Service
public class EmployeeMapper {
  private static final ZoneId ZONE = ZoneId.of("Europe/Warsaw");
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
      mapperFactory.getConverterFactory().registerConverter(new SectorToProfessionConverter());
           
      mapperFactory.classMap(EmployeeEntity.class, EmployeeDto.class)
          .byDefault()
          .field("profession", "sector")
          .customize(new CustomMapper<EmployeeEntity, EmployeeDto>() {
            @Override
            public void mapAtoB(EmployeeEntity employeeEntity, EmployeeDto employeeDto, MappingContext context) {
              super.mapAtoB(employeeEntity, employeeDto, context);

              int currentYear = Instant.now().atZone(ZONE).get(YEAR);
              int age = currentYear - employeeEntity.getBirthYear();
              employeeDto.setAge(age);
            }

            @Override
            public void mapBtoA(EmployeeDto employeeDto, EmployeeEntity employeeEntity, MappingContext context) {
              super.mapBtoA(employeeDto, employeeEntity, context);

              int currentYear = Instant.now().atZone(ZONE).get(YEAR);
              int birthYear = currentYear - employeeDto.getAge();
              employeeEntity.setBirthYear(birthYear);
            }
          })
          .register();
    }
  }
}
