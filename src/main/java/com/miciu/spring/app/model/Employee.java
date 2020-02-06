package com.miciu.spring.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@ToString
public class Employee {
  @NotBlank(message = "firstName is mandatory")
  private String firstName;
  @NotBlank(message = "lastName is mandatory")
  private String lastName;
  @Min(value = 18, message = "the minimum age is 18 years")
  private int age;
}
