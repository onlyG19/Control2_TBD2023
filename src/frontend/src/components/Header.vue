<template>
    <!-- Encabezado del sitio -->
    <header>
      <!-- Cambio de fuente para "My To-Do" -->
      <h1 class="title" style="font-family: sans-serif;">My To-Do</h1>
    </header>
  </template>
  
  <script>
  export default {
    name: 'Header'
  }
  </script>
  
  <style scoped>
  /* Estilos específicos para el componente Header */
  header {
    color: #fbfff1;
    font-size: 1.6rem;
    padding: 2rem;
    margin: 0;
  }
  
  header h1 {
    text-align: center;
    margin: 0;
    text-shadow: 1px 1px 2px rgba(0,0,0,0.47);
  }
  </style>
