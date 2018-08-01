package com.example.start.proyecto_i1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.start.proyecto_i1.Controladores.UsuarioControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;

public class Configuraciones extends AppCompatActivity implements View.OnClickListener {

    Button conf_aceptar;
    ImageButton btn_conregresar;
    Switch switch_sensorprox;
    Switch switch_sensorfuerza;
    Spinner son_izcab;
    Spinner son_dercab;
    Spinner son_cara;
    Spinner son_pecho;
    Spinner son_iztorso;
    Spinner son_dertorso;
    Spinner son_estomago;

    DBHelper admin=new DBHelper(this,"GRECS",null,1);
    UsuarioControlador uController = new UsuarioControlador(admin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuraciones);

        iniElementos();


    }

    @Override
    public void onClick(View v){

        int btn_id = v.getId();
        Intent intent =new Intent(this, MainActivity.class);

        switch (btn_id){
            case R.id.btn_conregresar:
                intent = new Intent(this, opciones.class);
                break;

            case R.id.conf_aceptar:
                Intent datosUsuario = getIntent();
                int ID = datosUsuario.getIntExtra("idUsuario", 0);
                intent = new Intent(this, opciones.class);
                uController.updateConfiguracion(ID, asignarconfiguraciones());


                break;


        }
        startActivity(intent);

    }

    private String asignarconfiguraciones(){

        String configuraciones = "";

        if(switch_sensorprox.isChecked()){
            configuraciones += "1";
        }else{configuraciones += "0";}

        if(switch_sensorfuerza.isChecked()){
            configuraciones += "1";
        }else{ configuraciones += "0";}
        System.out.println(configuraciones);



        configuraciones += son_izcab.getSelectedItem().toString();
        configuraciones += "-";
        configuraciones += son_dercab.getSelectedItem().toString();
        configuraciones += "-";
        configuraciones += son_cara.getSelectedItem().toString();
        configuraciones += "-";
        configuraciones += son_pecho.getSelectedItem().toString();
        configuraciones += "-";
        configuraciones += son_iztorso.getSelectedItem().toString();
        configuraciones += "-";
        configuraciones += son_dertorso.getSelectedItem().toString();
        configuraciones += "-";
        configuraciones += son_estomago.getSelectedItem().toString();
        configuraciones += "#";

        return configuraciones;


    }

    private void iniElementos(){

        String confUsuario = "";

        btn_conregresar = findViewById(R.id.btn_conregresar);
        conf_aceptar = findViewById(R.id.conf_aceptar);
        switch_sensorprox = findViewById(R.id.switch_sensorprox);
        switch_sensorfuerza = findViewById(R.id.switch_sensorfuerza);
        son_cara = (Spinner) findViewById(R.id.son_cara);
        son_dercab = (Spinner) findViewById(R.id.son_dercab);
        son_izcab = (Spinner) findViewById(R.id.son_izcab);
        son_pecho = (Spinner) findViewById(R.id.son_pecho);
        son_iztorso = (Spinner) findViewById(R.id.son_iztorso);
        son_dertorso = (Spinner) findViewById(R.id.son_dertorso);
        son_estomago = (Spinner) findViewById(R.id.son_estomago);


        ArrayAdapter spinnerNotasAdapter = ArrayAdapter.createFromResource( this, R.array.notas, android.R.layout.simple_spinner_item);
        spinnerNotasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        son_cara.setAdapter(spinnerNotasAdapter);
        son_dercab.setAdapter(spinnerNotasAdapter);
        son_izcab.setAdapter(spinnerNotasAdapter);
        son_pecho.setAdapter(spinnerNotasAdapter);
        son_iztorso.setAdapter(spinnerNotasAdapter);
        son_dertorso.setAdapter(spinnerNotasAdapter);
        son_estomago.setAdapter(spinnerNotasAdapter);


        int idUsuario = seleccionar_usuario.idUser;

        confUsuario = uController.getConfiguracionById(idUsuario);

        son_cara.setSelection(0);
        son_izcab.setSelection(1);
        son_dercab.setSelection(2);
        son_pecho.setSelection(3);
        son_iztorso.setSelection(4);
        son_dertorso.setSelection(5);
        son_estomago.setSelection(6);


        btn_conregresar.setOnClickListener(this);
        conf_aceptar.setOnClickListener(this);
    }
}