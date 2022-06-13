<template>
    <div class="container">
        <form @submit.prevent="login()">
            <div>
                <div> Login Form </div>
                <div class="col-sm-3">
                    <div class="form-group">
                        <label for="">Username: </label>
                        <input type="text" class="form-control" name="" id="" aria-describedby="helpId" placeholder="" v-model="username">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="form-group">
                        <label for="">Password: </label>
                        <input type="password" class="form-control" name="" id="" aria-describedby="helpId"
                            placeholder="" v-model="password">
                    </div>
                </div>
                <div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            
                
            </div>
        </form>

    </div>
</template>

<script>
import EmployeeService from '@/service/EmployeeService'
export default {
    data(){
        return {
            username: "",
            password: ""
        }
    },
    methods: {
        login(){
            var info = {
                username: this.username,
                password: this.password
            }
            console.log("username: " + info.username + ", password: " + info.password)
            EmployeeService.login(info).then(res => {
                var status = res.status;
                console.log(res);
                let jwt = res.headers.authorization
                document.cookie = jwt
                console.log(status)
                if(status === 200){
                    localStorage.setItem("name", this.username)
                    this.$router.push("/")
                }
            });
        }
    },
}
</script>

<style>
</style>