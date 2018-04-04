<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
import colors from 'vuetify/es5/util/colors'
import Vue from 'vue'
import Vuetify from 'vuetify'
import Router from 'vue-router'

Vue.use(Vuetify, {
  theme: {
    primary: '#48b9c7',
    secondary: '#ef3d55',
    accent: '#ffec38',
    error: '#ef3d55',
  }
})

//Vue.use(VueRouter)

//const routes = [
  //{ path: '/menu', component: Menu },
  //{ path: '/ajoutOffres', component: ajoutOffresVue },
//]

export default {
  
  el: '#app',
  name: 'app'
}


</script>

<style>
</style>
