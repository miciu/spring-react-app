package com.miciu.spring.app.services;

import com.miciu.spring.app.model.Employee;

import java.util.List;

public interface EmployeeRepository {

  List<Employee> findOlderThan(int minAge);
}
