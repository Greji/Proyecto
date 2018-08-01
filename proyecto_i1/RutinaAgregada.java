package com.example.start.proyecto_i1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.start.proyecto_i1.Adaptadores.ListaE_Adaptador;
import com.example.start.proyecto_i1.Controladores.EjercicioControlador;
import com.example.start.proyecto_i1.Controladores.EtapaControlador;
import com.example.start.proyecto_i1.Controladores.RutinaControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.RutinaModelo;

public class RutinaAgregada extends AppCompatActivity {

    Button btnRutOk;
    TextView txtMostrarNombre;
    TextView txtMostrarId;
    TextView txtMostrarDificultad;
    TextView txtMostrarTiempo;
    TextView txtMostrarIdUsuario;
    ListView listMostrarEtapas;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregarrutina);

        Intent i = getIntent();
        int idRutina = i.getIntExtra("id", -1);

        SharedPreferences datosUsuario = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        int idUsuario = datosUsuario.getInt("idUsuario", 0);
        System.out.println(idUsuario);

        System.out.println("Id de rutina");
        System.out.println(idRutina);

        DBHelper admin = new DBHelper(this, "GRECS", null, 1);
        EjercicioControlador ejControlador = new EjercicioControlador(admin);
        RutinaControlador rControlador = new RutinaControlador(admin);
        EtapaControlador etControlador = new EtapaControlador(admin);

        txtMostrarId = (TextView)findViewById(R.id.txtMostrarId);
        txtMostrarNombre = (TextView)findViewById(R.id.txtMostrarNombre);
        txtMostrarDificultad = (TextView)findViewById(R.id.txtMostrarDificultad);
        txtMostrarTiempo = (TextView)findViewById(R.id.txtMostrarTiempo);
        txtMostrarIdUsuario = (TextView)findViewById(R.id.txtMostrarUsuario);
        listMostrarEtapas = (ListView) findViewById(R.id.listMostrarEtapas);
        btnRutOk = (Button) findViewById(R.id.btnRutOk);

        txtMostrarId.setText(String.valueOf(idRutina));
        RutinaModelo rutina = rControlador.getRutinaById(idRutina, 2);

        txtMostrarNombre.setText("Nombre " + rutina.getNombre());
        txtMostrarTiempo.setText("Tiempo "+String.valueOf(rutina.getTiempoTotal()));
        txtMostrarDificultad.setText("Dificultad "+rutina.getDificultad());
        txtMostrarIdUsuario.setText("Id Usuario "+String.valueOf(rutina.getIdUsuario()));

        listMostrarEtapas.setAdapter(new ListaE_Adaptador(this, etControlador.getAllEtapas(rutina.getIdRutina())));

        btnRutOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(RutinaAgregada.this, lista_rutinas.class));
            }
        });

    }
}
