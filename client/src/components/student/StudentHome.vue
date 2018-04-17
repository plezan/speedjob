<template>
  <v-layout row wrap>
    <v-flex xs12 v-for="key of [1, 2, 3, 4, 5]" :key="key" class="ma-0">
      <v-card class="pa-3">
        <v-layout row>
          <v-flex xs1>
            <v-card-media src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfvslSVQuzPcCLmMOW4iGsaieZbqM7nXwr8BbI4BiHVQ9XvMb5" height="70px" width="75px"></v-card-media>
          </v-flex>
          <v-flex xs11>
            <v-card-text class="px-10">{{key}}</v-card-text>
          </v-flex>
        </v-layout>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
  name: 'StudentHome',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>
<style>
</style>
