<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>

import Vue from 'vue'
import Vuetify from 'vuetify'

Vue.use(Vuetify, {
  theme: {
    primary: '#48b9c7',
    secondary: '#ef3d55',
    accent: '#ffec38',
    error: '#ef3d55'
  }
})

export default {
  name: 'app'
}
</script>

<style>
</style>
