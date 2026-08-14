package com.diego.model;

import java.time.LocalDateTime;

public class Tarea_Hechas {

    private int id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fecha_completada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha_completada() {
        return fecha_completada;
    }

    public void setFecha_completada(LocalDateTime fecha_completada) {
        this.fecha_completada = fecha_completada;
    }
}
