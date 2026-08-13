package com.diego.model;

import com.diego.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {

    //Instancia para obtener tareas
    public List<Tarea> obtenerTareas() {

        List<Tarea> tareas = new ArrayList<>();

        String sql = "Select id, nombre, fecha, descripcion FROM tareas";

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

                tareas.add(tarea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tareas;
    }

    //Instancia para crear Tareas Nuevas
    public void crearTareas(String nombre, String descripcion, LocalDateTime fecha) {
        String sql = "Insert into tareas (nombre, descripcion) VALUES (?,?,?)";

        try(
            Connection conexion = Conexion.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setTimestamp(3, Timestamp.valueOf(fecha));

            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Tarea creada correctamente");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //Instancia para Editar una tarea
    public void editarTareas(int id, String nombre, String descripcion, LocalDateTime fecha){

        String sql = "UPDATE tareas SET nombre = ?, descripcion = ?, fecha = ?, WHERE id = ?";

        try(
            Connection conexion = Conexion.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql);

        ) {
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setTimestamp(3, Timestamp.valueOf(fecha));
            ps.setInt(4, id);

            int filasActualizar = ps.executeUpdate();

            if (filasActualizar > 0) {
                System.out.println("Tarea Actualizada Correctamente");
            } else {

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Instancia para eliminar una tarea
    public void eliminarTareas(int id, String nombre, String descripcion, LocalDateTime fecha) {
        String sql = "DELETE FROM tareas WHERE id = ?";

        try(
                Connection conexion = Conexion.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ) {
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setTimestamp(3, Timestamp.valueOf(fecha));
            ps.setInt(4, id);

            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas > 0){
                System.out.println("Tarea eliminada con Exito");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Eliminar un método dentro de 24 horas
}
