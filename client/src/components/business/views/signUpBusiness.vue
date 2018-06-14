<template>
  <div>
    <v-alert
      v-model="alert"
      :type="alertStatus"
      transition="slide-x-transition"
      dismissible
    >
      {{ alertMessage }}
    </v-alert>

    <v-container>

      <v-layout row wrap>
        <v-flex >

          <v-card class="elevation-2 mb-5" dark tile>

            <v-parallax src="https://wallpapersite.com/images/wallpapers/material-design-1920x1200-geometric-stock-dark-black-hd-10125.jpg" height="300">
              <v-card-title primary-title>
                <div class="mx-auto">
                  <div class="display-3 intro-inline ma-4">SpeedJob</div>
                  <div class="title intro ma-2">Plateforme d'embauche mettant en relation les entreprises et l'école du numérique IMIE</div>
                </div>
              </v-card-title>
            </v-parallax>

            <v-card class="elevation-12">

              <v-card-title>
                <div class="headline mx-auto">Inscription entreprise</div>
              </v-card-title>

              <v-card-text>
                <v-form v-model="valid" ref="form" lazy-validation>

                  <v-layout row wrap align-baseline>
                    <v-flex xs12 sm4 offset-sm1>

                      <v-text-field
                        name="lastName"
                        label="Nom"
                        suffix="*"
                        :rules="lastNameRules"
                        v-model="lastName"
                      ></v-text-field>

                      <v-text-field
                        name="firstName"
                        label="Prénom"
                        suffix="*"
                        :rules="firstNameRules"
                        v-model="firstName"
                      ></v-text-field>

                      <v-text-field
                        name="mail"
                        label="Email"
                        suffix="*"
                        :rules="mailRules"
                        v-model="mail"
                      ></v-text-field>

                      <v-text-field
                        name="phone"
                        label="Numéro de téléphone"
                        :error-messages="phoneRules"
                        :rules="phoneRulesWatched"
                        v-model="phone"
                      ></v-text-field>

                      <v-text-field
                        name="job"
                        label="Poste / fonction dans l'entreprise"
                        suffix="*"
                        :rules="jobRules"
                        v-model="job"
                      ></v-text-field>

                      <v-text-field
                        name="password"
                        label="Mot de passe"
                        suffix="*"
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
                        suffix="*"
                        :rules="passwordRepetitionRules"
                        v-model="passwordRepetition"
                        min="6"
                        :type="'password'"
                      ></v-text-field>

                    </v-flex>
                    <v-flex xs12 sm4 offset-sm2>


                      <v-tabs
                        v-model="tabActive"
                      >
                        <!-- Business already existing -->
                        <v-tab>
                          Existante
                        </v-tab>
                        <v-tab-item class="business">
                          <v-select
                            label="Raison sociale"
                            autocomplete
                            :loading="loadingBusiness"
                            :items="businesses ? businesses.map(business => business.name) : null"
                            :rules="[() => businessName.length > 0 || 'La raison sociale est obligatoire']"
                            v-model="businessName"
                            @keyup.enter="onChangeExistingBusinessName"
                            @blur="onChangeExistingBusinessName"
                            @change=""
                          ></v-select>

                          <v-text-field
                            name="siret"
                            label="SIREN - SIRET - RCS"
                            :error-messages="siretRules"
                            :rules="siretRulesWatched"
                            v-model="siret"
                          ></v-text-field>

                          <v-text-field
                            name="businessWebsiteUrl"
                            label="Website"
                            :error-messages="businessWebsiteUrlRules"
                            :rules="businessWebsiteUrlRulesWatched"
                            v-model="businessWebsiteUrl"
                            hint="http://mon-site_web.com/..."
                          ></v-text-field>
                        </v-tab-item>

                        <!-- Business will be created -->
                        <v-tab>
                          Nouvelle
                        </v-tab>
                        <v-tab-item class="business">
                          <v-text-field
                            label="Raison sociale"
                            suffix="*"
                            :error-messages="newBusinessRules"
                            :rules="[
                              () => businessName.length > 0 || 'La raison sociale est obligatoire',
                              newBusinessRulesWatched
                            ]"
                            v-model="businessName"
                            @change="onChangeNewBusinessName"
                          ></v-text-field>

                          <v-text-field
                            name="siret"
                            label="SIREN - SIRET - RCS"
                            :error-messages="siretRules"
                            :rules="siretRulesWatched"
                            v-model="siret"
                          ></v-text-field>

                          <v-text-field
                            name="businessWebsiteUrl"
                            label="Website"
                            :error-messages="businessWebsiteUrlRules"
                            :rules="businessWebsiteUrlRulesWatched"
                            v-model="businessWebsiteUrl"
                            hint="http://mon-site_web.com/..."
                          ></v-text-field>
                        </v-tab-item>
                      </v-tabs>



                      <v-layout row wrap>
                        <v-flex xs6 offset-xs3>

                          <div class="input-picture">
                            <picture-input
                              ref="pictureInput"
                              @change="onChangeImage"
                              accept="image/jpeg,image/png,image/gif"
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
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
  import PictureInput from 'vue-picture-input'
  import {getAllBusinesses} from "../api";
  import * as regex from "../../../commons/regex";

  export default {
    components: {
      PictureInput
    },
    created () {
      this.loadBusinesses();
    },
    data () {
      return {
        tabActive: null,
        alert: false,
        alertStatus: 'info',
        alertMessage: '',
        valid: false,
        loadingBusiness: false,
        businesses: [],
        firstName: '',
        firstNameRules: [
          v => !!v || 'Le prénom est obligatoire',
          v => (v && v.length <= 20) || 'Le prénom doit être inférieur ou égal à 20 caractères'
        ],
        lastName: '',
        lastNameRules: [
          v => !!v || 'Le nom de famille est obligatoire',
          v => (v && v.length <= 40) || 'Le nom de famille doit être inférieur ou égal à 40 caractères'
        ],
        mail: '',
        mailRules: [
          v => !!v || 'Le mail est obligatoire',
          v => regex.isMailValid(v) || 'Le mail n\'est pas valide'
        ],
        phone: '',
        phoneRules: [],
        job: '',
        jobRules: [
          v => !!v || 'Le poste est obligatoire',
          v => (v && v.length <= 20) || 'Le titre du poste doit être inférieur ou égal à 20 caractères'
        ],
        passwordHide: true,
        password: '',
        passwordRules: [
          v => !!v || 'Le mot de passe est obligatoire',
          v => (v && v.length >= 6) || 'Le mot de passe doit contenir au moins 6 caractères',
          v => (v && v.length <= 30) || 'Le mot de passe doit contenir moins de 30 caractères'
        ],
        passwordRepetition: '',
        passwordRepetitionRules: [
          v => !!v || 'Vous devez répéter votre mot de passe',
          v => (v && v === this.password) || 'Les mots de passe ne correspondent pas'
        ],
        businessName: '',
        businessNameRules: [
          v => !!v || 'La raison sociale est obligatoire',
          v => (v && v.length <= 30) || 'La raison sociale doit contenir moins de 30 caractères'
        ],
        siret: '',
        siretRules: [],
        businessWebsiteUrl: '',
        businessWebsiteUrlRules: [],
        postalCode: '',
        postalCodeRules: [
          v => !!v || 'Le code postal est obligatoire',
          v => regex.isFrenchPostalCodeValid(v) || 'Le code postal est invalide'
        ],
        city: '',
        cityRules: [
          v => !!v || 'La ville est obligatoire'
        ],
        image: null
      }
    },
    computed: {
      phoneRulesWatched () {
        this.phoneRules = this.phone.length > 0 && !regex.isPhoneValid(this.phone)
          ? ['Le numéro de téléphone n\'est pas valide.']
          : [];
        return true;
      },
      siretRulesWatched () {
        this.siretRules = this.siret.length > 0 && !regex.isSiretValid(this.siret)
          ? ['SIRENT/SIRET/RCS invalide.']
          : [];
        return [];
      },
      businessWebsiteUrlRulesWatched () {
        this.businessWebsiteUrlRules = this.businessWebsiteUrl.length > 0
          && !regex.isWebsiteUrlValid(this.businessWebsiteUrl)
          ? ['L\'adresse web est incorrecte.']
          : [];
        return [];
      }
    },
    methods: {
      loadBusinesses() {
        getAllBusinesses()
          .then((businesses) => {
            if (businesses && businesses.length > 0) {
              this.businesses = businesses;
            } else {
              // Will continue until the server responds
              this.alertStatus = 'error';
              this.alertMessage = 'Le préchargement des entreprises existantes a échoué.';
              this.alert = true;

              setTimeout(() => {
                this.loadBusinesses();
              }, 8000);
            }
          })
          .catch((error) => {
            console.log(error);
          });
      },
      onChangeImage (image) {
        if (image) {
          this.image = image;
        }
      },
      onChangeExistingBusinessName () {
        setTimeout(() => {
          for (let i in this.businesses) {
            if (this.businesses[i].name === this.businessName) {
              this.siret = this.businesses[i].siret ? this.businesses[i].siret : '';
              this.businessWebsiteUrl = this.businesses[i].websiteUrl ? this.businesses[i].websiteUrl : '';
            }
          }
        }, 200);
      },
      onChangeNewBusinessName () {
        for (let i in this.businesses) {
          if (this.businessName.toLowerCase() === this.businesses[i].name.toLowerCase()) {

          }

        }
      },
      submit: function (event) {
        if (this.$refs.form.validate()) {
          console.log(this.image);

        }
      }
    }
  }

</script>

<style lang="stylus" scoped>
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

  .intro-inline
    font-family 'Intro Inline'

  .business
    margin-top 20px

  .btn-validate
    z-index 1 !important
    bottom -40px
    right -30px

  .input-picture
    margin-bottom 50px
</style>
