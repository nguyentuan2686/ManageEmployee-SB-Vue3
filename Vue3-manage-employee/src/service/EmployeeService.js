
import axios from "axios";

const  EMPLOYEE_API_BASE_URL = "http://localhost:8080/employee/"
const  LOGIN_URL = "http://localhost:8080/login"
const TOKEN = localStorage.getItem("jwt");
const config = {
    headers: {
        'Content-Type': 'application/json',
        Authorization: TOKEN
    }
}

class EmployeeService{


    getEmployeeById(id){
        return axios.get(EMPLOYEE_API_BASE_URL + "get-by-id" + "?id=" + id)
    }

    getEmployee(){
        return axios.get(EMPLOYEE_API_BASE_URL + "get-all-employee")
    }

    addEmployee(employee){
        return axios.post(EMPLOYEE_API_BASE_URL + "add-employee", employee,config)
    }

    editEmployee(id, employee){
        // const config = { headers: {'Content-Type': 'application/json'} };
        console.log("token in edit: " + TOKEN)
        return axios.put(EMPLOYEE_API_BASE_URL + "edit-employee/" + id, employee,config)
    }

    deleteEmployee(id){
        return axios.delete(EMPLOYEE_API_BASE_URL + "delete-employee/" + id,config)
    }

    searchEmployee(keyword,currentPage,sizePage,sortField,sortType){
        console.log("Token in search: " + TOKEN)
        return axios.get(EMPLOYEE_API_BASE_URL + "get-all-employee" + "?keyword=" + keyword + "&currentPage=" + currentPage + "&sizePage=" + sizePage + "&sortField=" + sortField + "&sortType=" + sortType, config)
    }

    login(infoLogin){
        return axios.post(LOGIN_URL, infoLogin)
    }

}

export default new EmployeeService()