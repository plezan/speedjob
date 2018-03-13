<template>
  <v-container xs12 sm10>
    <v-card class="elevation-2 scroll-30">
      <v-parallax src="https://wallpapersite.com/images/wallpapers/material-design-1920x1200-geometric-stock-dark-black-hd-10125.jpg" height="300">
        <v-card-title primary-title>
          <div class="mx-auto">
            <div class="display-3 intro">Speed Job</div>
            <div class="mt-2">Plateforme d'embauche mettant en relation les entreprises et l'école du numérique IMIE</div>
          </div>
        </v-card-title>
      </v-parallax>

      <v-card class="elevation-12">
        <v-card-title>
            <div class="headline mx-auto">INFORMATIONS</div>
        </v-card-title>
        <v-card-text>
          <v-form v-model="valid" ref="form" lazy-validation>
            <v-layout row wrap align-baseline>
              <v-flex xs12 sm4 offset-sm1>
                <v-text-field
                  name="lastName"
                  label="Nom"
                  :rules="lastNameRules"
                  v-model="lastName"
                ></v-text-field>
                <v-text-field
                  name="firstName"
                  label="Prénom"
                  :rules="firstNameRules"
                  v-model="firstName"
                ></v-text-field>
                <v-text-field
                  name="mail"
                  label="Mail"
                  :rules="mailRules"
                  v-model="mail"
                ></v-text-field>
                <v-text-field
                  name="phone"
                  label="Numéro de téléphone"
                  :rules="phoneRules"
                  v-model="phone"
                ></v-text-field>
                <v-text-field
                  name="job"
                  label="Poste / fonction dans l'entreprise"
                  :rules="jobRules"
                  v-model="job"
                ></v-text-field>
                <v-text-field
                  name="password"
                  label="Mot de passe"
                  hint="Au moins 6 caractères"
                  :rules="passwordRules"
                  v-model="password"
                  min="6"
                  :append-icon="passwordHide ? 'visibility' : 'visibility_off'"
                  :append-icon-cb="() => (passwordHide = !passwordHide)"
                  :type="passwordHide ? 'password' : 'text'"
                  counter
                ></v-text-field>
                <v-text-field
                  name="password"
                  label="Confirmez votre mot de passe"
                  :rules="passwordRepetitionRules"
                  v-model="passwordRepetition"
                  min="6"
                  :type="'password'"
                ></v-text-field>
              </v-flex>
              <v-flex xs12 sm4 offset-sm2>
                <v-text-field
                  name="businessName"
                  label="Raison sociale"
                ></v-text-field>
                <v-text-field
                  name="streetName"
                  label="Adresse : rue, impasse..."
                ></v-text-field>
                <v-layout row wrap>
                  <v-flex xs6>
                    <v-text-field
                      name="postalCode"
                      label="Code Postal"
                    ></v-text-field>
                  </v-flex>
                  <v-flex xs6>
                    <v-text-field
                      name="city"
                      label="Ville"
                    ></v-text-field>
                  </v-flex>
                </v-layout>
                <v-text-field
                  name="businessPhone"
                  label="Téléphone de la société"
                  :rules="phoneRules"
                  v-model="businessPhone"
                ></v-text-field>
                <v-text-field
                  name="businessMail"
                  label="Mail de la société"
                  :rules="mailRules"
                  v-model="businessMail"
                ></v-text-field>
                <v-layout row wrap>
                  <v-flex xs6 offset-xs3>
                    <div class="input-picture">
                      <picture-input
                        ref="pictureInput"
                        @change="onChangeImage"
                        accept="image/jpeg,image/png,image/gif,application/pdf"
                        width="200"
                        height="200"
                        size="2"
                        radius="2"
                        margin="10"
                        removable
                        :zIndex="1"
                        :customStrings="{
                        drag: 'Déposez un logo',
                        remove: 'Supprimer le logo'
                      }">
                      </picture-input>
                    </div>
                  </v-flex>
                </v-layout>
              </v-flex>
            </v-layout>
            <v-btn
              @click="submit"
              fab
              large
              absolute
              right
              color="success"
              class="mt-3 mb-3 btn-validate"
            >
              <v-icon>
                send
              </v-icon>
            </v-btn>
          </v-form>
        </v-card-text>
      </v-card>
    </v-card>
  </v-container>
</template>

<script>
  import PictureInput from 'vue-picture-input'

  export default {
    components: {
      PictureInput
    },
    data: function () {
      return {
        valid: false,
        firstName: '',
        firstNameRules: [
          v => !!v || 'Le prénom est obligatoire',
          v => (v && v.length <= 21) || 'Le prénom doit être inférieur ou égal à 20 caractères'
        ],
        lastName: '',
        lastNameRules: [
          v => !!v || 'Le nom de famille est obligatoire',
          v => (v && v.length <= 41) || 'Le nom de famille doit être inférieur ou égal à 40 caractères'
        ],
        mail: '',
        mailRules: [
          v => !!v || 'Le mail est obligatoire',
          v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'Le mail n\'est pas valide'
        ],
        phone: '',
        phoneRules: [
          v => !!v || 'Le numéro de téléphone est obligatoire',
          v => /^(0|\+33)[1-9]([-. ]?[0-9]{2}){4}$/.test(v) || 'Le numéro de téléphone n\'est pas valide'
        ],
        job: '',
        jobRules: [
          v => !!v || 'Le poste est obligatoire',
          v => (v && v.length > 20) || 'Le titre du poste doit être inférieur ou égal à 20 caractères'
        ],
        passwordHide: true,
        password: '',
        passwordRules: [
          v => !!v || 'Le mot de passe est obligatoire',
          v => (v && v.length >= 6) || 'Le mot de passe doit contenir au moins 6 caractères',
          v => (v && v.length <= 31) || 'Le mot de passe doit contenir moins de 30 caractères'
        ],
        passwordRepetition: '',
        passwordRepetitionRules: [
          v => !!v || 'Vous devez répéter votre mot de passe',
          v => (v && v === this.password) || 'Les mots de passe ne correspondent pas'
        ],
        businessName: '',
        streetName: '',
        streetNumber: '',
        postalCode: '',
        city: '',
        businessPhone: '',
        businessMail: '',
        image: null
      }
    },
    methods: {
      onChangeImage (image) {
        console.log('New picture selected!')
        if (image) {
          console.log('Picture loaded.')
          this.image = image
          console.log(image)
        } else {
          console.log('FileReader API not supported: use the <form>, Luke!')
        }
      },
      submit: function (event) {
        var inputs = document.getElementsByClassName('oblg')
        var isfilled = true
        for (var i = 0; i < inputs.length; i++) {
          if (inputs[i].value === '') {
            isfilled = false
          }
        }
        if (!isfilled) {
          this.errormessage = 'Vous devez remplir tous les champs obligatoires'
          document.getElementById('errormessage').style.visibility = 'visible'
        } else if (document.getElementById('password').value !== document.getElementById('password2').value) {
          this.errormessage = 'Vos mot de passe ne correspondent pas'
          document.getElementById('errormessage').style.visibility = 'visible'
        } else {
          inputs = document.getElementsByTagName('input')
          var data = {}

          for (i = 0; i < inputs.length; i++) {
            data[inputs[i].id] = inputs[i].name
          }

          var xhr = new XMLHttpRequest()
          xhr.open('POST', 'localhost/server/entreprise')
          xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8')
          xhr.send(JSON.stringify(data))
        }
      }
    }
  }

</script>

<style lang="stylus">
  td
    padding 5px
    padding-right 50px
    border 2px solid #48b9c7
    text-color #ffec38

  form tr:last-child
    border none

  #errormessage
    visibility hidden
    color #ef3d55
    text-align center
    padding 5px

  button
    background-color #48b9c7
    padding 5px

  #submit
    margin-left auto

  .intro
    font-family 'Intro Inline'

  .btn-validate
    z-index 1 !important
    bottom -40px
    right -30px

  .input-picture
    margin-bottom 50px

  .scroll-30
    margin-bottom 30px
</style>
