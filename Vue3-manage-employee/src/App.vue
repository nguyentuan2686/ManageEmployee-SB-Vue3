<template>
  <div id="app">
  <Header></Header>
  <!-- <EmployeeList></EmployeeList> -->
  <router-view></router-view>
  </div>
</template>

<script>
import Header from "./components/Header.vue";
import EmployeeList from "./components/EmployeeList.vue";
export default {
  name: "App",
  components: { Header, EmployeeList }
}
</script>

<style>

</style>