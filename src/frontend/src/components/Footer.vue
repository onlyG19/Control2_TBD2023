<template>
<footer>
  <div class="footer-content">
    <div class="footer-section">
      <h3>Contacto</h3>
      <p><i class="fas fa-envelope"></i> epictodolist@hotmail.com</p>
      <p><i class="fas fa-phone"></i> +1 (123) 456-7890</p>
    </div>
    <div class="footer-section">
      <h3>Enlaces útiles</h3>
      <ul>
        <li><a href="#">Acerca de nosotros</a></li>
        <li><a href="#">Contacto</a></li>
      </ul>
    </div>
    <div class="footer-section">
      <h3>Síguenos</h3>
      <div class="social-icons">
        <a href="#"><i class="fab fa-facebook"></i></a>
        <a href="#"><i class="fab fa-twitter"></i></a>
        <a href="#"><i class="fab fa-instagram"></i></a>
      </div>
    </div>
  </div>
</footer>
</template>

<script>
export default {
name: 'Footer'
}
</script>

<style scoped>
/* Estilos específicos para el componente Footer */
footer {
margin: auto auto 0 auto; /* Agrega espacio de 20px arriba del componente */
bottom: 0;
width: 100%;
background-color: rgba(37, 79, 24, 0.6);
text-align: center;
padding: 1rem 0; /* Reduce el espacio vertical */
color: white;
font-family: sans-serif; /* Cambia la fuente a sans-serif */
margin-top: auto;
}

.footer-content {
display: flex;
justify-content: space-around;
}

.footer-section {
flex: 1;
margin: 0 1rem;
}

h3 {
font-size: 1rem; /* Tamaño de fuente más pequeño */
margin-bottom: 0.5rem; /* Reduce el espacio vertical */
text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5); /* Agrega sombreado al texto */
}

ul {
list-style: none;
padding: 0;
}

ul li {
margin-bottom: 0.25rem; /* Reduce el espacio vertical */
}

a {
color: white;
text-decoration: none;
text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5); /* Agrega sombreado al texto */
}

.social-icons {
display: flex;
justify-content: center;
margin-top: 0.5rem; /* Reduce el espacio vertical */
}

.social-icons a {
margin: 0 0.5rem;
color: white;
font-size: 1rem; /* Tamaño de fuente más pequeño */
text-decoration: none;
text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5); /* Agrega sombreado al texto */
}

/* Agrega estilos adicionales según tus preferencias */

</style>