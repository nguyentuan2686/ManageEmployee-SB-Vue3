<template>
    <div class="searchbar">
        <form @submit.prevent>
            <div class="row">
                <div class="col-sm-2">
                    <!-- <input type="text" placeholder="Find By Name"> -->
                    <div class="form-group">
                        <input type="text" class="form-control" name="" id="" aria-describedby="helpId"
                            placeholder="Find By Name">
                        <!-- <small id="helpId" class="form-text text-muted">Help text</small> -->
                    </div>
                </div>
                <div class="col-sm-3">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>--Choose item/page--</option>
                        <option value="3">3</option>
                        <option value="5">5</option>
                        <option value="10">10</option>
                    </select>
                </div>
                <div class="col-sm-3">
                    <div class="form-group">
                        <select class="form-select" name="" id="">
                            <option value="">--SortBy--</option>
                            <option>NumberEmployee</option>
                            <option>Age</option>
                            <option>FirstName</option>
                        </select>
                    </div>
                </div>

                <div class="col-sm-4">
                    <button type="submit" class="btn btn-primary" @click="searchEmployee()">Search</button>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
import axios from 'axios'
import EmployeeService from '@/service/EmployeeService'
export default {
    data() {
        return {
            keyword: "",
            currentPage: "",
            sizePage: "",
            sortField: "",
            sortType: ""
        }
    },
    methods: {
        searchEmployee() {
            EmployeeService.searchEmployee(keyword,currentPage,sizePage,sortField,sortType).then(response => {

            })
        }
    }
}
</script>

<style>
.searchbar {
    margin-left: 30px;
    margin-bottom: 10px;
}
</style>