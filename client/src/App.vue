<template>
  <div id="app">
  <div v-offer> </div>
  <offer></offer>
  <router-view></router-view>
  </div>
</template>

<script>
<<<<<<< Updated upstream
  /**
   * @vue
   * Entry point of the app
   */
  export default {
    name: 'app'
  }
=======
  import './components/offerPage.vue';
  import './components/addOffers.vue'; 
 
export default {
  components: {  }
}
>>>>>>> Stashed changes
</script>

<style></style>