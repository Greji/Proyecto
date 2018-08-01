package com.example.start.proyecto_i1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.start.proyecto_i1.Adaptadores.ListaRM_Adaptador;
import com.example.start.proyecto_i1.Controladores.RegistroControlador;
import com.example.start.proyecto_i1.Controladores.UsuarioControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.RegistroModelo;

import java.util.ArrayList;

public class registro_mensual extends AppCompatActivity implements View.OnClickListener{

    Button btn_gfuerza;
    Button btn_gtiempo;
    ImageButton btn_rmregresar;
    ListView lstRegistroMensual;

    DBHelper admin=new DBHelper(this,"GRECS",null,1);
    RegistroControlador regController = new RegistroControlador(admin);

    ArrayList<RegistroModelo> listaRegistro = new ArrayList<>();
    ListaRM_Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_mensual);
        btn_gfuerza = findViewById(R.id.btn_gfuerza);
        btn_gtiempo = findViewById(R.id.btn_gtiempo);
        btn_rmregresar = findViewById(R.id.btn_rmregresar);
        lstRegistroMensual = findViewById(R.id.lstRegistroMensual);

        btn_gfuerza.setOnClickListener(this);
        btn_gtiempo.setOnClickListener(this);
        btn_rmregresar.setOnClickListener(this);


        listaRegistro = regController.getAllRegistros();
        if (!listaRegistro.isEmpty()) {
            adaptador = new ListaRM_Adaptador(this, listaRegistro);
            lstRegistroMensual.setAdapter(adaptador);

            lstRegistroMensual.setAdapter(new ListaRM_Adaptador(registro_mensual.this, listaRegistro));

        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(registro_mensual.this)
                    .setTitle("NO HAY REGISTROS")
                    .setMessage("No hay ningún registro")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }});
            AlertDialog alert = builder.create();
            alert.show();
            btn_gtiempo.setVisibility(View.INVISIBLE);
            btn_gfuerza.setVisibility(View.INVISIBLE);

        }


    }

    @Override
    public void onClick(View v){

        int btn_id = v.getId();
        Intent intent =new Intent(this, MainActivity.class);

        switch (btn_id){
            case R.id.btn_rmregresar:
                intent = new Intent(this, opciones.class);
                break;

            case R.id.btn_gfuerza:
                intent = new Intent(this, grafica_fuerza.class);
                break;

            case R.id.btn_gtiempo:
                intent = new Intent(this, grafica_tiempo.class);
                break;



        }
        startActivity(intent);

    }
}
