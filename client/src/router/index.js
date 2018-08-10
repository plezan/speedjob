import Vue from 'vue'
import Router from 'vue-router'

import Menu from '@/components/layouts/menu'
import StudentHome from '@/components/student/StudentHome'
import StudentProfil from '@/components/student/StudentProfil'
import Blank from '@/components/layouts/blank'
// import Menu from '@/components/layouts/menu';
// import HelloWorld from '@/components/HelloWorld';
import SignUpContactBusiness from '@/components/contactBusiness/views/signUpContactBusiness';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      component: Blank,
      children: [{
        path: 'signUpContactBusiness',
        component: SignUpContactBusiness
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
      redirect: '/signUpBusiness'
    }
  ]
});
