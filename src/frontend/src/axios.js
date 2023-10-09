import axios from "axios";

axios.defaults.baseURL='http://localhost:8080'
/*
    axios.interceptors.request.use(
    (config) => {
      // Obtiene el token JWT almacenado en localStorage
      const token = localStorage.getItem("token");
  
      // Si existe el token y la ruta no es "/login" ni "/register", agrega el encabezado de autorización Bearer
      if (token && config.url !== "/usuario/login" && config.url !== "/usuario/register") {
        config.headers.common["Authorization"] = `Bearer ${token}`;
      }
  
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

*/
