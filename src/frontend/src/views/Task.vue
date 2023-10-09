<template>
  <body>
    <header>
      <h1 class="title">My To-Do</h1>
    </header>
    <section class="container">
      <article>
        <h2>Tarea 1: </h2>
        <p>yyyy/mm/dd</p>
        <p>Hacer x</p>
        <div class="container">
          <button class="edit">Editar</button>
          <button class="delete">Eliminar</button>
        </div>
      </article>
      <article>
        <h2>Tarea 2: </h2>
        <p>Hacer y</p>
      </article>
    </section>
    <footer>
      <p>Contacto: </p>
    </footer>
  </body>
</template>

<script>

</script>

<style scoped>
header {
  color: #fbfff1;
  background-color: #3066be;
  font-size: 1.6rem;
  padding: 2rem;
  margin: 0;
}

header h1 {
  text-align: center;
  background-color: #3066be;
  margin: 0;
}

section article {
  color: black;
  background-color: #b4c5e4;
  border-radius: 0.2rem;
  margin: 1rem 0;
  padding: 0.25rem 0;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}

section article h2, section article p {
  margin: 1.2rem;
}

.container{
  width: 400px;
  margin: 0 auto;
}

body {
  background-color: #fbfff1;
  margin:0;
}

button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #3066be;
  width: 35%;
  border: 0;
  padding: 15px;
  color: #fbfff1;
  font-size: 15px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
  margin-left: 3rem;
}

.delete {
  background: #3c3744;
}

button:hover, button:active, button:focus {
  background: #090c9b;
}

.container{
  display: block;
}
</style>