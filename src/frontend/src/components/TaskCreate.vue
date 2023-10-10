<template>
    <div>
      <!-- Button to Toggle Form Visibility -->
      <button class="crearButton" @click="toggleForm">Crear Tarea</button>
      <div class="form-container">
        <!-- Form -->
        <form class='custom-border' v-if="showForm" @submit.prevent="submitForm">
          <!-- Title Input -->
          <div class="form-group">
            <label for="title">Título:</label>
            <input type="text" id="title" placeholder="Titulo" v-model="task.title" required>
          </div>
    
          <!-- Date Input with Calendar Selector -->
          <div class="form-group">
            <label for="date">Fecha:</label>
            <input type="date" placeholder="Fecha" id="date" v-model="task.date" required>
          </div>
    
          <!-- Description Textarea -->
          <div class="form-group">
            <label for="description">Descripción:</label>
            <textarea id="description" placeholder="Descripción..." v-model="task.description" rows="4" cols="50"></textarea>
          </div>
    
          <!-- Submit Button -->
          <div class="button-container">
            <button class='crearButtonLite' @click.prevent="crearTarea">Crear</button>
          </div>
        </form>
      </div>
    </div>
</template>
  
<script>
  import axios from 'axios';
  export default {
    name: "TaskCreate",
    props: {
      task: {
        type: Object,
        required: true
      },
    },
    data() {
      return {
        task: {
          title: '',
          date: '',
          description: '',
          idU:''
        },
        showForm: false // Initially hide the form
      };
    },
    methods: {
      toggleForm() {
        // Toggle the form's visibility when the button is clicked
        this.showForm = !this.showForm;
      },
      submitForm() {
        // Handle form submission here, e.g., send the data to a server or perform other actions.
        console.log('Form submitted with data:', this.task);
  
        // Hide the form after submission
        this.showForm = false;
      },
      crearTarea() {
        try {
            const token = localStorage.getItem("token"); // Obtén el token JWT del almacenamiento local
            const user = JSON.parse(sessionStorage.getItem("user"));
            this.task.idU = user.id_usuario;
            let json = {
              nombre_tarea: this.task.title,
              desc_tarea: this.task.description,
              vence_tarea: this.task.date,
              estado_tarea: "En curso",
              id_usuario_tarea: this.task.idU
            }
            axios.post('http://localhost:8080/tarea', json,{
              headers: {
                Authorization: `Bearer ${token}`,
              }
            });
            this.showForm = false;
            window.location.reload()
          } catch (e){
            console.log(e);
          }
      }
    }
  };
</script>


<style scoped>
.form-container {
  margin: 15px;
  width: 100%;
}

/* Styling for the form border */
.custom-border {
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
  box-sizing: border-box;
}

.form-group input {
  width: 100%; 
  box-sizing: border-box;
}

form {
  background-color: #0e7b59;
  width: 450px;
  height: 400px;
  margin: auto;
  box-sizing: border-box;
}
.form-group input, textarea{
  margin: 10px;
  font-family: "Roboto", sans-serif;
  background: #f2f2f2;
  width: 95%;
  padding: 15px;
  font-size: 14px;
  box-sizing: border-box;
}

label {
  font-family: "Roboto", sans-serif;
  font-size: 18px;
  color: #f2f2f2;
}

button {
  font-family: "Roboto", sans-serif; /* Cambio de fuente */
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
.crearButtonLite{
  font-family: "Roboto", sans-serif;
  font-size: 16px;
  font-weight: 700;
  background-color: #E5B80B;
  color: #fff;
  margin: 0 auto;
  display: block;
  text-align: center;
  width: 15rem;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.608);
  border-radius: 4rem;
}
.crearButton{
  font-family: "Roboto", sans-serif;
  font-size: 20px;
  font-weight: 700;
  background-color: #E5B80B;
  color: #fff;
  margin: 0 auto;
  display: block;
  text-align: center;
  width: 20rem;
  text-shadow: 1px 1px 2px rgba(0,0,0,0.47);
  border-radius: 4rem;
}

button:hover {
  background: #16a294;
}

</style>