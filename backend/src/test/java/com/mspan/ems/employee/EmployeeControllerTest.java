package com.mspan.ems.employee;

import com.mspan.ems.controller.EmployeeController;
import com.mspan.ems.model.Employee;
import com.mspan.ems.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeService testService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void itShouldCreateMockMvc() {

        assertNotNull(mockMvc);
    }

    @Test
    void itShouldCreateEmployee() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employeeID\": \"a137\", \"employeeName\":\"john\", \"department\": \"IT\", \"email\":\"john@ll.lk\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(testService).addEmployee(any(Employee.class));

    }

    @Test
    void itShouldGetAllEmployees() throws Exception {

        when(testService.findAllEmployees()).thenReturn(Stream.of((new Employee(
                "a137", "Dew Matthew", "IT", "dew@gmail.com"
        ))).collect(Collectors.toList()));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/employee")
                        .header("x-foo", "Dew Matthew"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeName").value("Dew Matthew"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeID").value("a137"));
    }

    @Test
    void getEmployeeById() throws Exception {

        Employee emp1 = new Employee(
                "a137", "test", "testDepartment", "testEmail"
        );
        when(testService.findEmployeeById(emp1.getEmployeeID())).thenReturn(ResponseEntity.ok(emp1));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/employee/{employeeID}", emp1.getEmployeeID())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeID").value(emp1.getEmployeeID()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeName").value("test"));

    }

    @Test
    void getEmployeeByDepartment() throws Exception {

        Employee emp1 = new Employee(
                "a137", "test", "IT", "testEmail"
        );
        Employee emp2 = new Employee(
                "a138", "Dew Matthew", "IT", "dew@gmail.com"
        );

        String dpt = "IT";

        when(testService.findEmployeeByDepartment(dpt)).thenReturn(Stream.of(
                emp1, emp2
        ).collect(Collectors.toList()));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/employee/department/{department}", dpt)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
    }

    @Test
    void getEmployeeByEmail() throws Exception {

        Employee emp1 = new Employee(
                "a137", "test", "testDepartment", "testEmail"
        );
        when(testService.findEmployeeByEmail(emp1.getEmail())).thenReturn(emp1);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/employee/email/{email}", emp1.getEmail())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeID").value(emp1.getEmployeeID()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testEmail"));
    }

    @Test
    void modifyEmployeeDetails() throws Exception {

        String id = "a137";

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/employee/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employeeName\":\"john\", \"department\": \"IT\", \"email\":\"john@ll.lk\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(testService).updateEmployeeDetails(any(Employee.class), eq("a137"));
    }

    @Test
    void deleteEmployee() throws Exception {
        Employee emp1 = new Employee(
                "a137", "test", "testDepartment", "testEmail"
        );

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/employee/{employeeID}", emp1.getEmployeeID())
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(testService).deleteEmployee(emp1.getEmployeeID());

    }
}