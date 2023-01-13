package com.mspan.ems.service.impl;

import com.mspan.ems.Exception.EmployeeNotFoundException;
import com.mspan.ems.model.Employee;
import com.mspan.ems.repository.EmployeeRespository;
import com.mspan.ems.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRespository empRepository;

    @Override
    public Employee addEmployee(@NotNull Employee employee) {
        log.info("Employee added");
        employee.setEmployeeID(UUID.randomUUID().toString().split("-")[0]);
        return empRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        log.info("Getting employee data from db " + empRepository.findAll());
        return empRepository.findAll();
    }

    @Override
    public ResponseEntity<Employee> findEmployeeById(String employeeID) {

        Employee employee = empRepository.findById(employeeID)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeID));

            return ResponseEntity.ok(employee);
    }

    @Override
    public List<Employee> findEmployeeByDepartment(String department) {
        return empRepository.findByDepartment(department);
    }

    @Override
    public Employee findEmployeeByEmail(String email) {

        return empRepository.findByEmployeeEmail(email);
    }

    @Override
    public Employee updateEmployeeDetails(Employee employeeReq, String id) {

        return empRepository.findById(id)
                .map(employee -> {
                    employee.setEmployeeName(employeeReq.getEmployeeName());
                    employee.setDepartment(employeeReq.getDepartment());
                    employee.setEmail(employeeReq.getEmail());
                    log.info("updated employee = " + employee);
                    return empRepository.save(employee);
                })
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public String deleteEmployee(String employeeID) {

        if(!empRepository.existsById(employeeID)){
           throw new EmployeeNotFoundException(employeeID);
        }
        empRepository.deleteById(employeeID);
        return "Employee with id " + employeeID + " is deleted";
    }

}
