// initial state
// shape: [{ id, quantity }]
const state = {
  userProfil: {}
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
  }
}

// mutations
const mutations = {
  getUserInfo (state, { data }) {
    state.userProfil = data
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
