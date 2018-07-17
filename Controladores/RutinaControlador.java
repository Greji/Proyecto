package com.example.start.proyecto_i1.Controladores;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.RutinaModelo;
import com.example.start.proyecto_i1.modelos.UsuarioModelo;

import java.util.ArrayList;

/**
 * Created by start on 21/05/2018.
 */

public class RutinaControlador {

    protected DBHelper admin;
    protected ArrayList<RutinaModelo> listadeRutinas  =new ArrayList<>();

    public RutinaControlador(DBHelper admin) {
        this.admin = admin;
    }



    public void setRutina(RutinaModelo rutina){
        SQLiteDatabase db =admin.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nombre", rutina.getNombre());
        values.put("tiempo_total", rutina.getTiempoTotal());
        values.put("dificultad", rutina.getDificultad());
        values.put("ID_usuario", rutina.getIdUsuario());

        db.insert("rutina",null,values);
        db.close();

    }

    public int updateRutinas(RutinaModelo usuario){

        SQLiteDatabase db=admin.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("nombre",usuario.getNombre());
        values.put("tiempo_total",usuario.getTiempoTotal());
        values.put("dificultad",usuario.getDificultad());
        values.put("ID_usuario",usuario.getIdUsuario());
        return db.update("rutina", values, "ID_rutina" + " = ?",
                new String[] { String.valueOf(usuario.getIdRutina()) });

    }


    public RutinaModelo getRutinaById(int idRutina, int idUsuario){
        SQLiteDatabase db = admin.getWritableDatabase();
        RutinaModelo rutina = new RutinaModelo();;
        Cursor consultaRutina = db.rawQuery("SELECT * FROM rutina WHERE " +
                "ID_rutina="+ Integer.toString(idRutina),
                null);

        if (consultaRutina.moveToFirst()) {
            do {
                rutina.setIdRutina(consultaRutina.getInt(0));
                rutina.setNombre(consultaRutina.getString(1));
                rutina.setTiempoTotal(consultaRutina.getInt(2));
                rutina.setDificultad(consultaRutina.getString(3));
                rutina.setIdUsuario(consultaRutina.getInt(4));
                System.out.println("El nombre es:"+rutina.getNombre());

            } while (consultaRutina.moveToNext());
        }
        db.close();
        return rutina;
    }

    public int getIdByNombre(String nombre){
        SQLiteDatabase db = admin.getWritableDatabase();
        int idRutina=-1;

        String[] args = {nombre};
        Cursor consultaRutina = db.rawQuery("SELECT ID_rutina FROM rutina WHERE nombre='"+nombre+"'", null);



        if (consultaRutina.moveToFirst()) {
            do {

                idRutina = consultaRutina.getInt(0);
            } while (consultaRutina.moveToNext());
        }
        db.close();
        System.out.println("El id es:" + idRutina);
        return idRutina;
    }



    public ArrayList<RutinaModelo> getAllRutina(int idUsuario){
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaRutina = db.rawQuery("SELECT * FROM rutina WHERE ID_usuario="+ Integer.toString(idUsuario), null);

        if (consultaRutina.moveToFirst()) {
            do {
                RutinaModelo aux = new RutinaModelo();
                aux.setIdRutina(consultaRutina.getInt(0));
                aux.setNombre(consultaRutina.getString(1));
                aux.setTiempoTotal(consultaRutina.getInt(2));
                aux.setDificultad(consultaRutina.getString(3));
                aux.setIdUsuario(idUsuario);
                System.out.println(aux.getNombre());
                listadeRutinas.add(aux);

            } while (consultaRutina.moveToNext());
        }
        db.close();
        return listadeRutinas;
    }

    public void deleteRutina(RutinaModelo rutina){

        SQLiteDatabase db = admin.getWritableDatabase();

        db.delete("rutina", "ID_rutina" + " = ?",
                new String[] { String.valueOf(rutina.getIdRutina()) });


    }
}
