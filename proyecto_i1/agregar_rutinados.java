package com.example.start.proyecto_i1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.start.proyecto_i1.Controladores.EtapaControlador;
import com.example.start.proyecto_i1.Controladores.RutinaControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.EtapaModelo;
import com.example.start.proyecto_i1.modelos.RutinaModelo;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class agregar_rutinados extends AppCompatActivity implements View.OnClickListener {

    protected ImageButton btnRutRegresar;
    protected Button btnRutSiguiente;
    protected EditText editRutNombre;
    protected Spinner spnDificultad;



    int idUsuario = seleccionar_usuario.idUser;
    protected RutinaModelo rutinaNueva = new RutinaModelo();
    protected ArrayList<EtapaModelo> listaEtapas = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_rutinados);

        //Intent datosUsuario = getIntent();
        //idUsuario = datosUsuario.getIntExtra("idUsuario", 0);

        //SharedPreferences datosUsario = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        //idUsuario = datosUsario.getInt("idUsuario", 0);


        btnRutRegresar = (ImageButton) findViewById(R.id.btnRutRegresar);
        btnRutSiguiente = (Button) findViewById(R.id.btnRutSiguiente);
        editRutNombre = (EditText) findViewById(R.id.editRutNombre);
        spnDificultad = (Spinner) findViewById(R.id.spnDificultad);



        btnRutRegresar.setOnClickListener(this);
        btnRutSiguiente.setOnClickListener(this);

        ArrayAdapter spnDificultadadAdapter = ArrayAdapter.createFromResource( this, R.array.dificultad, android.R.layout.simple_spinner_item);
        spnDificultadadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDificultad.setAdapter(spnDificultadadAdapter);


    }


    public void onClick(View v) {
        int idBtn = v.getId();
        final Toast toast1;                                               //Toast para pruebas
        Intent intent = new Intent(this, modos.class);


        switch (idBtn) {
            case R.id.btnRutRegresar:
                intent = new Intent(this, lista_rutinas.class);
                startActivity(intent);
                break;


            case R.id.btnRutSiguiente:

                if(editRutNombre.getText().toString().isEmpty()){
                    AlertDialog.Builder noNombre = new AlertDialog.Builder(agregar_rutinados.this);
                    noNombre.setTitle("ATENCIÓN");
                    noNombre.setMessage("Agrega un nombre a la rutina");
                    noNombre.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    noNombre.show();
                }else {

                    rutinaNueva.setIdUsuario(idUsuario);
                    rutinaNueva.setDificultad(spnDificultad.getSelectedItem().toString());
                    rutinaNueva.setNombre(editRutNombre.getText().toString());
                    System.out.println(rutinaNueva.getNombre());
                    intent = new Intent(this, agregarrutinauno.class);
                    intent.putExtra("rutina", rutinaNueva);
                    startActivity(intent);
                }
                break;


        }




    }

}

