Gestor de Tareas - Java y MySQL

Descripción

Proyecto personal desarrollado con Java y MySQL. Consiste en un aplicación de gestión de tareas que
permite administrar actividades y organización mediante categorías.

Funcionalidades

- Crear nuevas tareas
- Consultar las tareas registradas
- Editar el nombre y la descripción
- Cambiar el estado de una tarea (pendiente o completada)
- Eliminar tareas.
- Organizar tareas mediante categorías.
- Exportar información a un archivo de texto.

Tecnologías utilizadas 

- Java 21
- MySQL
- JDBC
- Git
- GitHub

Estructura del proyecto

- src/: código fuente Java.
- database/tareasConsultas.sql: creación de la base de datos y sus tablas y sus consultas de ejemplo.

Requisitos

- Java 21 o compatible con el proyecto.
- MySQL Server
- Un IDE compatible con Java

Configuración de la base de datos

- Iniciar el servidor MySQL.
- Ejecutar el archivo database/tareasConsultas.sql.
- Configurar las siguientes variables de entorno:
DB_URL: dirección de conexión JDBC.
DB_USER: usuario de MySQL.
DB_PASSWORD: contraseña de MySQL.

Ejemplo de dirección de conexión:
    jdbc:mysql://localhost:3306/SuConexion

Ejecución

Abrir el proyecto desde el IDE, comprobar la configuración de conexión y ejecutar
la clase principal App.

Estado del proyecto

Proyecto personal en desarrollo, realizado como práctica de Java, JDBC y gestión de base de datos relaciones.
