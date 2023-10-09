<template>
    <!-- Contenedor de tareas -->
    <section class="container">
      <!-- Iterar a través de las tareas -->
      <article v-for="(task, index) in tasks" :key="index">
        <!-- Título de la tarea -->
        <h2 :style="taskTitleStyle">{{ task.title }}</h2>
        <!-- Estado de la tarea -->
        <p :style="taskStatusStyle">{{ task.status }}</p>
        <!-- Fecha de la tarea si está presente -->
        <p v-if="task.date">{{ task.date }}</p>
        <!-- Descripción de la tarea -->
        <p>{{ task.description }}</p>
        <!-- Botones de edición y eliminación -->
        <div class="container">
          <button class="edit" :style="editButtonStyle">Editar</button>
          <button class="delete" :style="deleteButtonStyle">Eliminar</button>
        </div>
      </article>
    </section>
  </template>
      
  <script>
  export default {
    name: "TaskList",
    data() {
      return {
        tasks: [
          {
            title: "Tarea 1:",
            status: "Pendiente",
            date: "yyyy/mm/dd",
            description: "Hacer x"
          },
          {
            title: "Tarea 2:",
            status: "Completada",
            date: "yyyy/mm/dd",
            description: "Hacer y"
          },
          // Puedes agregar más tareas aquí
        ],
      };
    },
    computed: {
      // Estilos de la fuente para el título de la tarea
      taskTitleStyle() {
        return {
          fontFamily: "NuevaFuente, sans-serif",
          // Otros estilos de fuente si es necesario
        };
      },
      // Estilos de la fuente para el estado de la tarea
      taskStatusStyle() {
        return {
          fontFamily: "NuevaFuente, sans-serif",
          // Otros estilos de fuente si es necesario
        };
      },
      // Estilos de la fuente para los botones de edición
      editButtonStyle() {
        return {
          fontFamily: "NuevaFuente, sans-serif",
          // Otros estilos de fuente si es necesario
        };
      },
      // Estilos de la fuente para los botones de eliminación
      deleteButtonStyle() {
        return {
          fontFamily: "NuevaFuente, sans-serif",
          // Otros estilos de fuente si es necesario
        };
      },
    },
  };
  </script>
      
  <style>
  /* Estilos específicos para el componente TaskList */
  section article {
    color: black;
    background-color: #b4c5e4;
    border-radius: 0.2rem;
    margin: 1rem 0;
    padding: 0.25rem 0;
    box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
  }
  
  section article h2,
  section article p {
    margin: 1.2rem;
  }
  
  .container {
    width: 400px;
    margin: 0 auto;
  }
  
  .delete {
    background: #3c3744;
  }
  
  button {
    font-family: "NuevaFuente, sans-serif"; /* Cambio de fuente */
    text-transform: uppercase;
    outline: 0;
    background: #3066be;
    width: 35%;
    border: 0;
    padding: 15px;
    color: #fbfff1;
    font-size: 15px;
    -webkit-transition: all 0.3s ease;
    transition: all 0.3s ease;
    cursor: pointer;
    margin-left: 3rem;
  }
  
  .delete {
    background: #3c3744;
  }
  
  button:hover,
  button:active,
  button:focus {
    background: #090c9b;
  }
  
  .container {
    display: block;
  }
  </style>
