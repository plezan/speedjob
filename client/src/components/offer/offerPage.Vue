<template>

</template>

<script>
  export default {
    el: '#app',
    data: () => {
      return {
        drawer: true,
        state: store.state,
        offer: '',
      }
    },
    computed: {
      offer () { return this.state.offer}
    }

  }
</script>

<style scoped>

</style>
