package com.mspan.ems.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
public class Employee {

    @Id
    private String employeeID;
    private String employeeName;
    private String department;
    private String email;

    public Employee(String employeeName,
                    String email,
                    String department) {
        this.employeeName = employeeName;
        this.department = department;
        this.email = email;
    }
}
