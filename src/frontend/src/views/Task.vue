<template>
  <div class="app-container">
    <Header />

    <button @click.prevent="userGuardado">Test de almacenado de usuario</button>

    <span v-for="(task, index) in tasks" :key="index">
      <TaskList :task="task"/>
    </span>    
    <Footer />
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import TaskList from "@/components/TaskList.vue";
import Footer from "@/components/Footer.vue";
import axios from 'axios';

export default {
  components: {
    Header,
    TaskList,
    Footer,
  },
  data () {
    return {
      //id: 10,
      tasks: []
    }
  },

  methods: {
    async get_tasks(){
      try {
        const user = JSON.parse(sessionStorage.getItem("user"));
        const token = localStorage.getItem("token"); // Obtén el token JWT del almacenamiento local

        console.log(user.id_usuario)
        const response = await axios.get(`/tareas/${user.id_usuario}`,{
          headers: {
            Authorization: `Bearer ${token}`,
          }
        });
        this.tasks = response.data;
      } catch (error){
        console.log(error);
      }
    },
    async userGuardado(){
      const user = JSON.parse(sessionStorage.getItem("user"));
      if (user) {
        // Puedes utilizar la información del usuario
            console.log(user);
      } else {
        // El usuario no ha iniciado sesión o la sesión ha expirado
            console.log('no se ha pillado el usuario, puede que no haya iniciado sesion o que haya expirado la sesion')
      }
    }
  },

  mounted () {
    this.get_tasks();
  }
};
</script>

<style scoped>
.app-container {
  background-image: url("@/assets/fondo.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  min-height: 100vh;
  flex-direction: column;
  display: flex;
  /* Otros estilos de diseño si es necesario */
}
</style>
