package com.example.start.proyecto_i1.Controladores;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.RegistroModelo;
import com.example.start.proyecto_i1.modelos.UsuarioModelo;

import java.util.ArrayList;

/**
 * Created by start on 14/05/2018.
 */

public class RegistroControlador {

    DBHelper admin;
    ArrayList<RegistroModelo> listadeRegistros  =new ArrayList<>();



    public RegistroControlador(DBHelper admin) {
        this.admin = admin;
    }


    public ArrayList<RegistroModelo> getAllRegistros() {

        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaRegistro = db.rawQuery("SELECT * FROM registro", null);

        if (consultaRegistro.moveToFirst()) {
            do {
                RegistroModelo aux = new RegistroModelo();
                aux.setIdRegistro(consultaRegistro.getInt(0));
                aux.setDia(consultaRegistro.getInt(3));
                aux.setMes(consultaRegistro.getInt(4));
                aux.setYear(consultaRegistro.getInt(5));
                aux.setFuerza(consultaRegistro.getDouble(6));
                aux.setTiempo(consultaRegistro.getDouble(11));
                aux.setTiempoMax(consultaRegistro.getDouble(7));
                aux.setTiempoMin(consultaRegistro.getDouble(8));
                aux.setGolpesTotales(consultaRegistro.getInt(9));
                aux.setGolpesAcertados(consultaRegistro.getInt(10));
                aux.setIdUsuario(consultaRegistro.getInt(12));

                listadeRegistros.add(aux);

            } while (consultaRegistro.moveToNext());
        }
        db.close();
        return listadeRegistros;
    }

    public RegistroModelo getRegistro(int dia, int mes) {

        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaRegistro = db.rawQuery("SELECT * FROM registro WHERE dia="+dia+" AND mes="+mes, null);
        RegistroModelo registro = new RegistroModelo();

        if (consultaRegistro.moveToFirst()) {
            do {
                registro.setIdRegistro(consultaRegistro.getInt(0));
                registro.setDia(consultaRegistro.getInt(3));
                registro.setMes(consultaRegistro.getInt(4));
                registro.setYear(consultaRegistro.getInt(5));
                registro.setFuerza(consultaRegistro.getDouble(6));
                registro.setTiempo(consultaRegistro.getDouble(11));
                registro.setTiempoMax(consultaRegistro.getDouble(7));
                registro.setTiempoMin(consultaRegistro.getDouble(8));
                registro.setGolpesTotales(consultaRegistro.getInt(9));
                registro.setGolpesAcertados(consultaRegistro.getInt(10));
                registro.setIdUsuario(consultaRegistro.getInt(12));


            } while (consultaRegistro.moveToNext());
        }
        db.close();
        return registro;


    }

    public void setRegistro(RegistroModelo registroDia){

        SQLiteDatabase db=admin.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("dia", registroDia.getDia());
        values.put("mes", registroDia.getMes());
        values.put("year", registroDia.getYear());
        values.put("fuerza", registroDia.getFuerza());
        values.put("tiempo", registroDia.getTiempo());
        values.put("fuerza_max", registroDia.getTiempoMax());
        values.put("fuerza_min", registroDia.getTiempoMin());
        values.put("golpes_acertados", registroDia.getGolpesAcertados());
        values.put("golpes_totales", registroDia.getGolpesTotales());
        values.put("id_usuario", registroDia.getIdUsuario());


        db.insert("registro",null,values);
        db.close();
    }

    public boolean isSetRegistro(int dia, int mes){
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consultaRegistro = db.rawQuery("SELECT * FROM registro WHERE dia="+dia+" AND mes="+mes, null);

        if (consultaRegistro.moveToFirst()) {
            return true;
        }
        db.close();

        return false;
    }

    public void deleteRegistros(){
        SQLiteDatabase db = admin.getWritableDatabase();

        db.execSQL("delete from registro");

    }


}
