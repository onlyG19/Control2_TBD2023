<template>
  <div class="login-page">
    <div class="form">
      <form class="register-form" v-show="!login">
        <input
          class="error-input"
          type="text"
          placeholder="username"
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
          placeholder="password"
          v-model="password"
          @blur="v$.password.$touch"
        />
        <div class="error-div">
          <p class="error-text" v-show="v$.password.$error">
            Se necesita contraseña
          </p>
        </div>
        <input
          class="error-input"
          type="text"
          placeholder="email address"
          v-model="email"
          @blur="v$.email.$touch"
        />
        <div class="error-div">
          <p class="error-text" v-show="v$.email.$error">Se necesita correo</p>
        </div>
        <button @click.prevent="registerUser">create</button>
        <p class="message">
          Already registered? <a href="#" @click="changeClean">Sign In</a>
        </p>
      </form>

      <form class="login-form" v-show="login">
        <input type="text" placeholder="username" />
        <div class="error-div">
          <p class="error-text" v-show="false">Usuario ingresado no existe</p>
        </div>
        <input type="password" placeholder="password" />
        <div class="error-div">
          <p class="error-text" v-show="false">
            La contraseña no coincide con el usuario
          </p>
        </div>
        <button @click.prevent="loginUser">login</button>
        <p class="message">
          Not registered?
          <a href="#" @click="login = !login">Create an account</a>
        </p>
        <a href="/Task">A las tareas</a>
      </form>
    </div>
  </div>
</template>

<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

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
  background: #b4c5e4;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  border-radius: 0.5rem;
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
  background: #3066be;
  width: 100%;
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
  background: #090c9b;
}
.form .message {
  margin: 15px 0 0;
  color: #fbfff1;
  font-size: 14px;
}
.form .message a {
  color: #3066be;
  text-decoration: none;
}
.form .register-form {
  display: block;
}
body {
  background-color: #fbfff1;
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>

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
        if (response.status == 200) {
          console.log("Registro exitoso, status 200");
          console.log(response);
          console.log(response.data.message);
          console.log("Error? : " + response.data.error);
        } else {
          console.error("Error en el registro");
        }
      } catch (error) {
        console.log("Error en la solicitud, error");
        console.log(error.response.data.message);
        console.log("Error? : " + error.response.data.error);
      }
    },

    async loginUser() {
      const userData = {
        nombre: this.username,
        password: this.password,
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
          this.$router.push("/dashboard"); // Reemplaza '/dashboard' con la ruta de tu página principal
        } else {
          console.error("Error en el inicio de sesión");
        }
      } catch (error) {
        console.log("Error en la solicitud");
        console.log(error);
      }
    },
  },
};

/*
  ###### FIN VALIDACIONES ##########
*/
// Obtener todos los elementos con la clase "message a"
var messageLinks = document.querySelectorAll(".message a");

// Agregar un controlador de eventos de clic a cada enlace
messageLinks.forEach(function (link) {
  link.addEventListener("click", function () {
    // Obtener todos los elementos <form>
    var forms = document.querySelectorAll("form");

    // Iterar a través de los elementos <form> y aplicar la animación a cada uno
    forms.forEach(function (form) {
      // Obtener el estilo actual del formulario
      var computedStyle = window.getComputedStyle(form);

      // Obtener la altura actual y la opacidad actual
      var currentHeight = parseFloat(computedStyle.height);
      var currentOpacity = parseFloat(computedStyle.opacity);

      // Definir la velocidad de la animación (puedes ajustarla según tus necesidades)
      var animationSpeed = 300; // milisegundos

      // Verificar si el formulario está visible o oculto
      if (currentHeight > 0 && currentOpacity > 0) {
        // Si está visible, ocultarlo gradualmente
        var targetHeight = 0;
        var targetOpacity = 0;
      } else {
        // Si está oculto, mostrarlo gradualmente
        var targetHeight = form.scrollHeight;
        var targetOpacity = 1;
      }

      // Animar la altura y la opacidad del formulario
      var startTime = null;
      function animate(time) {
        if (!startTime) startTime = time;
        var progress = (time - startTime) / animationSpeed;
        if (progress < 1) {
          form.style.height =
            currentHeight + (targetHeight - currentHeight) * progress + "px";
          form.style.opacity =
            currentOpacity + (targetOpacity - currentOpacity) * progress;
          requestAnimationFrame(animate);
        } else {
          form.style.height = targetHeight + "px";
          form.style.opacity = targetOpacity;
        }
      }

      requestAnimationFrame(animate);
    });
  });
});
</script>
