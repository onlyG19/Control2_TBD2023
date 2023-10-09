<template>
  <body>
    <div class="login-page">
      <div class="form">
        <form class="register-form" v-show="!login">
          <h1 class="title">Registro</h1>

          <div v-if="showMessage" :class="messageClass">
              {{ messageText }}
    
          </div>



          <input
            class="error-input"
            type="text"
            placeholder="Nombre de Usuario"
            v-model="username"
            @blur="v$.username.$touch"
          />
          <div class="error-div">
            <p class="error-text" v-show="v$.username.$error">
              Se necesita nombre de usario
            </p>
          </div>
          <input
            class="error-input"
            type="password"
            placeholder="Contraseña"
            v-model="password"
            @blur="v$.password.$touch"
          />
          <div class="error-div">
            <p class="error-text" v-show="v$.password.$error">
              Se necesita contraseña
            </p>
          </div>
          <!-- <input
            class="error-input"
            type="text"
            placeholder="email address"
            v-model="email"
            @blur="v$.email.$touch"
          />
          <div class="error-div">
            <p class="error-text" v-show="v$.email.$error">Se necesita correo</p>
          </div> -->
          <button @click.prevent="registerUser">Registro</button>
          <p class="message">
            ¿Ya estás registrado? <a href="#" @click="changeClean">Ingresar</a>
          </p>
        </form>

        <form class="login-form" v-show="login">
          <h1 class="title">Ingreso</h1>
          <input type="text" placeholder="Nombre de Usuario" v-model="usernamel"/>
          <div class="error-div">
            <p class="error-text" v-show="false">Usuario ingresado no existe</p>
          </div>
          <input type="password" placeholder="Contraseña" v-model="passwordl"/>
          <div class="error-div">
            <p class="error-text" v-show="false" >
              La contraseña no coincide con el usuario
            </p>
          </div>
          <button @click.prevent="loginUser">Ingresar</button>
          <p class="message">
            ¿No estás registrado?
            <a href="#" @click="login = !login">Registrate</a>
          </p>
        </form>
      </div>
    </div>
  </body>
</template>

<script>
/*
  ######## VALIDACIONES ##############3
*/

import { useVuelidate } from "@vuelidate/core";
import { required, email } from "@vuelidate/validators";
import axios from "axios";

export default {
  setup() {
    return { v$: useVuelidate() };
  },
  data() {
    return {
      username: "",
      email: "",
      password: "",
      login: true,
      usernamel: "",
      passwordl:"",
      showMessage: false,
      messageText: "",
      messageClass: "",
    };
  },
  validations() {
    return {
      username: { required },
      email: { required, email },
      password: { required },
    };
  },
  methods: {
    async changeClean() {
      this.login = !this.login;
      this.v$.$reset();
    },

    async registerUser() {
      const userData = {
        nombre: this.username,
        password: this.password,
      };
      try {
        const response = await axios.post("/usuario/register", userData);
        if (response.status == 200) {  // Registro Exitoso
          console.log("Registro exitoso, status 200");
          console.log(response);
          console.log(response.data.message);
          console.log("Error? : " + response.data.error);

          this.showMessage= true;
          this.messageText= "Registro exitoso.";
          this.messageClass= "success-message";

        } else {
          console.error("Error en el registro");
          this.showMessage = true;
          this.messageText = "Error en el registro. Por favor, inténtalo de nuevo.";
          this.messageClass = "error-message";
        }
      } catch (error) {
        console.log("Error en la solicitud, error");
        console.log(error.response.data.message);
        console.log("Error? : " + error.response.data.error);
        this.showMessage = true;
        this.messageText = "Error en el registro. Por favor, inténtalo de nuevo.";
        this.messageClass = "error-message";

      }
    },

    async loginUser() {
      const userData = {
        nombre: this.usernamel,
        password: this.passwordl,
      };

      try {
        const response = await axios.post("/usuario/login", userData); // Reemplaza '/login' con la ruta correcta de tu backend para el inicio de sesión
        if (response.status === 200) {
          console.log("Inicio de sesión exitoso, status 200");
          console.log(response.data.message);
          console.log("Error? : " + response.data.error);

          // Almacenar el token de autenticación en el almacenamiento local (localStorage) o en una cookie
          const token = response.data.token;
          localStorage.setItem("token", token); // Almacena el token en localStorage

          // Redirige al usuario a la página principal o a donde desees después del inicio de sesión
          // Puedes usar Vue Router para manejar la navegación
          this.$router.push("/task"); // Reemplaza '/dashboard' con la ruta de tu página principal
        } else {
          console.error("Error en el inicio de sesión");
        }
      } catch (error) {
        console.log("Error en la solicitud");
        console.log(error);
        alert("Credenciales incorrectas, Ingrese nuevamente");
      }
    },
  },
};

/*
  ###### FIN VALIDACIONES ##########
*/
</script>

<style scoped>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

body {
  text-shadow: 1px 1px 2px rgba(0,0,0,0.47);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  margin: 0;
  /* background-color: #3c3744; */
  background-image: url('../img/Mountains.jpg');
  min-height: 100vh;
  overflow: hidden;
}
.login-page {
  width: 360px;
  padding: 8% 0 0;
  margin: auto;
}
.error-div {
  height: 15px;
}
.error-text {
  font-size: 75%;
  color: #eb2f06;
  margin: 0;
  text-align: left;
  line-height: 1;
}
.form {
  position: relative;
  z-index: 1;
  background: #8C7A69;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  border-radius: 15rem;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #186826;
  width: 75%;
  border: 0;
  padding: 15px;
  color: #fbfff1;
  font-size: 15px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form button:hover,
.form button:active,
.form button:focus {
  background: #16A294;
}
.form .message {
  margin: 15px 0 0;
  color: #fbfff1;
  font-size: 14px;
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-shadow: 1px 1px 2px rgba(0,0,0,0.47);
}
.form .message a {
  color: #254f18;
  text-decoration: none;
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-shadow: 1px 1px 2px rgba(0,0,0,0.47);
}
.form .register-form {
  display: block;
}
.title {
  /* font-family: "Roboto", sans-serif; */
  font-family: Georgia, serif;
  font-weight: 400;
  font-size: 35px;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #fbfff1;
  text-shadow: 3px 1px 2px rgba(0,0,0,0.47);
}

.success-message {
  background-color: #4caf50;
  color: white;
  padding: 10px;
  margin: 10px 0;
}

.error-message {
  background-color: #f44336;
  color: white;
  padding: 10px;
  margin: 10px 0;
}

</style>
