package com.mspan.ems.service;

import com.mspan.ems.model.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface EmployeeService {
    Employee addEmployee(@NotNull Employee employee);

    List<Employee> findAllEmployees();

    ResponseEntity<Employee> findEmployeeById(String employeeID);

    List<Employee> findEmployeeByDepartment(String department);

    Employee findEmployeeByEmail(String email);

    Employee updateEmployeeDetails(Employee employeeReq, String id);

    String deleteEmployee(String employeeID);
}
