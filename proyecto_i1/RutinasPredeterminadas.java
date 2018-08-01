package com.example.start.proyecto_i1;

import com.example.start.proyecto_i1.modelos.EtapaModelo;
import com.example.start.proyecto_i1.modelos.RutinaModelo;


import java.util.ArrayList;

/**
 * Created by start on 10/06/2018.
 */

public class RutinasPredeterminadas {

    protected RutinaModelo rutina;
    public ArrayList<EtapaModelo> etapas;


    public RutinasPredeterminadas(int id){

    }

    public ArrayList<EtapaModelo> RutinaPredeterminaUno(){

        etapas = new ArrayList<>();
        etapas.add(new EtapaModelo(
                1,
                1,
                60,
                0,
                "1111111",
                1,
                1,
                -1

        ));



        return etapas;
    }
    public ArrayList<EtapaModelo> RutinaPredeterminaDos(){

        etapas = new ArrayList<>();
        etapas.add(new EtapaModelo(
                1,
                1,
                60,
                10,
                "11110001",
                5,
                10,
                -4

        ));
        etapas.add(new EtapaModelo(
                2,
                2,
                60,
                0,
                "1000001",
                6,
                10,
                -4

        ));

        return etapas;
    }

    public ArrayList<EtapaModelo> RutinaPredeterminaTres(){

        etapas = new ArrayList<>();
        etapas.add(new EtapaModelo(
                4,
                3,
                30,
                10,
                "11110001",
                7,
                2,
                -4

        ));
        etapas.add(new EtapaModelo(
                4,
                4,
                30,
                0,
                "1000001",
                1,
                1,
                -4

        ));

        etapas.add(new EtapaModelo(
                2,
                3,
                30,
                0,
                "1000001",
                5,
                1,
                -1

        ));

        return etapas;


    }

    public ArrayList<EtapaModelo> RutinaPredeterminaCuatro(){

        etapas = new ArrayList<>();
        etapas.add(new EtapaModelo(
                1,
                1,
                50,
                10,
                "11110001",
                2,
                1,
                -1

        ));
        etapas.add(new EtapaModelo(
                2,
                2,
                60,
                0,
                "1000001",
                1,
                1,
                -1

        ));
        etapas.add(new EtapaModelo(
                1,
                1,
                50,
                10,
                "11110001",
                2,
                1,
                -1

        ));
        etapas.add(new EtapaModelo(
                2,
                2,
                60,
                0,
                "1000001",
                1,
                1,
                -1

        ));

        return etapas;
    }

    public ArrayList<EtapaModelo> RutinaPredeterminaCinco(){

        etapas = new ArrayList<>();
        etapas.add(new EtapaModelo(
                1,
                1,
                50,
                10,
                "11110001",
                2,
                1,
                -1

        ));
        etapas.add(new EtapaModelo(
                2,
                2,
                60,
                0,
                "1000001",
                1,
                1,
                -1

        ));

        return etapas;
    }






}
