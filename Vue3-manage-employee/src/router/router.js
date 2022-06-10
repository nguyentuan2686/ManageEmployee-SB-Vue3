 import { createRouter, createWebHistory } from 'vue-router'

 import EditEmployee from '../components/EditEmployee.vue'
 import InfoEmployee from '@/components/InfoEmployee.vue'
 import ErrorVue from '@/components/Error.vue'
import EmployeeListVue from '@/components/EmployeeList.vue'
import AddEmployeeVue from '@/components/AddEmployee.vue'
import LoginVue from '@/components/Login.vue'

 const routes = [
     {path: '/', component: EmployeeListVue},
     {path: '/edit-employee', component: EditEmployee},
     {path: '/info-employee', component: InfoEmployee},
     {path: '/add-employee', component: AddEmployeeVue},
     {path: '/:catchAll(.*)', component: ErrorVue},
     {path: "/login", component: LoginVue}
 ]

 const router = createRouter({
     history: createWebHistory(),
     routes: routes
 })

 export default router