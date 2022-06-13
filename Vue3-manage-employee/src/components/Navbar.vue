<template>
  <div class="container">
    <div class="row">
      <div class="col-md-2">
        <p>Hello {{ name }} </p>
      </div>
      <div class="col-md-3 bg-light">
        <button type="button" class="btn btn-primary" @click="logout()">Logout</button>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      name: localStorage.getItem("name")
    }
  },
  methods: {
    logout() {
      this.$router.push("/login")
    }
  },
  created(){
  }
}
</script>

<style>
</style>