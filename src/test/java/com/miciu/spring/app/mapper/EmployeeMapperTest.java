package com.miciu.spring.app.mapper;

import com.miciu.spring.app.entity.EmployeeEntity;
import com.miciu.spring.app.model.EmployeeDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeMapperTest {

  private EmployeeMapper mapper;

  @Before
  public void before() {
    MapperFacade mapperFacade = buildMapper();
    mapper = new EmployeeMapper(mapperFacade);
  }

  @Test
  public void should_map_employee_entity_to_employee_dto() {
    //given
    EmployeeEntity givenEmployeeEntity = new EmployeeEntity("Jan", "Kowalski", 1978);

    //when
    EmployeeDto result = mapper.map(givenEmployeeEntity);

    //then
    assertThat(result.getFirstName()).isEqualTo(givenEmployeeEntity.getFirstName());
    assertThat(result.getLastName()).isEqualTo(givenEmployeeEntity.getLastName());
    assertThat(result.getAge()).isEqualTo(42);

  }

  @Test
  public void should_map_employee_dto_to_employee_entity() {
    //given
    EmployeeDto giEmployeeDto = new EmployeeDto("Jan", "Kowalski", 42);

    //when
    EmployeeEntity result = mapper.map(giEmployeeDto);

    //then
    assertThat(result.getFirstName()).isEqualTo(giEmployeeDto.getFirstName());
    assertThat(result.getLastName()).isEqualTo(giEmployeeDto.getLastName());
    assertThat(result.getBirthYear()).isEqualTo(1978);

  }

  private MapperFacade buildMapper() {
    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    EmployeeMapper.Configurer.configure(mapperFactory);
    return mapperFactory.getMapperFacade();
  }

}