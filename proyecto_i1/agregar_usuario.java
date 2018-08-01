package com.example.start.proyecto_i1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;

import com.example.start.proyecto_i1.Controladores.UsuarioControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;

import com.example.start.proyecto_i1.modelos.UsuarioModelo;

public class agregar_usuario extends AppCompatActivity implements View.OnClickListener{


    protected Button btnAgUsuario;
    protected EditText editAgNombre;
    protected ImageButton btnAgRegresar;
    protected DBHelper admin = new DBHelper(this,"GRECS",null,1);
    protected UsuarioControlador uControlador = new UsuarioControlador(admin);
    protected UsuarioModelo usuario = new UsuarioModelo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_usuario);


        btnAgRegresar = (ImageButton)findViewById(R.id.btnAgRegresar);
        btnAgUsuario = (Button)findViewById(R.id.btnAgUsuario);
        editAgNombre=(EditText)findViewById(R.id.editAgNombre);

        btnAgRegresar.setOnClickListener(this);
        btnAgUsuario.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int idBoton = v.getId();
        Intent intent =new Intent(this, agregar_usuario.class);

        switch (idBoton){
            case R.id.btnAgRegresar:
                intent = new Intent(this, MainActivity.class);
                break;

            case R.id.btnAgUsuario:
                String txtNombre = editAgNombre.getText().toString();
                if(txtNombre.isEmpty()){
                    mensajesAlerta("Ingresa un nombre");
                }
                else if(txtNombre.contains("&") || txtNombre.contains("%") || txtNombre.contains("¡") || txtNombre.contains("!") || txtNombre.contains("?") || txtNombre.contains("¿") || txtNombre.contains(";")|| txtNombre.contains("*")){
                    mensajesAlerta("El nombre no puede tener los siguientes caracteres:  & % ¡ !  ¿ ? ; *");
                }else {
                    usuario.setNombre(txtNombre);
                    if(uControlador.isSetUsuario(usuario.getNombre())){
                        mensajesAlerta("Ya existe un usuario con ese nombre, ingrese un nombre distinto");
                    }else {

                        usuario.setConfiguracion("00DO-RE-MI-FA-SOL-LA-SI#");
                        uControlador.setUsuarios(usuario);
                        intent = new Intent(this, MainActivity.class);
                    }
                }
        }
        startActivity(intent);

    }

    protected void mensajesAlerta(String mensaje){
        AlertDialog.Builder existeUsuario = new AlertDialog.Builder(agregar_usuario.this);
        existeUsuario.setTitle("ATENCIÓN");
        existeUsuario.setMessage(mensaje);
        existeUsuario.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        existeUsuario.show();
    }

}

