<template>
  <v-app id="inspire" dark>
    <v-navigation-drawer
      clipped
      fixed
      v-model="drawer"
      app
    >
      <v-list dense>
        <v-list-tile >
          <v-list-tile-action>
            <v-icon>dashboard</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Dashboard</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile >
          <v-list-tile-action>
            <v-icon>settings</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Settings</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar app fixed clipped-left>
      <v-toolbar-side-icon> </v-toolbar-side-icon>
      <v-toolbar-title>SpeedJob</v-toolbar-title>
    </v-toolbar>

     <v-badge left color="primary" overlap>
      <v-icon slot="badge" dark small>done</v-icon>
      <v-icon
        large
        color="grey lighten-1"
      >
        account_circle
      </v-icon>
    </v-badge>
    <v-badge overlap color="secondary">
      <v-icon slot="badge" dark small>notifications</v-icon>
      <v-icon
        large
        color="grey darken-1"
      >
        account_box
      </v-icon>
    </v-badge>
    <v-content>
      <v-container fluid fill-height>
        <v-layout justify-center align-center>
          <v-flex shrink>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
    <v-footer app fixed>
      <span>&copy; </span>
    </v-footer>
  </v-app>
</template>

<script>


  export default {
    data () {
      return {
        drawer: true,
        state: store.state,
      }
    },
    computed: {
      offer () { return this.state.offer}
    }

  }


</script>
