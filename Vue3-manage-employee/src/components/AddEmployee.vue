<template>
<div class="container">
    <form @submit.prevent>
      <h3>Add Employee</h3>
      <!-- <button @click="showAlert()">Hello world</button> -->
      <!-- <div class="employeeID">
        <p>ID:</p>
        <input type="text" readonly v-model="id">
      </div> -->
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
import EmployeeService from '@/service/EmployeeService';
import Swal from 'sweetalert2';

export default {

  data() {
    return {
      employeeNumber: "",
      firstName: "",
      lastName: "",
      age: "",
      password: ""
    }
  },

  methods: {
    saveButton() {
      var employee = {
        firstName: this.firstName,
        lastName: this.lastName,
        employeeNumber: this.employeeNumber,
        age: this.age,
        workStatus: this.workStatus,
        password: this.password
      }
      EmployeeService.addEmployee(employee).then(res => {
        var status = res.status;
        console.log("status: " + status)
        if(status === 200){
          Swal.fire({
            title: "Add Employee Success",
          }).then(res => {
            this.$router.push("/")
          })
        }else{
          Swal.fire({
            title: "Some thing error"
          })
        }
      })
    },
    backButton() {
      this.$router.push("/")
    },
  },
  components: { }
}
</script>

<style>
.addEmployee {
  margin-left: 30px;
  margin-right: auto;
}

.button-action{
  margin-top: 20px
}

.btn{
  margin: 1px;
}
</style>