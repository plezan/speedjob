<template>
  <v-app id="inspire" dark>

    <v-toolbar app fixed clipped-left>
      <v-toolbar-title>SpeedJob</v-toolbar-title>
    </v-toolbar>

    <v-content>
      <v-container fluid fill-height>
        <v-layout justify-center align-center>
          <router-view></router-view>
            <div>
              <form id="form" class="form">
                <table>
                  <thead>
                    <tr>
                      <th colspan="2"><h2>Informations</h2></th>
                    </tr>
                  </thead>
                  <tbody>

                    <tr>
                      <td>
                        <input class="oblg" type="text" id="name" placeholder="Nom*">
                      </td>
                      <td>
                        <input class="oblg" type="text" id="raisonSociale" placeholder="Raison Sociale*">
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <input class="oblg" type="text" id="firstname" placeholder="Prenom*">
                      </td>
                      <td>
                        <input class="oblg" type="text" id="adress" placeholder="Adresse*">
                      </td>
                    </tr>
                    <tr>
                      <td clas="input">
                        <input class="oblg" type="text" id="mail" placeholder="Mail*">
                      </td>
                      <td clas="input">
                        <input class="oblg" type="text" id="postalcode" placeholder="Code Postal*">
                      </td>
                    </tr>
                    <tr>
                      <td clas="input">
                        <input class="oblg" type="text" id="telperso" placeholder="telephone*">
                      </td>
                      <td clas="input">
                        <input class="oblg" type="text" id="telpro" placeholder="telephone*">
                      </td>
                    </tr>
                    <tr>
                      <td clas="input">
                        <input class="" type="text" id="job" placeholder="Fonction">
                      </td>
                      <td clas="input">
                        <input class="oblg" type="text" id="mail2" placeholder="Mail*">
                      </td>
                    </tr>
                    <tr>
                      <td clas="input">
                        <input class="oblg" type="password" id="password" placeholder="Mot de Passe">
                      </td>
                      <td clas="input" rowspan="2">
                        <img id="mylogo" src="">
                        <input type="file" id="logo" accept="image/*" onchange="loadImage(event)">
                      </td>
                    </tr>
                    <tr>
                      <td clas="input">
                        <input class="oblg" type="password" id="password2" placeholder="Confirmer le mot de passe">
                      </td>
                    </tr>
                  </tbody>
                </table>
                        <div id="errormessage">
                          <p>{{errormessage}}</p>
                        </div>
                        <div id="submit">
                          <button v-on:click="submit">Valider</button>
                        </div>
              </form>
            </div>
        </v-layout>
      </v-container>
    </v-content>

    <v-footer app fixed>
      <span>&copy; IMIE 2018</span>
    </v-footer>
  </v-app>
</template>

<script>
export default {
  data: function () {
    return {
      errormessage: 'attention',
      test: 'test'
    }
  },
  methods: {
    loadImage: function (event) {
      var img = document.getElementById('mylogo')
      this.test = 'img'
      img.src = URL.createObjectURL(event.target.files[0])
      this.test = img.src
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

<style scoped>
td {
padding: 5px;
padding-right: 50px;
border: 2px solid #48b9c7;
text-color: #ffec38
}
form tr:last-child {
  border: none
}

#errormessage {
visibility: hidden;
color: #ef3d55;
text-align: center;
padding: 5px;
}

button {
background-color: #48b9c7;
padding: 5px;
}

#submit {
margin-left: auto
}
</style>
