package com.diego;

import com.diego.model.Tarea;
import com.diego.model.TareaDAO;
import com.diego.model.Tarea_Hechas;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

     //   try {
       //     Connection conexion = Conexion.conectar();

      //      System.out.println("¡Conexión Realizada Correctamente!");

      //      conexion.close();


      //  } catch (Exception e) {
      //      System.out.println("Error de Conexion con MySQL");

      //      e.printStackTrace();
      //  }

        Scanner entrada = new Scanner(System.in);
        TareaDAO tareaDAO = new TareaDAO();
        int opcion;

        System.out.println(" === Bienvenidos a la lista de Tareas ===");
        do {
            System.out.println("\n---Menú---");
            System.out.println("1. Listar Tareas ");
            System.out.println("2. Crear Tareas ");
            System.out.println("3. Ver tareas realizadas");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            opcion = Integer.parseInt(entrada.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Lista de tareas pendientes");
                    List<Tarea> tareas = tareaDAO.obtenerTareas();
                    for(Tarea t: tareas){
                        System.out.println(t.getId() +  " - " + t.getNombre() + " - " + t.getFecha());
                    }
                    break;

                case 2:
                    System.out.println("=== Agregando una nueva tarea ===");
                    System.out.print("Escriba un nombre: ");
                    String nombre = entrada.nextLine();

                    System.out.print("Escriba una descripcion: ");
                    String descripcion = entrada.nextLine();

                    tareaDAO.crearTareas(nombre, descripcion);
                    break;

                case 3:
                    System.out.println(" === Lista de Tareas realizadas === ");

                    List<Tarea> hechas = tareaDAO.TareasHechas();
                    for(Tarea t: hechas){
                        System.out.println(t.getId() + " + " + t.getNombre() + " + " + t.getDescripcion() + " + " + t.getFecha());
                    }
                    break;
            }

        } while (opcion != 4);


    }
}
