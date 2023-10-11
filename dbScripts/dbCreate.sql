CREATE TABLE IF NOT EXISTS usuario(
    id_usuario SERIAL PRIMARY KEY,
    nombre_usuario VARCHAR(255),
    contrasenia_usuario VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tarea(
    id_tarea SERIAL PRIMARY KEY,
    nombre_tarea VARCHAR(255),
    desc_tarea VARCHAR(255),
    vence_tarea DATE,
    estado_tarea VARCHAR(255),
    id_usuario_tarea INT,
    CONSTRAINT fk_usuario_tarea
        FOREIGN KEY (id_usuario_tarea)
        REFERENCES usuario(id_usuario)
);