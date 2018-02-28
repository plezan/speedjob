import Vue from 'vue'
import Router from 'vue-router'

// import Blank from '@/components/layouts/blank'
import Menu from '@/components/layouts/menu'

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
      path: '*',
      redirect: '/HelloWorld'
    }
  ]
})
