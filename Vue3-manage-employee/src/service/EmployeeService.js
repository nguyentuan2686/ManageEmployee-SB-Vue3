import axios from "axios";

const  EMPLOYEE_API_BASE_URL = "http://localhost:8080/employee/"

class EmployeeService{

    getEmployeeById(id){
        return axios.get(EMPLOYEE_API_BASE_URL + "get-by-id" + "?id=" + id)
    }

    getEmployee(){
        return axios.get(EMPLOYEE_API_BASE_URL + "get-all-employee")
    }

    addEmployee(employee){
        return axios.post(EMPLOYEE_API_BASE_URL + "add-employee", employee)
    }

    editEmployee(id, employee){
        const config = { headers: {'Content-Type': 'application/json'} };
        return axios.put(EMPLOYEE_API_BASE_URL + "edit-employee/" + id, employee,config)
    }

    deleteEmployee(id){
        return axios.delete(EMPLOYEE_API_BASE_URL + "delete-employee/" + id)
    }

    searchEmployee(keyword,currentPage,sizePage,sortField,sortType){
        return axios.get(EMPLOYEE_API_BASE_URL + "get-all-employee" + "?keyword=" + keyword + "&currentPage=" + currentPage + "&sizePage=" + sizePage + "&sortField=" + sortField + "&sortType=" + sortType)
    }

}

export default new EmployeeService()