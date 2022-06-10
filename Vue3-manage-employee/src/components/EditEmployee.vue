<template>
  <div class="container">
    <form @submit.prevent>
      <h3>Edit Employee</h3>
      <!-- <button @click="showAlert()">Hello world</button> -->
      <div class="employeeID">
        <p>ID:</p>
        <input type="text" readonly v-model="id">
      </div>
      <div>
        <p>Employee Number: </p>
        <input type="text" v-model="employeeNumber">
      </div>
      <div>
        <p>First Name:</p>
        <input type="text" v-model="firstName">
      </div>
      <div>
        <p>Last Name</p>
        <input type="text" v-model="lastName">
      </div>
      <div>
        <p>Age</p>
        <input type="text" v-model="age">
      </div>
      <div>
        <p>WorkStatus</p>
        <select name="" id="" v-model="workStatus">
          <option value="THUVIEC">THUVIEC</option>
          <option value="CHINHTHUC">CHINHTHUC</option>
          <option value="NGHIPHEPDAIHAN">NGHIPHEPDAIHAN</option>
          <option value="DATHOIVIEC">DATHOIVIEC</option>
        </select>
      </div>
      <div>
        <p>Password</p>
        <input type="text" v-model="password">
      </div>

      <div class="button-action">
        <button class="btn btn-primary" @click="saveButton()" type="submit"> Save </button>
        <button type="reset" class="btn btn-danger"> Cancel </button>
        <button type="button" class="btn btn-secondary" @click="backButton()"> Back </button>
      </div>

    </form>

  </div>

</template>

<script>
import Swal from 'sweetalert2'
import EmployeeService from '../service/EmployeeService'
// import { MyEmployee } from "../components/MyEmployee"
import { useRouter } from 'vue-router'
export default {
  data() {
    return {
      employee: {
        id: "",
        firstName: "",
        lastName: "",
        employeeNumber: "",
        age: "",
        workStatus: "",
        password: ""
      }

    }
  },

  methods: {
    // showAlert() {
    //   // Use sweetalert2
    //   var employee = JSON.parse(localStorage.getItem("Employee"))
    //   Swal.fire("Employee Id is: " + employee.id)
    // },
    getEmployee() {
      var employee = JSON.parse(localStorage.getItem("Employee"))
      console.log(employee)
      var id = parseInt(JSON.parse(localStorage.getItem("id")))
      console.log("id: " + id)
      this.id = id;
      this.firstName = employee.firstName;
      this.lastName = employee.lastName;
      this.employeeNumber = employee.employeeNumber;
      this.age = employee.age;
      this.workStatus = employee.workStatus;
      this.password = employee.password
      // EmployeeService.getEmployeeById(id).then(response => {
      //   this.employee = response.data;
      //   console.log("employee: " + response.data)
      // })
    },

    backButton() {
      const router = useRouter()
      this.$router.push("/")
    },

    saveButton() {
      console.log("name atfer: " + this.firstName)
      console.log("workStatus atfer: " + this.workStatus)
      const newEm = {
        id: this.id,
        firstName: this.firstName,
        lastName: this.lastName,
        employeeNumber: this.employeeNumber,
        age: this.age,
        workStatus: this.workStatus,
        password: this.password
      }
      console.log("new employee: " + newEm.firstName)

      EmployeeService.editEmployee(this.id, newEm).then(response => {
        console.log("response after update: " + response.data)
        console.log("response status: " + response.status)
        if (response.status == 200) {
          Swal.fire({
            title: "Update success"
          }).then(re => {
             this.$router.push("/")
          })
        }


      }).catch(error => {
        console.log("error " + error)
      })

      // myEmployee.id = this.id
      // myEmployee.firstName = this.firstName
      // console.log(myEmployee)
    }
  },


  beforeCreate() {

  },

  created() {
    this.getEmployee();
    // var employee = JSON.parse(localStorage.getItem("employee"))
    // console.log(employee)
    // this.employeeId = employee.id
    // console.log("employee Id: " + this.employeeId)
    // this.firstName = employee.firstName
    // this.lastName = employee.lastName
    // this.employeeNumber = employee.employeeNumber
    // this.age = employee.age
    // this.workStatus = employee.workStatus
  },


}
</script>

<style>
.container {
  margin-left: 20px;
}
</style>