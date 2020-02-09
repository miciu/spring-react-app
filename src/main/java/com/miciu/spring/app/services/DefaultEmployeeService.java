package com.miciu.spring.app.services;

import com.miciu.spring.app.model.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class DefaultEmployeeService implements EmployeeService {
  @Value("${employee.first.name}")
  private String firstName;
  @Value("${employee.last.name}")
  private String lastName;
  @Value("${employee.age}")
  private int age;

  @Override
  public List<EmployeeDto> readEmployees() {
    EmployeeDto employee = new EmployeeDto(firstName, lastName, age);

    return Arrays.asList(employee);
  }

  @Override
  public void addEmployee(EmployeeDto employee) {
    log.info("Successfully added employee: {}", employee);
  }
}
