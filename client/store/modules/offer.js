import axios from 'axios';

const HTTP = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Authorization': 'JWT_Speed_Job eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuZnIiLCJleHAiOjE1MjQwMTI1NDB9.vJOTIpMR4ct5eTToSK9pPn4nboYNLVKGfLhWkGclumPQiFN6AKw3EuJcWJEhLLoo5kqRF8wja0oiK11LjmcWpw',
    'Access-Control-Allow-Origin': 'http://localhost:8000',
    'Access-Control-Allow-Methods': '*',
    'accept': 'text/plain'
  }
})

const state = {
  offer: {
    offe_id: '',
    offe_title: '',
    type_name: '',
    offe_status: '',
    offe_description: '',
    offe_startDate: '',
    offe_endDate: '',
    comp_id: '',
    competences: [],
    offe_status: '',
    type_id: '',
    type_name: '',
  }
}

const getters= {
  getOffer: state => {
    return state.offer
  }
}

const actions = {
  publishOffer ({commit, state}, offer){
    axios.post('offers/offer?id=1', offer)
    .then(response => {
      commit('publishOffer', response)
    })
    .catch(err => {
      console.log(err, response)
    })
  },

  registerUnfinishedOffer ({commit, state}, offer){
    //changement de status pour enregistrer un brouillon
    this.offer_status = true;
    axios.post('offer/offers', offer)
    .then(response => {
      commit('response', response)
    })
    .catch(err => {
      console.log(err)
    })
  },

  getAllOffers ({commit, state}, offer){
    axios.get('offer/offers', offer)
    .then(response => {
      commit('getAllOffers', response)
      .catch(err => {
        console.log(err)
      })
    })
  }
}

const mutations = {
  publishOffer (state, { data }){
    state.addOffers = data;
  },
  registerUnfinishedOffer (state, { data }){
    state.addOffers.offer = data;
  },
  getAllOffers ( state, { data }){
    state.offerPage.offers = data;
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
