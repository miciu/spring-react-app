package com.miciu.spring.app.services;

import com.miciu.spring.app.mapper.EmployeeMapper;
import com.miciu.spring.app.model.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.stream.Collectors.*;
import static java.util.stream.StreamSupport.stream;

public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeMapper mapper;
  private final EmployeeRepository repository;

  @Autowired
  public EmployeeServiceImpl(EmployeeMapper mapper,
                             EmployeeRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  @Override
  public List<EmployeeDto> readEmployees() {
    return stream(repository.findAll().spliterator(), false)
        .map(employeeEntity -> mapper.map(employeeEntity))
        .collect(toList());
  }

  @Override
  public void addEmployee(EmployeeDto employee) {
    repository.save(mapper.map(employee));
  }
}
