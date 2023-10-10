<template>
  <!-- Encabezado del sitio -->
  <header>
    <!-- Cambio de fuente para "My To-Do" -->
    <h1 class="title" style="font-family: 'NuevaFuente', sans-serif">
      My Epic To-Do List
    </h1>
    <!-- Barra de Búsqueda -->
    <div class="search-bar">
      <input
        type="text"
        class="search-input"
        placeholder="Buscar..."
        style="font-family: 'NuevaFuente', sans-serif"
        @keydown.enter="performSearch"
      />
    </div>
    <!-- Botón de Cerrar Sesión -->
    <div class="logout-container">
      <button
        class="logout-button"
        @click="logout"
        style="font-family: 'NuevaFuente', sans-serif"
      >
        Salir
        <!-- Texto "Salir" sin icono -->
      </button>
    </div>
  </header>
</template>

<script>
export default {
  name: "Header",
  methods: {
    logout() {
      // Elimina el token y la información del usuario del almacenamiento local
      localStorage.removeItem("token");
      sessionStorage.removeItem("user");
      // Redirige a la página de inicio de sesión
      this.$router.push("/");
    },
    performSearch() {
      // Implementa la lógica de búsqueda aquí
      // Por ejemplo, puedes obtener el valor de la barra de búsqueda (this.searchTerm)
      // y realizar una búsqueda en tu aplicación
      const searchTerm = document.querySelector(".search-input").value;
      // Realiza la búsqueda o ejecuta la acción deseada
      console.log("Búsqueda realizada:", searchTerm);
    },
  },
};
</script>

<style scoped>
/* Estilos específicos para el componente Header */
header {
  color: white;
  background-color: rgba(48, 102, 190, 0.6);
  font-size: 1.6rem;
  padding: 2rem;
  margin: 0;
  border-radius: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
}

header h1 {
  text-align: center;
  background-color: transparent;
  margin: 0;
  font-size: 2rem;
  /* Agrega un efecto de sombreado al texto del título */
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.search-bar {
  display: flex;
  align-items: center;
}

.search-input {
  border: none;
  border-radius: 20px;
  padding: 10px;
  font-size: 1rem;
  width: 200px;
}

.logout-container {
  display: flex;
  align-items: center;
}

.logout-button {
  background-color: #090c9b;
  color: white;
  border: none;
  border-radius: 30px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-size: 1rem;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px 50px;
  /* Agrega sombreado al texto del botón */
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
}

.logout-button:hover {
  background-color: #b4c5e4;
}
</style>