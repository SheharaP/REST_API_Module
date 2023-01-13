import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import EmployeeService from '../services/EmployeeService';

export default function Home() {

    const [employees, setEmployees] = useState([]);

    useEffect(() => {
        employeeList();
    }, []);

    const employeeList = async () => {
        const response = await EmployeeService.getEmployees();
        console.log(response.data);
        setEmployees(response.data);
    }

    const deleteEmp = async (id) => {
        await EmployeeService.deleteEmployee(id);
        employeeList();
    }

    return (
        <div className='container'>
            <div className='py-4'>
                <table className="table border shadow">
                    <thead>
                        <tr>
                            <th scope="col">Employee ID</th>
                            <th scope="col">Employee Name</th>
                            <th scope="col">Employee Email</th>
                            <th scope="col">Employee Department</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            employees.map(
                                employee =>
                                    <tr key={employee.employeeID}>
                                        <td>{employee.employeeID}</td>
                                        <td>{employee.employeeName}</td>
                                        <td>{employee.email}</td>
                                        <td>{employee.department}</td>
                                        <td>
                                        <Link className="btn btn-primary mx-2" to={`/view-employee/${employee.employeeID}`}> View </Link>
                                        <Link className="btn btn-outline-primary mx-2" to={`/update-employee/${employee.employeeID}`}> Update </Link>
                                        <button className="btn btn-danger mx-2" 
                                        onClick={() => deleteEmp(employee.employeeID)}> 
                                        Delete </button>
                                        </td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        </div>

    )
}
