<template>
    <div id="employee">
        <div class="container">
            <div class="searchBar">
                <form @submit.prevent>
                    <div class="row">
                        <div class="col-sm-2">
                            <!-- <input type="text" placeholder="Find By Name"> -->
                            <div class="form-group">
                                <input type="text" class="form-control" name="" id="" aria-describedby="helpId"
                                    v-model="keyword" placeholder="Find By Name">
                                <!-- <small id="helpId" class="form-text text-muted">Help text</small> -->
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <select class="form-select" aria-label="Default select example" v-model="sizePage">
                                <option selected value="">--Choose item/page--</option>
                                <option value="3">3</option>
                                <option value="5">5</option>
                                <option value="10">10</option>
                            </select>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <select class="form-select" name="" id="" v-model="sortField">
                                    <option value="">--SortBy--</option>
                                    <option value="employeeNumber">NumberEmployee</option>
                                    <option value="age">Age</option>
                                    <option value="firstName">FirstName</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <button type="submit" class="btn btn-primary" @click="searchEmployee()">Search</button>
                        </div>
                    </div>
                </form>
            </div>

            <div>
                <button type="button" class="btn btn-primary" @click="exportExcel()">Export File</button>
            </div>


            <div>
                <button class="btn btn-primary float-right" @click="addEmployee()">Add Employee</button>
            </div>
            <br>
            <table class="table table-striped">
                <thead>
                    <th> Id </th>
                    <th> Number </th>
                    <th> FirstName </th>
                    <th> LastName </th>
                    <th> Age </th>
                    <th> WorkStatus </th>
                    <th> Action </th>
                </thead>
                <tbody>
                    <tr v-for="employee in listEmployee" v-bind:key="employee.id">
                        <td> {{ employee.id }} </td>
                        <td> {{ employee.employeeNumber }} </td>
                        <td> {{ employee.firstName }} </td>
                        <td> {{ employee.lastName }} </td>
                        <td> {{ employee.age }} </td>
                        <td> {{ employee.workStatus }} </td>
                        <td>
                            <span class="edit_icon" @click="editEmployee(employee.id, employee)">
                                <fai :icon="['fas', 'edit']" />
                                <!-- <router-link to="/edit-employee"><fai :icon="['fas', 'edit']" /></router-link>                            -->
                            </span>

                            <span class="delete_icon" @click="deleteEmployee(employee.id)">
                                <fai :icon="['fas', 'remove']" />
                            </span>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>


<script>

import EmployeeService from '../service/EmployeeService'
import { useRoute } from 'vue-router'
import Swal from 'sweetalert2'
import * as filesaver from '../components/filesaver.js'
import jwt_decode from "jwt-decode";


export default {
    name: "employee",
    data() {
        return {
            keyword: "",
            sizePage: "",
            sortField: "",
            listEmployee: [],
        }

    },
    methods: {
        getEmployee() {
            // const config = {
            //     headers: {
            //         Authorization: token
            //     }
            // }
            EmployeeService.searchEmployee("", 0, 10, "", "").then(response => {
                this.listEmployee = response.data;
            })
        },

        searchEmployee() {
            // let token = document.cookie;
            // console.log("token" + token)
            // const config = {
            //     headers: {
            //         Authorization: token
            //     }
            // }
            EmployeeService.searchEmployee(this.keyword, 0, this.sizePage, this.sortField, "").then(res => {
                this.listEmployee = res.data;
            })
        },

        addEmployee() {
            this.$router.push("/add-employee")
        },

        exportExcel() {
            console.log(window)
            var data = this.listEmployee
            const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
            const EXCEL_EXTENSION = '.xlsx';
            const worksheet = XLSX.utils.json_to_sheet(data)
            console.log(worksheet)
            const workbook = {
                Sheets: {
                    data: worksheet
                },
                SheetNames: ["data"]
            };
            console.log(workbook)

            const excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
            console.log(excelBuffer)
            const myExcel = new Blob([excelBuffer], { type: EXCEL_TYPE });
            console.log("what")
            // filesaver.saveAs(myExcel, "myFile" + EXCEL_EXTENSION)
            saveAs(myExcel, "myFile" + EXCEL_EXTENSION)
        },

        editEmployee(id, employee) {
            localStorage.setItem("id", id)
            var token = localStorage.getItem("jwt");
            var decodeJWT = jwt_decode(token);
            console.log("JWT: " + JSON.stringify(decodeJWT) )

            const router = useRoute();
            if (decodeJWT.sub == employee.employeeNumber) {
                this.$router.push({
                    path: '/edit-employee'
                })
                localStorage.setItem('Employee', JSON.stringify(employee))
            } else {
                var authorrities = decodeJWT.authorrities;
                
                for (let i = 0; i < authorrities.length; i++) {
                    console.log("authorrities: " + authorrities[i].authority)
                    if (authorrities[i].authority == "ROLE_ADMIN") {
                        this.$router.push({
                            path: '/edit-employee'
                        })
                        localStorage.setItem('Employee', JSON.stringify(employee))
                        break;
                    }else{
                        Swal.fire({
                            title: "U dont have a permission to edit other employee"
                        })
                    }
                }
            }

        },
        deleteEmployee(id) {
            Swal.fire({
                title: "Are u sure delete this employee",
                icon: "warning",
                showDenyButton: true,
                confirmButtonText: 'OK',
                denyButtonText: `Let me think more`,
            }).then(result => {
                if (result.isConfirmed) {
                    EmployeeService.deleteEmployee(id).then(resonse => {
                        var data = resonse.data;
                        var status = resonse.status;
                        if (status === 200) {
                            Swal.fire({
                                title: "Delete success"
                            }).then(res => {
                                window.location.reload()
                            })
                        } else {
                            Swal.fire({
                                title: "Error"
                            })
                        }
                    })
                }
            })
        }
    },
    created() {
        this.getEmployee()
    },
    mounted() {
        let recaptchaScript = document.createElement('script')
        recaptchaScript.setAttribute('src', 'https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.2/xlsx.full.min.js')
        document.head.appendChild(recaptchaScript)
    },
    components: {}
}
</script>

<style>
.edit_icon {
    margin-right: 10px;
    color: blue;
    cursor: pointer;
}

.delete_icon {
    color: red;
    cursor: pointer;
}

.searchBar {
    margin: 10px 0 10px 0;
}
</style>