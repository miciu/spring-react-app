package com.miciu.spring.app.services;

import com.miciu.spring.app.model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {

  List<Employee> readEmployees() throws IOException;
}
