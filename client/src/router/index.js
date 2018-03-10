import Vue from 'vue'
import Router from 'vue-router'

// import Blank from '@/components/layouts/blank'
// import Menu from '@/components/layouts/menu'

// import HelloWorld from '@/components/HelloWorld'
import inscriptionEntreprise from '@/components/layouts/inscriptionEntreprise'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: inscriptionEntreprise
    }
  ]
})
