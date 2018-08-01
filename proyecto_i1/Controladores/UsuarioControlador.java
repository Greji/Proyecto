package com.example.start.proyecto_i1.Controladores;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.start.proyecto_i1.GRECSBD.DBHelper;

import java.util.ArrayList;

import android.content.ContentValues;

import com.example.start.proyecto_i1.modelos.UsuarioModelo;


/**
 * Created by start on 13/05/2018.
 */

public class UsuarioControlador{

    protected DBHelper admin;
    protected ArrayList<UsuarioModelo> listadeUsuarios  =new ArrayList<>();



    public UsuarioControlador(DBHelper admin) {
        this.admin = admin;
    }



    public ArrayList<UsuarioModelo> getAllUsuarios() {

        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaUsuario = db.rawQuery("SELECT * FROM usuarios", null);

        if (consultaUsuario.moveToFirst()) {
            do {
                UsuarioModelo aux = new UsuarioModelo();
                aux.setId_usuario(consultaUsuario.getInt(0));
                aux.setNombre(consultaUsuario.getString(1));
                System.out.println(aux.getNombre());
                listadeUsuarios.add(aux);

            } while (consultaUsuario.moveToNext());
        }
        db.close();
        return listadeUsuarios;


    }

    public void setUsuarios(UsuarioModelo usuario){

        SQLiteDatabase db=admin.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("nombre",usuario.getNombre());
        values.put("configuracion",usuario.getConfiguracion());

        db.insert("usuarios",null,values);
        db.close();
    }

    public int getNumeroUsuarios(){
        int n=0;
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaUsuario = db.rawQuery("SELECT count(ID_usuario) FROM usuarios", null);
        if (consultaUsuario.moveToFirst()) {
            do {

                n = consultaUsuario.getInt(0);

            } while (consultaUsuario.moveToNext());
        }
        db.close();
        System.out.println(n);
        return n;

    }

    public String getConfiguracionById(int id){

        String configuracion = "";
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaUsuario = db.rawQuery("SELECT configuracion FROM usuarios WHERE ID_usuario="+ Integer.toString(id), null);

        if (consultaUsuario.moveToFirst()) {
            do {

                configuracion = consultaUsuario.getString(0);

            } while (consultaUsuario.moveToNext());
        }
        db.close();

        return configuracion;

    }

    public int getIdByNombre(String nombre){
        int id=0;
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaUsuario = db.rawQuery("SELECT ID_usuario FROM usuarios WHERE nombre='"+ nombre+"'", null);

        if (consultaUsuario.moveToFirst()) {
            do {

                id = consultaUsuario.getInt(0);

            } while (consultaUsuario.moveToNext());
        }
        db.close();

        return id;
    }

    public int updateConfiguracion(int id, String conf){

        SQLiteDatabase db=admin.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("configuracion", conf);

        // updating row
        return db.update("usuarios", values, "ID_usuario" + " = ?",
                new String[] { String.valueOf(id) });
    }


    public boolean isSetUsuario(String nombre){
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaUsuario = db.rawQuery("SELECT ID_usuario FROM usuarios WHERE nombre='"+ nombre+"'", null);

        if (consultaUsuario.moveToFirst()) {
            return true;
        }
        db.close();

        return false;
    }

    public void deleteUsuario(int id){
        SQLiteDatabase db = admin.getWritableDatabase();

        db.delete("usuarios", "ID_usuario" + " = ?",
                new String[] { String.valueOf(id) });

    }
}
