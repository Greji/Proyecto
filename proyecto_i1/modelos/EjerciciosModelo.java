package com.example.start.proyecto_i1.modelos;

/**
 * Created by start on 13/05/2018.
 */

public class EjerciciosModelo {
    protected int idEjercicio;
    protected String nombre;
    protected String descripcion;

    public EjerciciosModelo() {
    }

    public EjerciciosModelo(int idEjercicio, String nombre, String descripcion) {
        this.idEjercicio = idEjercicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
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
}
