package com.mspan.ems.repository;

import com.mspan.ems.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@EnableMongoRepositories
@Repository
public interface EmployeeRespository extends MongoRepository<Employee, String> {

    List<Employee> findByDepartment(String department);

    @Query("{email: ?0}")
    Employee findByEmployeeEmail(String email);

}
