package com.example.start.proyecto_i1.modelos;

/**
 * Created by start on 13/05/2018.
 */

public class UsuarioModelo {
    int id_usuario;
    String nombre;
    String configuracion;

    public UsuarioModelo() {
    }

    public UsuarioModelo(int id_usuario, String nombre, String configuracion) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.configuracion = configuracion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
    }
}
