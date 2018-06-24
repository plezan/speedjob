<template>
  <v-app id="app" dark>
    <v-navigation-drawer clipped fixed v-model="drawer" app>
      <v-list dense>
        <v-list-tile>
          <v-list-tile-action>
            <v-icon>dashboard</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="/profilEntreprise">Profil</router-link>
            <v-list-tile-title>Dashboard</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile>
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
      <v-toolbar-side-icon ></v-toolbar-side-icon>
      <v-toolbar-title>SpeedJob</v-toolbar-title>
      <v-spacer></v-spacer>

      <div class="text-xs-center">
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

  </div>
    </v-toolbar>
    <v-content>
      <v-container>
          <div>
          <v-layout justify-center align-center row wrap>
            <router-view></router-view>

            <v-flex xs12>
              <v-subheader></v-subheader>
              <v-text-field v-model="offe_title" name="offe_title" label="Titre de l'offre" id="titre"></v-text-field>
            </v-flex>

            <v-flex>
              <v-spacer></v-spacer>
                <v-text-field v-model="type_name" name="type_name" label="Type de contrat" id="contrat"></v-text-field>
            </v-flex>
          </v-layout>
          </div>
          <v-spacer></v-spacer>

          <div>
            <div class="contrat">
            <p>Début du contrat : {{ offe_startDate }}</p>
            <p>Fin du contrat : {{ offe_endDate }} </p>
            </div>
            <v-card color="grey darken-3" flat>
            <v-card-text>
            <v-subheader dark>Description de l'offre</v-subheader>

            <v-layout row wrap>
            <v-flex xs10 offset-xs1 required>
              <v-text-field v-model="offe_description" name="offe_description" label="Tapez ici..." textarea dark></v-text-field>
            </v-flex>
            </v-layout>

            </v-card-text>
            </v-card>
          </div>
      </v-container>

      <v-container class="Time">

      <div class="children">
        <v-subheader v-text="'Date de début'"></v-subheader>
        <v-date-picker v-model="offe_startDate" name="offe_startDate" max-height="200" :landscape="landscape" :reactive="reactive"></v-date-picker>
      </div>

       <div class="children">
            <v-subheader v-text="'Date de fin'"></v-subheader>
        <v-date-picker v-model="offe_endDate" name="offe_endDate" max-height="200" :landscape="landscape" :reactive="reactive"></v-date-picker>
      </div>
      </v-container>

      <v-layout>
      <v-flex xs12 sm6>
        <v-subheader v-text="'Liste de compétences que vous recherchez'"></v-subheader>
      </v-flex>
      <v-flex xs6 sm6>
        <v-select
          label="Select"
          :items="competences"
          v-model="e7"
          multiple
          max-height="200"
          chips
          hint="Sélectionnez les compétences pour votre annonce"
          persistent-hint
        ></v-select>
      </v-flex>
    </v-layout>

      <div class="ContactBusiness">
      <v-menu transition="scale-transition">
      <v-btn dark color="primary" slot="activator">Contacts</v-btn>
      <v-list>
        <v-list-tile v-for="n in 5" >
          <v-list-tile-title v-text="'Item ' + n"></v-list-tile-title>
        </v-list-tile>
      </v-list>
      </v-menu>
      </div>

      <v-flex align-end>
      <div>
        <v-btn depressed small color="primary" v-on:click="brouillon()" class="btn">Enregistrer comme brouillon</v-btn>
      </div>
      <div>
        <v-btn depressed small color="secondary" v-on:click="publier()" class="btn" >Publier</v-btn>
      </div>
      </v-flex>
    </v-content>
    <v-footer app fixed>
      <span>&copy; IMIE 2018</span>
    </v-footer>
  </v-app>
</template>

<script>

export default {
  el: '#app',
  data: () => ({
       return: {
        todos: [],
        e7: [],
        competences: [
          'Angular2','Angular.js', 'CSS3', 'Javascript', 'Jquery',
          'HTML5', 'PHP7', 'Python', 'Java',
          'Node.js', 'C#', 'C++',
          'Go', 'TypeScript', 'Github', 'Webpack', 'Vue.JS',
          'Algorithmes', 'Bases de données', 'MySql', 'MongoDB', 'NoSql',
          'GraphQL', 'PostGres', 'CMS', 'Machine Learning',
          'Elastic Search', 'Symphony', 'Laravel', 'React',
          'React Native', 'Android', 'Swift', 'Django',
          'XCode', 'Ionic', 'Docker', 'Kubernetes',
          '.NET', 'POO', 'Méthodes Agiles',
          'Ajax', 'JWT',
          'Bootstrap', 'SASS',
          'Jenkins', 'Jest', 'UX', 'NGINX', 'NPM',
        ],
      },
      offe_id: '',
      offe_title: '',
      type_name: '',
      offe_status: '',
      offe_description: '',
      offe_startDate: '',
      offe_endDate: '',
      comp_id: '',
      offe_status: '',
      type_id: '',
      type_name: '',

  }),

  computed: {
    offer: function (){
      return this.$store.state.offer
    },
    user: function(){
    return this.$store.state.user
    }

  },

  mounted: {
  this.$store.dispatch('publishOffer')
  this.$store.dispatch('registerUnfinishedOffer')
  }


  methods: {

      let newOffer = {
        offe_id: this.offe_id,
        offe_title: this.offe_title,
        type_name: this.type_name,
        offe_description: this.offe_description,
        offe_startDate: this.offe_startDate,
        offe_endDate: this.offe_endDate,
        comp_id: this.comp_id,
        competences: this.competences,
        offe_status: this.offe_status,
        type_id: this.type_id,


      return newOffer = JSON.stringify(new newOffer);
    },

    isFilled: function (newOffer){

    },



  }

</script>

<style scoped>

.notifications  {
  float: right;
}

.p{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  margin-right: 20px;
}

.Time{
  display: flex;
  flex-direction: row;
  margin-left: 20px;
  margin-right: 20px;
  flex-wrap: wrap;
};

.btn{
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}
</style>
