<template>
    <!-- Contenedor de tareas -->
    <section v-if="!eliminado" class="container">
      <!-- Iterar a través de las tareas -->
      <article>
        <!-- Título de la tarea -->
        <h2 v-show="!edicion" :style="taskTitleStyle">{{ title }}</h2>
        <input v-show="edicion" :style="taskTitleStyle" type="text" v-model="title" />
        <!-- Estado de la tarea -->
        <p :style="taskStatusStyle">{{ status }}</p>
        <!-- Fecha de la tarea si está presente -->
        <p v-if="task.vence_tarea">{{ date }}</p>
        <!-- Descripción de la tarea -->
        <p v-show="!edicion">{{ description }}</p>
        <textarea v-show="edicion" type="text" v-model="description" @input="autosize" ref="textarea"></textarea>
        <!-- Botones de edición y eliminación -->
        <div class="container">
          <button class="edit" :style="editButtonStyle" @click="edit">{{ textoBotonEdicion }}</button>
          <button class="delete" :style="deleteButtonStyle" @click="eliminar">{{ textoBotonEliminar }}</button>
        </div>
      </article>
    </section>
  </template>
      
<script>
  import axios from 'axios'

  export default {
    name: "TaskList",
    props: {
      task: {
        type: Object,
        required: true
      },
    },
    data() {
      return {
        edicion: false,
        eliminado: false,
        textoBotonEdicion: 'Editar',
        textoBotonEliminar: 'Eliminar',
        title: '',
        status: '',
        date: '',
        description: '',
      }
    },
    mounted () {
      this.title = this.task.nombre_tarea;
      this.status = this.task.estado_tarea;
      this.date = this.task.vence_tarea;
      this.description = this.task.desc_tarea;
    },
    // reutilizacion de botones al cambiar la funcion por la edicion
    methods: {
      autosize() {
        // ajustar el textarea al tamaño del contenido
        let element = this.$refs["textarea"];
        element.style.height = "18px";
        element.style.height = element.scrollHeight + "px";
    },
      edit () {
        if(this.edicion){
          this.textoBotonEdicion = 'Editar'
          this.task.nombre_tarea = this.title;
          this.task.estado_tarea = this.status;
          this.task.vence_tarea = this.date;
          this.task.desc_tarea = this.description;
          try {

            const token = localStorage.getItem("token"); // Obtén el token JWT del almacenamiento local
            axios.put(`http://localhost:8080/tarea/${this.task.id_tarea}`, this.task,{
              headers: {
              Authorization: `Bearer ${token}`,
              }
              });
          } catch (e){
            console.log(e);
          }
        }else {
          this.textoBotonEdicion = 'Guardar';
          this.textoBotonEliminar = 'Cancelar';
        }
        this.edicion = !this.edicion;
    },
      eliminar (){
        if(this.edicion){
          this.title = this.task.nombre_tarea;
          this.status = this.task.estado_tarea;
          this.date = this.task.vence_tarea;
          this.description = this.task.desc_tarea;
          this.textoBotonEliminar = 'Eliminar';
          this.edicion = !this.edicion;
          this.textoBotonEdicion = 'Editar'
        }else{
          try{
            const token = localStorage.getItem("token"); // Obtén el token JWT del almacenamiento local

            axios.delete(`http://localhost:8080/tarea/${this.task.id_tarea}`,{
            headers: {
            Authorization: `Bearer ${token}`,
          }
          });
            this.eliminado = true;
          }catch (e){
            console.log(e);
          }
        }
      },

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
  section article p{
    margin: 1.2rem;
  }

  p{
    font-size: 15px;
  }

  .container {
    width: 400px;
    margin: 0 auto;
  }
  
  .delete {
    background: #3c3744;
  }

  input,
  textarea{
    outline: none;
    width: 90%;
  }

  input{
    margin: 0.8em 0.8em 0 0.8em;
    font-size: 22px;

  }

  textarea{
    margin: 0 0.8em 0.8em 0.8em;
    height: auto;
    min-height: 20px;
    overflow-y: hidden;
    resize: none;
    font-size: 13px;
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
  
  button:hover {
    background: #090c9b;
  }
  
  .container {
    display: block;
  }
  </style>
