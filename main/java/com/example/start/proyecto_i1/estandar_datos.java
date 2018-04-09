package com.example.start.proyecto_i1;

/**
 * Created by start on 05/03/2018.
 */

public class estandar_datos {
    protected String modo;
    protected String tipo_estimulo;
    protected String nivel;
    protected String tiempo;
    protected String t_estimulo;
    protected char []estimulo = {'0', '0', '0', '0', '0', '0', '0'};

    public estandar_datos() {
        this.modo = "0";
        this.tipo_estimulo = "1";
        this.nivel = "0";
        this.tiempo = "0";
        this.t_estimulo= "0";
    }

    public String juntar(){
        String paquete;
        String estimulos;
        estimulos="";

        for(int x=0; x<7; x++){
            estimulos += estimulo[x];
        }

        paquete = modo + "#" + tipo_estimulo + "#" + nivel +"#"+  t_estimulo +"#" + estimulos;
        return paquete;
    }

}
