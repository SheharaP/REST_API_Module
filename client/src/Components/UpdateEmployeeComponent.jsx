import React, { useEffect, useState } from 'react'
import { useNavigate , Link, useParams} from 'react-router-dom';
import EmployeeService from '../services/EmployeeService';

export default function UpdateEmployeeComponent() {

    let navigate = useNavigate();

    const {id} = useParams();

    const [employee, setEmployee] = useState({
        employeeName:'',
        department:'',
        email:''
    })

    const {employeeName, department, email} = employee;

    const onInputChange = (e) =>{
        setEmployee({...employee, [e.target.name]:e.target.value})
    }

    useEffect (() =>{
        getEmployee();
    }, [])

    const onSubmit = async (e) =>{
        e.preventDefault();
        await EmployeeService.updateEmployee(employee, id);
        navigate("/");
    }

    const getEmployee = async () =>{
        const response = await EmployeeService.getEmployeeById(id);
        setEmployee(response.data);
    }
    

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                <h2 className='text-center'>Update Employee Details</h2>
                <form>
                <div className='mb-3'>
                    <label htmlFor='name' className='form-label mt-3'>
                        Employee Name
                    </label>
                    <input type={'text'} 
                    className='form-control' 
                    placeholder='Fulll Name' 
                    name='employeeName'
                    value={employeeName}
                    onChange={(e) => onInputChange(e)}/>
                </div>
                <div className='mb-3'>
                    <label htmlFor='name' className='form-label mt-3'>
                        Employee Email
                    </label>
                    <input type={'text'} 
                    className='form-control' 
                    placeholder='Employee Email' 
                    name='email'
                    value={email} 
                    onChange={(e) => onInputChange(e)}/>
                </div>
                <div className='mb-3'>
                    <label htmlFor='name' className='form-label mt-3'>
                        Employee Department
                    </label>
                    <input type={'text'} 
                    className='form-control' 
                    placeholder='Employee Department' 
                    name='department'
                    value={department}  
                    onChange={(e) => onInputChange(e)}/>
                </div>
                
                <button type='submit' className='btn btn-outline-success' onClick={(e) => onSubmit(e)}>Update</button>
                <Link className='btn btn-outline-danger mx-3' to="/">Cancel</Link>
                </form>

            </div> 
        </div>
    </div>
  )
}
