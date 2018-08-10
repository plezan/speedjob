<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  import Router from 'router/index'
  /**
   * @vue
   * Entry point of the app
   */
  export default {
    name: 'app'
  }
</script>

<style lang="stylus">
  @import '~vuetify/src/stylus/main'
  @import './styl/card.styl'

  @font-face
    font-family 'Intro'
    src url('assets/typo/Intro.otf')

  @font-face
    font-family 'Intro Inline'
    src url('assets/typo/Intro_Inline.otf')

</style>
