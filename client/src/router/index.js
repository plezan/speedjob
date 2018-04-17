import Vue from 'vue'
import Router from 'vue-router'

// import Blank from '@/components/layouts/blank'
import Menu from '@/components/layouts/menu'
import StudentHome from '@/components/student/StudentHome'
import StudentProfil from '@/components/student/StudentProfil'

import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: Menu,
      children: [{
        path: 'HelloWorld',
        component: HelloWorld
      }]
    },
    {
      path: '/',
      component: Menu,
      children: [
        {
          path: 'student/home',
          component: StudentHome
        },
        {
          path: 'student/profil',
          component: StudentProfil
        }
      ]
    },

    {
      path: '*',
      redirect: '/HelloWorld'
    }
  ]
})
