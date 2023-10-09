<template>
  <div class="app-container">
    <Header />
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
      id: 10,
      tasks: []
    }
  },

  methods: {
    async get_tasks(){
      try {
        const response = await axios.get(`http://localhost:8080/tareas/${this.id}`);
        this.tasks = response.data;
      } catch (error){
        console.log(error);
      }
    },

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
