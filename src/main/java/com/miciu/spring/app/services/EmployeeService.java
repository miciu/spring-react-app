package com.miciu.spring.app.services;

import com.miciu.spring.app.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {

  List<EmployeeDto> readEmployees();
  
  void addEmployee(EmployeeDto employee);
}
