package com.mspan.ems.employee;

import com.mspan.ems.EMSApplicationTests;
import com.mspan.ems.model.Employee;
import com.mspan.ems.repository.EmployeeRespository;
import com.mspan.ems.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class EmployeeServiceImplTest extends EMSApplicationTests {

    @MockBean
    EmployeeRespository underTestRepo;

    @Autowired
    private EmployeeServiceImpl testService;

    @Test
    public void itShouldAddEmployee(){
        Employee testEmployee = new Employee(
                "a137", "Dew Matthew", "IT", "dew@gmail.com"
        );

        when(underTestRepo.save(testEmployee)).thenReturn(testEmployee);
        assertEquals(testEmployee, testService.addEmployee(testEmployee));
    }

    @Test
    void itShouldGetAllEmployees() {

        when(underTestRepo.findAll()).thenReturn(Stream.of(
                new Employee(
                       "a137", "Dew Matthew", "dew@gmail.com", "IT"
                ),
                new Employee(
                        "a138", "Matthew Dew", "matt@gmail.com", "Compliance"
                )).collect(Collectors.toList()));

        assertEquals(2, testService.findAllEmployees().size());
    }

    @Test
    void itShouldFindEmployeeByID() throws Exception {

        Employee testEmployee = new Employee(
                "a137", "Dew Matthew", "dew@gmail.com", "IT"
        );
        when(underTestRepo.findById(testEmployee.getEmployeeID())).thenReturn(Optional.of(testEmployee));

        assertThat(Optional.of(testEmployee)).isNotNull();
        assertEquals(ResponseEntity.ok(testEmployee), testService.findEmployeeById(testEmployee.getEmployeeID()));

    }

    @Test
    void itShouldfindEmployeeByEmail() {
        Employee emp1 = new Employee(
                "a137", "Dew Matthew", "dew@gmail.com", "IT"
        );

        when(underTestRepo.findByEmployeeEmail(emp1.getEmail())).thenReturn(emp1);

        assertEquals(emp1, testService.findEmployeeByEmail(emp1.getEmail()));

    }

    @Test
    void itShouldfindEmployeeByDepartment() {

        Employee emp1 = new Employee(
                "a137", "Dew Matthew", "IT","dew@gmail.com"
        );

        String dpt = "IT";

        when(underTestRepo.findByDepartment(dpt)).thenReturn(Stream.of(
                emp1
        ).collect(Collectors.toList()));

        assertEquals(1, testService.findEmployeeByDepartment(dpt).size());

    }

    @Test
    void itShouldUpdateEmployeeDetails() throws Exception {
        Employee testEmployee = new Employee(
                "a137", "Dew Matthew", "IT", "dew@gmail.com"
        );

        Employee test = new Employee(
                "a137","test","test", "test"
        );
        when(underTestRepo.findById(testEmployee.getEmployeeID())).thenReturn(Optional.of(testEmployee));
        assertThat(Optional.of(testEmployee)).isNotNull();

        testService.updateEmployeeDetails(test, test.getEmployeeID());

        verify(underTestRepo, times(1)).save(test);

    }

    @Test
    void itShouldDeleteEmployee(){
        Employee test = new Employee(
                "a137","test","test", "test"
        );

        testService.deleteEmployee(test.getEmployeeID());
        verify(underTestRepo, times(1)).deleteById(test.getEmployeeID());
    }
}