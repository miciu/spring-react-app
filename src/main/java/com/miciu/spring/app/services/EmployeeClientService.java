package com.miciu.spring.app.services;

import com.miciu.spring.app.model.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeClientService {
  private final RestOperations restTemplate;

  @Value("${oauth2.client.employee.url}")
  private String clientUrl;

  @Autowired
  public EmployeeClientService(RestOperations restTemplate) {
    this.restTemplate = restTemplate;
  }

  public List<EmployeeDto> readEmployees() {
    //TODO call client API to get list of employees
   return Collections.emptyList();
  }
}
