package com.diego;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/Tareas";
    private static final String usuario = "root";
    private static final String password = "12345";

    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, usuario, password);
    }

}
