package com.diego;

import com.diego.model.Tarea;
import com.diego.model.TareaDAO;

import java.sql.*;
import java.util.*;
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
        mostrarMenu(entrada, tareaDAO);
    }

    public static void mostrarMenu(Scanner entrada, TareaDAO tareaDao) {
        int opcion;
        do {
            System.out.println("===============================");
            System.out.println("       Gestor de Tareas        ");
            System.out.println("===============================");
            System.out.println();

            System.out.println("1. Mostrar Tareas ");
            System.out.println("2. Crear Tarea");
            System.out.println("3. Editar una tarea");
            System.out.println("4. Borrar una tarea");
            System.out.println("5. Imprimir tareas en fichero");
            System.out.println("6. Completar tarea");
            System.out.println("0. Salir");
            System.out.println("Elije una opción");

            opcion = Integer.parseInt(entrada.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Mostrar tareas disponibles... ");
                    List<Tarea> tareas = tareaDao.obtenerTareas();
                    for (Tarea t: tareas) {
                        System.out.println(t.getId() + " - " + t.getNombre() + " - " + t.getDescripcion() + " - " +  t.getFecha() + " - " + t.isCompletada());
                    }
                    break;

                case 2:
                    System.out.println("Crear una tarea nueva... ");
                    System.out.println();

                    System.out.println("Escribe un nombre: ");
                    String nombre = entrada.nextLine();

                    System.out.println("Escribe una descripción: ");
                    String descripcion = entrada.nextLine();

                    tareaDao.crearTareas(nombre, descripcion);

                    break;
                case 3:
                    System.out.println("Editar una tarea existente... ");

                    System.out.println("Selecciona el id de la tarea por favor:");
                    int id = Integer.parseInt(entrada.nextLine());

                    System.out.println("Escribe el nombre para cambiarlo.");
                    nombre = entrada.nextLine();

                    System.out.println("Escribe la descripcion para cambiarlo.");
                    descripcion = entrada.nextLine();

                    tareaDao.editarTareas(id, nombre, descripcion);

                    break;
                case 4:
                    System.out.println("Eliminar una tarea... ");

                    System.out.println("Selecciona la tarea a eliminar: ");
                    id = Integer.parseInt(entrada.nextLine());
                    tareaDao.eliminarTareas(id);

                    break;
                case 5:
                    System.out.println("Leyendo tareas existentes.");
                    List<Tarea> tareasFichero = tareaDao.obtenerTareas();

                    System.out.println("Imprimiendo tareas en fichero... ");
                    tareaDao.imprimirDatos(tareasFichero);
                    break;

                case 6:
                    System.out.println("Seleccione las tareas que quiere marcar como completadas:");
                    id = Integer.parseInt(entrada.nextLine());
                    tareaDao.completarTarea(id);
                    if(tareaDao.completarTarea(id) == true) {
                        System.out.println("La tarea se completó con éxito.");
                    } else {
                        System.out.println("Dicho id no existe en la lista.");
                    }
                    break;

                case 0:
                    System.out.println("Programa finalizado...");
                    break;

                default:
                    System.out.println("Opción no válida...");
            }
        } while(opcion != 0);
    }
}