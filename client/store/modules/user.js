import axios from 'axios'

const HTTP = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Authorization': 'JWT_Speed_Job eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBpbWllLmZyIiwiZXhwIjoxNTI0MTIwOTcxfQ.PrGK1BFSIkmrCHGEPL9NA8lNfjtrQOG1d5RScr-5YFyoQRKdbCuNP_Yhmp3ZBT68_uqXMuA-9sv_Uv40b9YuWw',
    'Access-Control-Allow-Origin': 'http://localhost:8000',
    'Access-Control-Allow-Methods': '*',
    'accept': 'text/plain'
  }
})

// initial state
// shape: [{ id, quantity }]
const state = {
  userProfil: {
    lastName: '',
    firstName: '',
    phone: '',
    mail: '',
    cv: '',
    presentation: '',
    competences: []
  }
}

// getters
const getters = {
  getUser: state => {
    return state.userProfil
  }
}

// actions
const actions = {
  getUserInfo ({ commit, state }, user) {
    HTTP.get('users/user?id=1')
      .then(response => {
        commit('getUserInfo', response)
      })
  },
  updateUserInfo ({ commit, state }, user) {
    HTTP.put('users/updateUser', user)
      .then(response => {
        console.log('response', response)
      })
      .catch(err => {
        console.log(err)
      })
  },
  updateCv ({ commit, state }, data) {
    let formData = new FormData();
    formData.append('file', data.file);
    HTTP.put('users/updateCv?id='+data.user.id, formData)
      .then(response => {
        commit('getUserInfo', response)
      })
      .catch(err => console.log(err))
  }
}

// mutations
const mutations = {
  getUserInfo (state, { data }) {
    state.userProfil = data
  },
  updateCv (state, { data }) {
    state.userProfil.cv = data
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
