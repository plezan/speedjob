import Vue from 'vue'
import Router from 'vue-router'

import Blank from '@/components/layouts/blank'
// import Menu from '@/components/layouts/menu'

// import HelloWorld from '@/components/HelloWorld'
import SignUpBusiness from '@/components/business/views/signUpBusiness'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: Blank,
      children: [{
        path: 'signUpBusiness',
        component: SignUpBusiness
      }]
    },
    {
      path: '*',
      redirect: '/signUpBusiness'
    }
  ]
})
