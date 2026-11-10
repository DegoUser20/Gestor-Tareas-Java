package com.diego.model;

import com.diego.Conexion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {

    //Instancia para obtener tareas
    public List<Tarea> obtenerTareas() {

        List<Tarea> tareas = new ArrayList<>();

        String sql = "Select id, nombre, fecha, descripcion, completada FROM tareas";

        try(
                Connection conexion = Conexion.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Tarea tarea = new Tarea();

                tarea.setId(rs.getInt("id"));
                tarea.setNombre(rs.getString("Nombre"));
                tarea.setFecha(
                        rs.getTimestamp("Fecha").toLocalDateTime()
                );
                tarea.setDescripcion(
                        rs.getString("Descripcion")
                );
                tarea.setCompletada(rs.getBoolean("completada"));
                tareas.add(tarea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tareas;
    }

    //Instancia para crear Tareas Nuevas
    public void crearTareas(String nombre, String descripcion) {
        String sql = "Insert into tareas (nombre, descripcion) VALUES (?,?)";

        //Validación: Comprobar que nombre y descripcion no sean vacíos
        if(nombre == null || nombre.trim().isEmpty() && descripcion == null || descripcion.trim().isEmpty()) {
            System.out.println("Nombre y descripcion no pueden estar vacios vacios.");
            return;
        }

        //Comprobar que el nombre a corregir no sobrepase los 30 caracteres
        if(nombre.length() > 31) {
            System.out.println("El nombre no puede sobrepasar el límite de caracteres.");
        }

        try(
            Connection conexion = Conexion.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            ps.setString(1, nombre);
            ps.setString(2, descripcion);


            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Tarea creada correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Instancia para Editar una tarea
    public void editarTareas(int id, String nombre, String descripcion){

        String sql = "UPDATE tareas SET nombre = ?, descripcion = ? WHERE id = ?";

        //Validación: Comprobar que nombre y descripción no sean vacíos.
        if(nombre == null || nombre.trim().isEmpty() && descripcion == null || descripcion.trim().isEmpty()) {
            System.out.println("Editar tarea: no puede editar una tarea con campos vacios");
            return;
        }

        //Comprobar que el nombre pase de los 30 caracteres
        if(nombre.length() > 31) {
            System.out.println("El nombre no puede sobrepasar el limite de caracteres (30)");
            return;
        }

        try(
            Connection conexion = Conexion.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql);

        ) {
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setInt(3, id);

            int filasActualizar = ps.executeUpdate();

            if (filasActualizar > 0) {
                System.out.println("Tarea Actualizada Correctamente");
            } else {
                System.out.println("No existe tarea con dicho id.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Instancia para eliminar una tarea
    public void eliminarTareas(int id) {
        String sql = "DELETE FROM tareas WHERE id = ?";

        try(
                Connection conexion = Conexion.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ) {

            ps.setInt(1, id);

            int filasEliminadas = ps.executeUpdate();

            if (filasEliminadas > 0){
                System.out.println("Tarea eliminada con Exito");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Eliminar un método dentro de 24 horas luego de ser completada la tarea
    public void eliminarTiempo(int id, LocalDateTime fechaCompletada) {

        String sql = "DELETE FROM tareas_hechas WHERE id = ? AND fecha_completada <= ? ";

        try(
                Connection conexion = Conexion.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ) {

            LocalDateTime limite = LocalDateTime.now().minusHours(24);

            ps.setInt(1, id);
            ps.setTimestamp(2, Timestamp.valueOf(limite));

            int filasEliminadas = ps.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Tarea Eliminar correctamente");
            } else {
                System.out.println("Aún no ha pasado las 24 horas");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Método para completar una tarea
    public boolean completarTarea(int id) {
        String sql = "UPDATE tareas set completada = true where id = ?";

        try(
                Connection conexion = Conexion.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
                ) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Imprimir datos mediante fichero
    public void imprimirDatos(List<Tarea> tareas) {
        File fichero = new File("C:/Users/Dego/Desktop/EjerciciosJAVA/ListaTareaJAVA/ListaTarea/src/main/java/com/diego/fichero/tareas.txt");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {

            for(Tarea t : tareas) {
                bw.write(t.getId() + " - " + t.getNombre() + " - " + t.getDescripcion());
                bw.newLine();
            }
            System.out.println("Tareas guardadas en el fichero.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ocurrio un error al momento de crear/cargar el fichero.");
        }

    }

}
