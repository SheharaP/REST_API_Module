package com.mspan.ems.Exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String id){

        super("Could not found the employee with id "+ id);
    }
}