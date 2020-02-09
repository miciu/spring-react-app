package com.miciu.spring.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class EmployeeDto implements Serializable {
  @NotBlank(message = "firstName is mandatory")
  private String firstName;
  @NotBlank(message = "lastName is mandatory")
  private String lastName;
  @Min(value = 18, message = "the minimum age is 18 years")
  private int age;

  public EmployeeDto(String firstName, String lastName, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }
}
