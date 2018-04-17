<template>
  <v-layout row wrap>
    <v-flex xs2>
      <v-layout row wrap>
        <v-flex xs6>
          <v-card-media src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfvslSVQuzPcCLmMOW4iGsaieZbqM7nXwr8BbI4BiHVQ9XvMb5" height="150px" width="150px"></v-card-media>
        </v-flex>
        <v-flex xs6>
          <v-btn color="error">Supprimer</v-btn>
          <v-btn color="info">Parcourir</v-btn>
        </v-flex>
        <v-flex xs12>
          <v-text-field
            name="input-1"
            label="Nom"
            id="lastName"
            v-model="user.lastName"
            :rules="[() => user.lastName.length > 0 || 'Le nom est obligatoire']"
            required
            @blur="updateUser"
          ></v-text-field>
        </v-flex>
        <v-flex xs12>
          <v-text-field
            name="input-1"
            label="Prénom"
            id="testing"
            v-model="user.firstName"
            :rules="[() => user.firstName.length > 0 || 'Le prénom est obligatoire']"
            required
            @blur="updateUser"
          ></v-text-field>
        </v-flex>
        <v-flex xs12>
          <v-text-field
            name="input-1"
            label="Email"
            id="mail"
            v-model="user.mail"
            :rules="[() => user.mail.length > 0 || 'Le mail est obligatoire']"
            required
            @blur="updateUser"
          ></v-text-field>
        </v-flex>
        <v-flex xs12>
          <v-text-field
            name="input-1"
            label="Mobile"
            id="Phone"
            v-model="user.phone"
            :error="user.phone === ''"
            @blur="updateUser"
          ></v-text-field>
        </v-flex>
        <v-flex xs12>
          <v-text-field
            name="input-1"
            label="Adresse"
            id="testing"
          ></v-text-field>
        </v-flex>
        <v-flex xs6 class="pr-3">
          <v-text-field
            name="input-1"
            label="Code postal"
            id="testing"
          ></v-text-field>
        </v-flex>
        <v-flex xs6>
          <v-text-field
            name="input-1"
            label="Ville"
            id="testing"
          ></v-text-field>
        </v-flex>
        <v-flex xs12>
          <v-btn color="error">Changer de mot de passe</v-btn>
        </v-flex>
        <v-flex xs6>
          <v-btn color="error" v-if="user.cv" @click.native.stop="dialog = true">Visualiser le CV</v-btn>
          <upload-button title="Ajouter un CV" :selectedCallback="fileSelectedFunc" v-if="!user.cv">
          </upload-button>
        </v-flex>
      </v-layout>
    </v-flex>
    <v-flex xs10 class="pl-5">
      <v-layout row wrap>
        <v-flex xs12>Description</v-flex>
        <v-flex xs12>
          <wysiwyg id="editor" v-model="user.presentation"  @change="updateData" :height="400"/>
        </v-flex>
        <v-flex xs4 class="pl-5">
          <v-card>
            <v-card-title>Disponible</v-card-title>
            <v-divider></v-divider>
            <v-list dense>
              <v-list-tile v-for="(item, index) of items" v-if="!checkUserCompetence(item)" :key="index" :class="{'blue': checkSelectedItem(item)}" @click="selectItem(item)">
                <v-list-tile-content>{{item.name}}</v-list-tile-content>
              </v-list-tile>
            </v-list>
          </v-card>
        </v-flex>
        <v-flex xs2 class="pl-5">
          <v-layout justify-center>
            <v-flex class="no-wrap">
              <v-btn color="info" @click="addItem()">Ajouter</v-btn>
              <v-btn color="error" @click="delItem()">Supprimer</v-btn>
            </v-flex>
          </v-layout>
        </v-flex>
        <v-flex xs4 class="pl-5">
          <v-card>
            <v-card-title>Acquis</v-card-title>
            <v-divider></v-divider>
            <v-list dense>
              <v-list-tile v-for="(item, index) of items" v-if="checkUserCompetence(item)" :key="index" :class="{'blue': checkSelectedItem(item)}" @click="selectItem(item)">
                <v-list-tile-content>{{item.name}}</v-list-tile-content>
              </v-list-tile>
            </v-list>
          </v-card>
        </v-flex>
      </v-layout>
    </v-flex>
    <v-dialog v-model="dialog" max-width="1200px">
      <v-card>
        <v-btn icon @click.native="dialog = false" dark>
          <v-icon>close</v-icon>
        </v-btn>
        <v-spacer></v-spacer>
        <iframe :src="user.cv"
                width="1150px" height="730px"/>
      </v-card>
    </v-dialog>

  </v-layout>
</template>

<script>
import UploadButton from '@/components/UploadButton'
import pdf from 'vue-pdf'

export default {

  name: 'StudentProfil',
  data: function () {
    return {
      requiredField: ['lastName', 'firstName', 'mail'],
      msg: 'Welcome to Your Vue.js App',
      cv: null,
      dialog: false,
      sendPresentation: false,
      itemSelected: []
    }
  },
  components: {
    UploadButton,
    pdf
  },
  computed: {
    items: function () {
      return this.$store.state.competence.competences
    },
    user: function () {
      return this.$store.state.user.userProfil
    }
  },
  mounted: function () {
    this.$store.dispatch('getUserInfo')
    this.$store.dispatch('getAllCompetences')
  },
  methods: {
    checkedItem: function (item) {
      return this.itemSelected.findIndex(function (element) {
        return element.id === item.id
      })
    },
    selectItem: function (item) {
      var found = this.checkedItem(item)
      if (found === -1) {
        this.itemSelected.push(item)
      } else {
        this.itemSelected.splice(found, 1)
      }
    },
    checkSelectedItem: function (item) {
      var found = this.itemSelected.find(function (element) {
        return element.id === item.id
      })
      return typeof found !== 'undefined'
    },
    addItem: function () {
      this.items.forEach(function (item) {
        if (this.checkUserCompetence(item) === false && this.checkedItem(item) !== -1) {
          this.user.competences.push(item)
          this.selectItem(item)
        }
      }, this)
      this.updateUser(this.user)
    },
    delItem: function () {
      this.items.forEach(function (item) {
        if (this.checkUserCompetence(item) === true && this.checkedItem(item) !== -1) {
          var found = this.user.competences.findIndex(function (element) {
            return element.id === item.id
          })
          this.user.competences.splice(found, 1)
          this.selectItem(item)
        }
      }, this)
      this.updateUser(this.user)
    },
    fileSelectedFunc: function (file) {
      this.$store.dispatch('updateCv', { user: this.user, file: file })
    },
    updateUser: function () {
      let error = null
      this.sendPresentation = false
      this.requiredField.forEach(field => {
        if (this.user[field] === '') {
          error = true
        }
      })
      if (error) {
        return
      }
      this.$store.dispatch('updateUserInfo', this.user)
    },
    updateData: function (content) {
      this.user.presentation = content
      if (!this.sendPresentation) {
        this.sendPresentation = true
        setTimeout(() => {
          this.updateUser()
        }, 3000)
      }
    },
    checkUserCompetence: function (competence) {
      var found = this.user.competences.find(function (element) {
        return element.id === competence.id
      })
      return typeof found !== 'undefined'
    }
  }
}
</script>
<style scoped>
  .listboxbuttons {
    display: -webkit-box;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  #editor {
    background-color: #f6f6f6;
    color: #000;
  }

  .card {
    overflow-x: scroll;
    height: 228px !important;
  }
</style>
