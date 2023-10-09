<template>
  <div class="app-container">
    <Header />
    <TaskList />
    <Footer />
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import TaskList from "@/components/TaskList.vue";
import Footer from "@/components/Footer.vue";

export default {
  components: {
    Header,
    TaskList,
    Footer,
  },
};
</script>

<style>
.app-container {
  background-image: url("@/assets/fondo.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  /* Otros estilos de diseño si es necesario */
}
</style>