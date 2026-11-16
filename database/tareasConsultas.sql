-- ================================
-- Base de datos: tareas
-- Proyecto: Gestor de tareas Java + SQL
-- ================================

CREATE DATABASE IF NOT EXITS Tareas;
USE Tareas;

-- =================================
-- 1. Tabla: categorias
-- =================================

CREATE TABLE categorias (
	id_categoria INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(30) NOT NULL,

	PRIMARY KEY (id_categoria)
);

-- =================================
-- 2. Tabla: tareas
-- =================================

CREATE TABLE tareas (
	id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(30) NOT NULL,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    descripcion VARCHAR(255),
	completada BOOLEAN NOT NULL DEFAULT FALSE,
    id_categoria INT NOT NULL,
    
    PRIMARY KEY (id),
    
    CONSTRAINT FK_id_categoria
		FOREIGN KEY (id_categoria)
        REFERENCES categorias(id_categoria)
);


-- =================================
-- 1. Datos De Ejemplo
-- =================================

INSERT INTO categorias (nombre) 
VALUES 
	('Compras'), 
    ('Estudios'),  
    ('Personal');

-- =================================
-- FIN DEL SCRIPT
-- =================================


-- =================================
-- Consultas SQL
-- =================================

-- Consultar todas las tareas
SELECT * FROM tareas;

-- Consultar tareas completadas
SELECT id, nombre
FROM tareas
WHERE completada = TRUE;

-- Consultar tareas pendientes
SELECT id, nombre
FROM tareas
WHERE completada = FALSE;

-- Consultar tareas junto a sus categorías
SELECT 
	tareas.id,
    tareas.nombre,
    tareas.fecha,
    tareas.descripcion,
    tareas.completada,
    categorias.nombre AS nombre_categoria
FROM tareas
INNER JOIN categorias
ON tareas.id_categoria = categorias.id_categoria;