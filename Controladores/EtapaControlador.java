package com.example.start.proyecto_i1.Controladores;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.EtapaModelo;

import java.util.ArrayList;

/**
 * Created by start on 21/05/2018.
 */

public class EtapaControlador {
    protected DBHelper admin;

    protected ArrayList<EtapaModelo> listadeEtapas  = new ArrayList<>();

    public EtapaControlador(DBHelper admin) {
        this.admin = admin;
    }


    public ArrayList<EtapaModelo> getAllEtapas(int idRutina) {

        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaEtapa = db.rawQuery("SELECT * FROM etapas WHERE ID_rutina='"+idRutina+"'", null);

        if (consultaEtapa.moveToFirst()) {
            do {
                EtapaModelo aux = new EtapaModelo();
                aux.setIdEtapa(consultaEtapa.getInt(0));
                aux.setNumeroEtapa(consultaEtapa.getInt(1));
                aux.setTiempoEtapa(consultaEtapa.getInt(2));
                aux.setTiempoDescanso(consultaEtapa.getInt(3));
                aux.setEstimulo(consultaEtapa.getString(4));
                aux.setIdEjercicio(consultaEtapa.getInt(5));
                aux.setRepeticiones(consultaEtapa.getInt(6));
                aux.setIdRutina(consultaEtapa.getInt(7));

                listadeEtapas.add(aux);

            } while (consultaEtapa.moveToNext());
        }
        db.close();
        return listadeEtapas;


    }

    public EtapaModelo getEtapaById(int numeroEtapa, int idRutina) {

        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaEtapa = db.rawQuery("SELECT * FROM etapas WHERE numero_etapa="+numeroEtapa+"AND ID_rutina="+idRutina , null);
        EtapaModelo aux = new EtapaModelo();
        if (consultaEtapa.moveToFirst()) {
            do {

                aux.setIdEjercicio(consultaEtapa.getInt(0));
                aux.setNumeroEtapa(consultaEtapa.getInt(1));
                aux.setTiempoEtapa(consultaEtapa.getInt(2));
                aux.setTiempoDescanso(consultaEtapa.getInt(3));
                aux.setIdEjercicio(consultaEtapa.getInt(4));
                aux.setRepeticiones(consultaEtapa.getInt(5));
                aux.setIdRutina(consultaEtapa.getInt(6));

                System.out.println(aux.getIdEjercicio());
                listadeEtapas.add(aux);

            } while (consultaEtapa.moveToNext());
        }
        db.close();
        return aux;

    }


    public void setEtapas(ArrayList<EtapaModelo> etapas){
        SQLiteDatabase db=admin.getWritableDatabase();
        ContentValues values=new ContentValues();

        for (int i=0; i<etapas.size(); i++) {

            values.put("numero_etapa",etapas.get(i).getNumeroEtapa());
            values.put("tiempo",etapas.get(i).getTiempoEtapa());
            values.put("tiempo_descanso",etapas.get(i).getTiempoDescanso());
            values.put("ID_ejercicio",etapas.get(i).getIdEjercicio());
            values.put("rep_ejercicio",etapas.get(i).getRepeticiones());
            values.put("estimulo", etapas.get(i).getEstimulo());
            values.put("ID_rutina",etapas.get(i).getIdRutina());
            db.insert("etapas",null,values);
        }
            db.close();
    }
    
    
}
