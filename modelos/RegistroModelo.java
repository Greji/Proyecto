package com.example.start.proyecto_i1.modelos;

/**
 * Created by start on 13/05/2018.
 */

public class RegistroModelo {
    protected int idRegistro;
    protected int dia;
    protected int mes;
    protected int year;
    protected double fuerza;
    protected double tiempo;
    protected double tiempoMin;
    protected double tiempoMax;
    protected int golpesTotales;
    protected int golpesAcertados;
    protected int idUsuario;

    public RegistroModelo() {
    }

    public RegistroModelo(int idRegistro, int dia, int mes, double fuerza, double tiempo, double tiempoMin, double tiempoMax, int golpesTotales, int golpesAcertados, int idUsuario) {
        this.idRegistro = idRegistro;
        this.dia = dia;
        this.mes = mes;
        this.fuerza = fuerza;
        this.tiempo = tiempo;
        this.tiempoMin = tiempoMin;
        this.tiempoMax = tiempoMax;
        this.golpesTotales = golpesTotales;
        this.golpesAcertados = golpesAcertados;
        this.idUsuario = idUsuario;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getFuerza() {
        return fuerza;
    }

    public void setFuerza(double fuerza) {
        this.fuerza = fuerza;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public double getTiempoMin() {
        return tiempoMin;
    }

    public void setTiempoMin(double tiempoMin) {
        this.tiempoMin = tiempoMin;
    }

    public double getTiempoMax() {
        return tiempoMax;
    }

    public void setTiempoMax(double tiempoMax) {
        this.tiempoMax = tiempoMax;
    }

    public int getGolpesTotales() {
        return golpesTotales;
    }

    public void setGolpesTotales(int golpesTotales) {
        this.golpesTotales = golpesTotales;
    }

    public int getGolpesAcertados() {
        return golpesAcertados;
    }

    public void setGolpesAcertados(int golpesAcertados) {
        this.golpesAcertados = golpesAcertados;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


}


