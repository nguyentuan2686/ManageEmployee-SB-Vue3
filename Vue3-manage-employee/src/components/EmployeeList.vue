<template>
  <div id="employeeList">
    <h1 class="text-center"> Employee List</h1>
    <Employee></Employee>
  </div>
</template>

<script>
import Employee from "./Employee.vue";
import AddEmployee from "./AddEmployee.vue";

export default {
  name: "EmployeeList",
  components: { Employee, AddEmployee }
}
</script>

<style>
</style>