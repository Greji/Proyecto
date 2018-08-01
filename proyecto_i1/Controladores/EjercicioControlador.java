package com.example.start.proyecto_i1.Controladores;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.EjerciciosModelo;
import com.example.start.proyecto_i1.modelos.UsuarioModelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by start on 21/05/2018.
 */

public class EjercicioControlador {

    protected DBHelper admin;
    protected ArrayList<EjerciciosModelo> listadeEjercicios  =new ArrayList<>();




    public EjercicioControlador(DBHelper admin) {
        this.admin = admin;
    }

    public ArrayList<EjerciciosModelo> getAllEjercicios() {
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaEjercicios = db.rawQuery("SELECT * FROM ejercicios", null);

        if (consultaEjercicios.moveToFirst()) {
            do {
                EjerciciosModelo aux = new EjerciciosModelo();
                aux.setIdEjercicio(consultaEjercicios.getInt(0));
                aux.setNombre(consultaEjercicios.getString(1));
                aux.setDescripcion(consultaEjercicios.getString(2));
                System.out.println(aux.getNombre());
                listadeEjercicios.add(aux);

            } while (consultaEjercicios.moveToNext());
        }
        db.close();
        return listadeEjercicios;
    }

    public ArrayList<String> getNombreEjercicio(){

        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaEjercicios = db.rawQuery("SELECT nombre FROM ejercicios", null);
        ArrayList<String> listaNombres = new ArrayList<>();

        if (consultaEjercicios.moveToFirst()) {
            do {
                String aux = consultaEjercicios.getString(0);
                listaNombres.add(aux);
            } while (consultaEjercicios.moveToNext());
        }
        db.close();
        return listaNombres;
    }

    public int getIdEjercicioByNombre(String nombre){
        SQLiteDatabase db = admin.getWritableDatabase();
        int idEjercicio=-1;
        Cursor consultaEjercicios = db.rawQuery("SELECT ID_ejercicio FROM ejercicios WHERE nombre='"+ nombre+"'", null);

        if (consultaEjercicios.moveToFirst()) {
            do {
                idEjercicio = consultaEjercicios.getInt(0);
            } while (consultaEjercicios.moveToNext());
        }

        db.close();
        return idEjercicio;

    }

    public String getEjercicio(int id){
        SQLiteDatabase db = admin.getWritableDatabase();
        String ejercicio ="";
        Cursor consultaEjercicios = db.rawQuery("SELECT nombre FROM ejercicios WHERE ID_ejercicio="+ Integer.toString(id), null);

        if (consultaEjercicios.moveToFirst()) {
            do {
                ejercicio = consultaEjercicios.getString(0);
            } while (consultaEjercicios.moveToNext());
        }

        db.close();
        return ejercicio;
    }

    public void setEjercicio(EjerciciosModelo ejercicio){

        SQLiteDatabase db=admin.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("nombre",ejercicio.getNombre());
        values.put("descripcion",ejercicio.getDescripcion());

        db.insert("ejercicios",null,values);
        db.close();
    }
}
