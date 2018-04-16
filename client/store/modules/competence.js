import axios from 'axios'

const HTTP = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Authorization': 'JWT_Speed_Job eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuZnIiLCJleHAiOjE1MjQwMTI1NDB9.vJOTIpMR4ct5eTToSK9pPn4nboYNLVKGfLhWkGclumPQiFN6AKw3EuJcWJEhLLoo5kqRF8wja0oiK11LjmcWpw',
    'Access-Control-Allow-Origin': 'http://localhost:8000',
    'Access-Control-Allow-Methods': '*',
    'accept': 'text/plain'
  }
})

// initial state
// shape: [{ id, quantity }]
const state = {
  competences: {
    name: '',
    level: ''
  }
}

// getters
const getters = {
  getCompetences: state => {
    return state.competences
  }
}

// actions
const actions = {
  getAllCompetences ({ commit, state }) {
    HTTP.get('competence/competence')
      .then(response => {
        commit('getAllCompetences', response)
      })
  }
}

// mutations
const mutations = {
  getAllCompetences (state, { data }) {
    state.competences = data
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
