<template>
  <v-alert
    v-model="active"
    :type="type"
    :transition="transition"
    dismissible
  >
    {{ message }}
  </v-alert>
</template>

<script>
  export default {
    name: "alert",
    props: {
      active: {type: Boolean, default: true},
      type: {type: String, default: 'info'},
      message: {type: String, required: true},
      transition: {type: String, default: 'slide-x-transition'}
    }
  }
</script>

<style lang="stylus" scoped>
  .alert
    position fixed
    right 15px
    z-index 9999

  & > div
    max-width 390px
    word-wrap break-word
</style>
