<template>
    <!-- Contenedor de tareas -->
    <section v-if="!eliminado" v-show="!mostrarTarea" class="container">
      <!-- Iterar a través de las tareas -->
      <article>
        <!-- Título de la tarea -->
        <div>
          <h2 class="word-wrap" v-show="!edicion" :style="taskTitleStyle">Nombre: {{ title }}</h2>
          <input v-show="edicion" :style="taskTitleStyle" type="text" v-model="title" />
        </div>
        
        <!-- Estado de la tarea -->
        <div>
          <h2  v-show="!edicion" :style="taskStatusStyle">Estado de la tarea: {{ status }}</h2>
          
           <!-- Estado de la tarea (solo se muestra en modo edición) -->
           <div v-show="edicion">
              <label class="input-text" for="estado">Estado de la tarea:</label>
              <select id="estado" v-model="status">
                <option value="Pendiente">Pendiente</option>
                <option value="En Curso">En Curso</option>
               <option value="Terminado">Terminado</option>
              </select>
           </div>
        </div>
        
        <!-- Fecha de la tarea si está presente -->
        <div>
          <h2 v-show="!edicion" :style="taskStatusStyle"  v-if="task.vence_tarea">Fecha Vencimiento: {{ date }}</h2>
          
          <div v-show="edicion">
  
            <div class="form-group">
              <div class="input-with-text">
                <span class="input-text">Fecha de Vencimiento:</span>
                <input type="date" id="date" v-model="date" required>
              </div>
            </div>

          </div>
          

        </div>
        
        <!-- Descripción de la tarea -->
        <div>
          <p :style="taskStatusStyle" class="word-wrap" v-show="!edicion">{{ description }}</p>
          <div v-show="edicion">
            <textarea v-show="edicion" type="text" v-model="description" @input="autosize" ref="textarea" class="input-text"></textarea>

          </div>
        </div>
        
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
      filter: {
        type: String
      },
    },
    data() {
      return {
        hola: '',
        edicion: false,
        eliminado: false,
        textoBotonEdicion: 'Editar',
        textoBotonEliminar: 'Eliminar',
        title: '',
        status: '',
        date: '',
        description: ''
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
          this.textoBotonEliminar = 'Eliminar'
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
            console.log(this.task.vence_tarea);
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
      mostrarTarea() {
        if(this.filter === ''){
          return false;
        }else {
          return !(this.title.toLowerCase().includes(this.filter.toLowerCase())
                  || this.description.toLowerCase().includes(this.filter.toLowerCase()))
        }
      },
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
      
<style scoped>
  /* Estilos específicos para el componente TaskList */
  .word-wrap {
    overflow-wrap: break-word;
  } 

  section article {
    color: black;
    background-color: white;
    border-radius: 0.2rem;
    margin: 0.35rem 0;
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
    font-family: "sans-serif"; 
    text-transform: uppercase;
    outline: 0;
    background: #E5B80B;
    width: 35%;
    border: 0;
    padding: 15px;
    color: #fbfff1;
    font-size: 15px;
    -webkit-transition: all 0.3s ease;
    transition: all 0.3s ease;
    cursor: pointer;
    margin-left: 3rem;
    border-radius: 4rem;
  }
  
  .delete {
    background: #254f18;
    background-color: #254f18;
  }
  
  button:hover {
    background: #16a294;
  }
  
  .container {
    display: block;
  }

  .input-with-text {
  display: flex; /* Para que los elementos estén en línea */
  align-items: center; /* Centrar verticalmente los elementos */
}

.input-text {
  margin-right: 10px; /* Espacio entre el texto y el input */
  margin-left: 20px;
}

</style>
