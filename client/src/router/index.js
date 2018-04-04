import Vue from 'vue'
import Vuetify from 'vuetify'
import Router from 'vue-router'
import Axios from 'axios'


import Menu from '@/components/layouts/menu'
import addOffers from '@/components/layouts/addOffers'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: addOffers,
      children: [{
        path: 'layouts/addOffers',
        component: addOffers
      }]
    },
    {
    
    }
  ]
})
