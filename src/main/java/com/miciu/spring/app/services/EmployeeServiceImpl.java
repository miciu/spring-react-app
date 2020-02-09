package com.miciu.spring.app.services;

import com.miciu.spring.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import static java.util.stream.Collectors.*;
import static java.util.stream.StreamSupport.stream;

public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository repository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Employee> readEmployees() {
    return stream(repository.findOlderThan(40).spliterator(), false)
        .collect(toList());
  }

  @Override
  public void addEmployee(Employee employee) {
    repository.save(employee);
  }
}
