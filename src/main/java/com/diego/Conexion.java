package com.diego;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL = "DB_URL";
    private static final String usuario = "DB_USER";
    private static final String password = "DB_PASSWORD";

    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, usuario, password);
    }

}
