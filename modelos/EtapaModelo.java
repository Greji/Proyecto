package com.example.start.proyecto_i1.modelos;

import java.io.Serializable;

/**
 * Created by start on 21/05/2018.
 */

public class EtapaModelo implements Serializable{
    protected int idEtapa;
    protected int numeroEtapa;
    protected int tiempoEtapa;
    protected int tiempoDescanso;
    protected String estimulo;
    protected int idEjercicio;
    protected int repeticiones;
    protected int idRutina;


    public EtapaModelo() {
        estimulo="";
    }


    public EtapaModelo(int numeroEtapa) {
        this.numeroEtapa = numeroEtapa;
        this.repeticiones=1;
        this.idEjercicio=1;
        this.tiempoDescanso=500;
        this.idRutina=1;
    }


    public EtapaModelo(int idEtapa, int numeroEtapa, int tiempoEtapa, int tiempoDescanso, String estimulo, int idEjercicio, int repeticiones, int idRutina) {
        this.idEtapa = idEtapa;
        this.numeroEtapa = numeroEtapa;
        this.tiempoEtapa = tiempoEtapa;
        this.tiempoDescanso = tiempoDescanso;
        this.idEjercicio = idEjercicio;
        this.repeticiones = repeticiones;
        this.idRutina = idRutina;
        this.estimulo = estimulo;
    }



    public int getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(int idEtapa) {
        this.idEtapa = idEtapa;
    }

    public int getNumeroEtapa() {
        return numeroEtapa;
    }

    public void setNumeroEtapa(int numeroEtapa) {
        this.numeroEtapa = numeroEtapa;
    }

    public int getTiempoEtapa() {
        return tiempoEtapa;
    }

    public void setTiempoEtapa(int tiempoEtapa) {
        this.tiempoEtapa = tiempoEtapa;
    }

    public int getTiempoDescanso() {
        return tiempoDescanso;
    }

    public void setTiempoDescanso(int tiempoDesccanso) {
        this.tiempoDescanso = tiempoDesccanso;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }


    public String getEstimulo() {
        return estimulo;
    }

    public void setEstimulo(String estimulo) {
        this.estimulo += estimulo;
    }
}
