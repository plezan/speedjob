import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import competence from './modules/competence'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    user,
    competence
  }
})

export default store
