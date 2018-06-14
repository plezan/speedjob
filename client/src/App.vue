<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  /**
   * @vue
   * Entry point of the app
   */
  export default {
    name: 'app'
  }
</script>

<style lang="stylus">
  @import './styl/alert.styl'
  @import './styl/card.styl'

  @font-face
    font-family 'Intro'
    src url('assets/typo/Intro.otf')

  @font-face
    font-family 'Intro Inline'
    src url('assets/typo/Intro_Inline.otf')

</style>
