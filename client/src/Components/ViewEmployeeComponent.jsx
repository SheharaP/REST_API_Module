import React, { useEffect, useState } from 'react'
import { Link, useParams} from 'react-router-dom';
import EmployeeService from '../services/EmployeeService';

export default function ViewEmployeeComponent() {

    const {id} = useParams();

    const [employee, setEmployee] = useState([]);

    useEffect (() =>{
        getEmployee();
    }, [])

    const getEmployee = async () =>{
        const response = await EmployeeService.getEmployeeById(id);
        setEmployee(response.data);
    }

    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center'>Employee Details</h2>

                    <div className='card'>
                        <div className='card-header my-2'>
                            Details of Employee ID : <b>{employee.employeeID}</b>
                            <ul className='list-group list-group-flush'>
                                <li className='list-group-item my-2'>
                                    <b>Name : </b> {employee.employeeName}
                                    
                                </li>
                                <li className='list-group-item my-2'>
                                    <b>Email : </b> {employee.email}
                                    
                                </li>
                                <li className='list-group-item my-2'>
                                    <b>Department : </b> {employee.department}
                                    
                                </li>
                            </ul>
                        </div>
                    </div>
                    <Link className="btn btn-primary my-2" to={"/"}>Back to Home</Link>
                </div>
            </div>
        </div>
    )
}
