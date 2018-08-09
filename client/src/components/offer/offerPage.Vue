<template>

</template>

<script>
  export default {
    name: 'offerPage'
    data: () => {
      return {
        drawer: true,
        state: store.state,
        offer: '',
      }
    },
    computed: {
      offer () { return this.state.offer}
    }, 
    methods: {
      display: function (offer){
        if(this.offer.length > 0){
          return this.offer
        }
      }
    }

  }
</script>

<style scoped>

</style>
