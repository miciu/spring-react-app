package com.miciu.spring.app.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miciu.spring.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

@Service
public class EmployeeService {

  @Value("classpath:/data/employees.json")
  private Resource resourceFile;
  
  private final ObjectMapper mapper;

  @Autowired
  public EmployeeService(ObjectMapper mapper) {
    this.mapper = mapper;
  }


  public List<Employee> readEmployeesFromJson() throws IOException {
    String json = loadResource();
    List<Employee> employees = mapper.readValue(json, List.class);

    return employees;
  }

  private String loadResource() throws IOException {
    return new String(readAllBytes(get(resourceFile.getURI())));
  }

}
