package com.miciu.spring.app.services;

import com.miciu.spring.app.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
  
}
