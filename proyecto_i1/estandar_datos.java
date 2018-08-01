package com.example.start.proyecto_i1;

import com.example.start.proyecto_i1.Controladores.UsuarioControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;

/**
 * Created by start on 05/03/2018.
 */

public class estandar_datos {
    protected String modo;
    protected String tipo_estimulo;
    protected String tiempoEstimulo;
    protected String estimulo = "1111111";


    public estandar_datos() {
        this.modo = "0";
        this.tipo_estimulo = "1";
        this.tiempoEstimulo= "0";
    }


}
