<template>
  <v-flex xs12 sm8 md6>
    <v-flex tag="h1" style="text-align: center">{{ msg }}</v-flex>
    <v-flex tag="p">Plateforme d'embauche mettant en relation les entreprises et l'ecole du numerique IMIE</v-flex>
    <v-card class="elevation-12">

      <v-card-text>
        <v-container grid-list-md text-xs-center>
          <v-layout row wrap>
            <v-flex xs6>
              <v-form @submit.prevent="login()">
                <v-text-field
                prepend-icon="person"
                name="mail"
                label="mail"
                type="text"
                v-model="mail"
                ></v-text-field>
                <v-text-field
                prepend-icon="lock"
                name="password"
                label="mot de passe"
                id="password"
                v-model="password"
                type="password"
                ></v-text-field>
                <v-btn
                type="submit"
                block
                color="primary"
                >Se connecter</v-btn>
              </v-form>
            </v-flex>

            <v-flex xs6>
              Inscription
              <v-btn
              block
              color="secondary"
              >Je suis étudiant</v-btn>
              <v-btn
              block
              color="secondary"
              >Je suis une entreprise</v-btn>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-text>
    </v-card>
  </v-flex>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Login',
  data () {
    return {
      msg: 'SpeedJob',
      password: '',
      mail: ''
    }
  },
  methods: {
    login: function () {
      if (!this.password || !this.mail) return false

      axios.post(
        '/back/login',
        {
          mail: this.mail,
          password: this.password
        }
      ).then((response) => {
        let jwt = response.headers.authorization
        console.log(jwt)
      })

      return false
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
