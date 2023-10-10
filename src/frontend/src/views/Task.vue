<template>
  <div class="app-container" id="custom-scrollbar">
    <div class="content">
      <Header />

      <!-- <button @click.prevent="userGuardado">Test de almacenado de usuario</button> -->
          <span>
            <TaskCreate />
          </span>
          <!-- Notificaciones -->
          <div class="notifications">
            <div v-for="(notification, index) in notifications" :key="index" class="notification">
              {{ notification.message }}
            </div>
          </div> 

          <!-- -->
          <span v-for="(task, index) in tasks" :key="index">
            <TaskList :task="task"/>
          </span>
      </div>  
    <Footer />
  </div>
</template>

<script>
import Header from "../components/Header.vue";
import TaskList from "../components/TaskList.vue";
import Footer from "../components/Footer.vue";
import axios from 'axios';
import TaskCreate from "../components/TaskCreate.vue";

export default {
  components: {
    Header,
    TaskList,
    Footer,
    TaskCreate
  },
  data () {
    return {
      tasks: [],
      notifications: [],
    };
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
    },
    // generateNotifications() {
    //   // Logic to generate notifications based on task due dates
    //   const now = new Date();
    //   const tomorrow = new Date();
    //   tomorrow.setDate(tomorrow.getDate() + 1);

    //   console.log(now);
    //   console.log(tomorrow);

    //   const dueTasks = this.tasks.filter(
    //     (task) => task.getDate > now && task.getDate <= tomorrow
    //   );

    //   // Generate notifications for due tasks
    //   this.notifications = dueTasks.map((task) => ({
    //     id: task.id, // Optional ID or unique identifier
    //     message: `Task "${task.title}" is due for tomorrow.`,
    //     timestamp: new Date(), // Optional timestamp
    //   }));
    // },
  },
    mounted () {
      this.get_tasks();
      // this.generateNotifications();
    }
  }
</script>

<style scoped>
.app-container {
  background-image: url("../img/Beach.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  min-height: 100vh;
  overflow: hidden;
  flex-direction: column;
  display: flex;
  /* Otros estilos de diseño si es necesario */
}

.content {
  margin-bottom: 50px;
}

#custom-scrollbar {
  /* Set a specific height and overflow to make the scrollbar visible */
  height: 300px;
  overflow-y: auto;
  scrollbar-width: thin; /* For Firefox */
  scrollbar-color: #FFCD4B #254f18; /* For Firefox */

  /* For Webkit-based browsers (like Chrome and Safari) */
  &::-webkit-scrollbar {
    width: 10px; /* Width of the scrollbar */
  }
  &::-webkit-scrollbar-thumb {
    background-color: #FFCD4B; /* Color of the thumb (scrolling indicator) */
    border-radius: 4px; /* Make the thumb rounded */
  }
  &::-webkit-scrollbar-track {
    background-color: #254f18; /* Color of the scrollbar track */
  }
}
</style>
