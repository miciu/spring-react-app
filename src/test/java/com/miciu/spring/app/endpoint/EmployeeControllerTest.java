package com.miciu.spring.app.endpoint;

import com.miciu.spring.app.model.EmployeeDto;
import com.miciu.spring.app.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.miciu.spring.app.model.Sector.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc(addFilters=false)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

  @MockBean(name = "employeeServiceImpl")
  private EmployeeService employeeService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void should_return_list_of_employees() throws Exception {
    EmployeeDto givenEmployee = new EmployeeDto("Jan", "Kowalski", 45, HEALTH);
    when(employeeService.readEmployees()).thenReturn(Arrays.asList(givenEmployee));

    mockMvc.perform(get("/employee")).andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0].firstName", is("Jan")))
        .andExpect(jsonPath("$[0].lastName", is("Kowalski")))
        .andExpect(jsonPath("$[0].age", is(45)))
        .andExpect(jsonPath("$[0].sector", is("HEALTH")));
  }

  @Test
  void should_add_employee() throws Exception {
    mockMvc.perform(post("/employee")
        .content("{\"firstName\": \"Jan\", \"lastName\": \"Testowy\", \"age\": \"54\"}")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    
    verify(employeeService).addEmployee(any(EmployeeDto.class));
  }

  @Test
  void should_fail_validation_when_empty_employee_is_added() throws Exception {
    mockMvc.perform(post("/employee")
        .content("{}")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.firstName", is("firstName is mandatory")))
        .andExpect(jsonPath("$.lastName", is("lastName is mandatory")))
        .andExpect(jsonPath("$.age", is("the minimum age is 18 years")));

    verify(employeeService, never()).addEmployee(any(EmployeeDto.class));
  }
}