<template>
  <div id="employeeList">
    <div class="container">
      <Navbar></Navbar>
    </div>
    <h1 class="text-center"> Employee List</h1>
    <Employee></Employee>
  </div>
</template>

<script>
import Employee from "./Employee.vue";
import AddEmployee from "./AddEmployee.vue";
import Navbar from "./Navbar.vue";

export default {
  name: "EmployeeList",
  methods: {
  },
  components: { Employee, AddEmployee, Navbar }
}
</script>

<style>
</style>