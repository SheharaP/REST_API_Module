package com.mspan.ems.controller;

import com.mspan.ems.model.Employee;
import com.mspan.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeService empService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {

        return empService.addEmployee(employee);
    }

    @GetMapping()
    public List<Employee> getEmployees(){

        return empService.findAllEmployees();
    }

    @GetMapping("{employeeID}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeID) throws Exception {
        return empService.findEmployeeById(employeeID);
    }

    @GetMapping("/department/{department}")
    public List<Employee> getEmployeeByDepartment(@PathVariable String department){
        return empService.findEmployeeByDepartment(department);
    }

    @GetMapping("/email/{email}")
    public Employee getEmployeeByEmail(@PathVariable String email){

        return empService.findEmployeeByEmail(email);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee modifyEmployeeDetails(@RequestBody Employee employee, @PathVariable String id){
        return empService.updateEmployeeDetails(employee, id);
    }

    @DeleteMapping("/{employeeID}")
    public String deleteEmployee(@PathVariable String employeeID){

        return empService.deleteEmployee(employeeID);
    }

}
