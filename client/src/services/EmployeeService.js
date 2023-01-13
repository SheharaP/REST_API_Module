import axios from 'axios';

const BASE_URL = process.env.REACT_APP_BASE_URL;

class EmployeeService {

    getEmployees(){
        return axios.get(BASE_URL);
    }

    createEmployee(employee){
        return axios.post(BASE_URL, employee);
    }

    getEmployeeById(id){
        return axios.get(`${BASE_URL}/${id}`);
    }

    updateEmployee(employee, id){
        return axios.put(`${BASE_URL}/${id}`, employee);
    }

    deleteEmployee(id){
        return axios.delete(`${BASE_URL}/${id}`);
    }

}

export default new EmployeeService();