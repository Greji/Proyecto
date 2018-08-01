package com.example.start.proyecto_i1.GRECSBD;

/**
 * Created by start on 29/04/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE usuarios(" +
                "ID_usuario integer PRIMARY KEY AUTOINCREMENT," +
                "nombre text, " +
                "configuracion text)");

        db.execSQL("CREATE TABLE registro(" +
                "ID_registro  integer PRIMARY KEY AUTOINCREMENT," +
                "dia integer, " +
                "mes integer," +
                "year integer," +
                "fuerza double, " +
                "fuerza_max double, " +
                "fuerza_min double, " +
                "golpes_totales int, " +
                "golpes_acertados int, " +
                "tiempo double, " +
                "ID_usuario integer, " +
                "FOREIGN KEY(ID_usuario) REFERENCES usuarios )");

        db.execSQL("CREATE TABLE estimulo(" +
                "ID_estimulo integer PRIMARY KEY AUTOINCREMENT," +
                "nombre text)");

        db.execSQL("CREATE TABLE rutina(" +
                "ID_rutina integer PRIMARY KEY AUTOINCREMENT," +
                "nombre text, " +
                "tiempo_total int," +
                "dificultad text," +
                "ID_usuario integer," +
                "FOREIGN KEY(ID_usuario) REFERENCES usuarios )");

        db.execSQL("CREATE TABLE etapas(" +
                "ID_etapa integer PRIMARY KEY AUTOINCREMENT," +
                "numero_etapa int," +
                "tiempo int," +
                "tiempo_descanso int," +
                "estimulo text," +
                "ID_ejercicio int," +
                "rep_ejercicio int," +
                "ID_rutina int," +
                "FOREIGN KEY(ID_rutina) REFERENCES rutinas," +
                "FOREIGN KEY(ID_ejercicio) REFERENCES ejercicios )");

        db.execSQL("CREATE TABLE ejercicios(" +
                "ID_ejercicio integer PRIMARY KEY AUTOINCREMENT," +
                "nombre text, " +
                "descripcion text)");

        db.execSQL("CREATE TABLE rutina_aux(" +
                "ID_rutina integer PRIMARY KEY AUTOINCREMENT," +
                "nombre text, " +
                "tiempo_total text," +
                "dificultad int)");

        db.execSQL("CREATE TABLE etapas_aux(" +
                "ID_etapa integer PRIMARY KEY AUTOINCREMENT," +
                "numero_etapa int," +
                "tiempo int," +
                "tiempo_descanso int," +
                "estimulo text," +
                "ID_ejercicio int," +
                "rep_ejercicio int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE usuarios(" +
                "ID_usuario integer PRIMARY KEY AUTOINCREMENT," +
                "nombre text, " +
                "configuracion text)");

        db.execSQL("CREATE TABLE registro(" +
                "ID_registro  integer PRIMARY KEY AUTOINCREMENT," +
                "dia integer, " +
                "mes integer," +
                "year integer," +
                "fuerza double, " +
                "fuerza_max double, " +
                "fuerza_min double, " +
                "golpes_totales int, " +
                "golpes_acertados int, " +
                "tiempo double, " +
                "ID_usuario integer, " +
                "FOREIGN KEY(ID_usuario) REFERENCES usuarios )");

        db.execSQL("CREATE TABLE estimulo(" +
                "ID_estimulo integer PRIMARY KEY AUTOINCREMENT," +
                "nombre text)");

        db.execSQL("CREATE TABLE rutina(" +
                "ID_rutina integer PRIMARY KEY AUTOINCREMENT," +
                "nombre text, " +
                "tiempo_total int," +
                "dificultad text," +
                "ID_usuario integer," +
                "FOREIGN KEY(ID_usuario) REFERENCES usuarios )");

        db.execSQL("CREATE TABLE etapas(" +
                "ID_etapa integer PRIMARY KEY AUTOINCREMENT," +
                "numero_etapa int," +
                "tiempo int," +
                "tiempo_descanso int," +
                "estimulo text," +
                "ID_ejercicio int," +
                "rep_ejercicio int," +
                "ID_rutina int," +
                "FOREIGN KEY(ID_rutina) REFERENCES rutinas," +
                "FOREIGN KEY(ID_ejercicio) REFERENCES ejercicios )");

        db.execSQL("CREATE TABLE ejercicios(" +
                "ID_ejercicio integer PRIMARY KEY AUTOINCREMENT," +
                "nombre text, " +
                "descripcion text)");

        db.execSQL("CREATE TABLE rutina_aux(" +
                "ID_rutina integer PRIMARY KEY AUTOINCREMENT," +
                "nombre text, " +
                "tiempo_total text," +
                "dificultad int)");

        db.execSQL("CREATE TABLE etapas_aux(" +
                "ID_etapa integer PRIMARY KEY AUTOINCREMENT," +
                "numero_etapa int," +
                "tiempo int," +
                "tiempo_descanso int," +
                "estimulo text," +
                "ID_ejercicio int," +
                "rep_ejercicio int)");

    }
}