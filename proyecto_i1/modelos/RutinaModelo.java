package com.example.start.proyecto_i1.modelos;

import java.io.Serializable;

/**
 * Created by start on 21/05/2018.
 */

public class RutinaModelo implements Serializable{
    protected int idRutina;
    protected String nombre;
    protected int tiempoTotal;
    protected int idUsuario;
    protected String dificultad;

    public RutinaModelo() {
    }

    public RutinaModelo(int idRutina, String nombre, int tiempoTotal,  int idUsuario, String dificultad) {
        this.idRutina = idRutina;
        this.nombre = nombre;
        this.tiempoTotal = tiempoTotal;
        this.idUsuario = idUsuario;
        this.dificultad = dificultad;
    }
    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }



}
