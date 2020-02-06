package com.miciu.spring.app.services;

import com.miciu.spring.app.model.Employee;

import java.util.Arrays;
import java.util.List;

public class DefaultEmployeeService implements EmployeeService {
  private String firstName;
  private String lastName;
  private int age;

  @Override
  public List<Employee> readEmployees() {
    Employee employee = new Employee(firstName, lastName, age);

    return Arrays.asList(employee);
  }
}
