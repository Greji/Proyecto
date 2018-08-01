package com.example.start.proyecto_i1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.start.proyecto_i1.Bluetooth.DispositivosBT;
import com.example.start.proyecto_i1.modelos.UsuarioModelo;


public class opciones extends AppCompatActivity implements View.OnClickListener{


    ImageButton btn_opregresar;
    Button btn_entrenar;
    Button btn_rutina;
    Button btn_config;
    Button btn_registro;
    Button btn_con;
    TextView nombreusuario;

    String direccion ="";


    UsuarioModelo usuario = new UsuarioModelo();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones);
        btn_opregresar = (ImageButton)findViewById(R.id.btn_opregresar);
        btn_entrenar = (Button)findViewById(R.id.btn_entrenar);
        btn_rutina = (Button)findViewById(R.id.btn_rutina);
        btn_config = (Button)findViewById(R.id.btn_config);
        btn_registro = (Button)findViewById(R.id.btn_registro);
        btn_con = (Button)findViewById(R.id.btn_con);

        nombreusuario = (TextView)findViewById(R.id.nombreusuario);

        btn_opregresar.setOnClickListener(this);
        btn_entrenar.setOnClickListener(this);
        btn_rutina.setOnClickListener(this);
        btn_config.setOnClickListener(this);
        btn_registro.setOnClickListener(this);
        btn_con.setOnClickListener(this);

        Intent datosUsuario = getIntent();
        usuario.setId_usuario(datosUsuario.getIntExtra("idUsuario", 0));
        usuario.setNombre(datosUsuario.getStringExtra("nombreUsuario"));

        if(usuario.getNombre()==null){
            usuario.setNombre("Invitado");
        }

        nombreusuario.setText(usuario.getNombre());




    }



    @Override
    public void onClick(View v){

        int id_btn = v.getId();
        Intent intent =new Intent(this, MainActivity.class);
        Intent i;


        switch (id_btn){

            case R.id.btn_opregresar:
                intent = new Intent(this, seleccionar_usuario.class);
                break;

            case R.id.btn_entrenar:
                intent = new Intent(this, modos.class);
                i = getIntent();
                direccion = i.getStringExtra(DispositivosBT.EXTRA_DEVICE_ADDRESS);
                break;

            case R.id.btn_rutina:
                intent = new Intent(this, lista_rutinas.class);
                intent.putExtra("idUsuario", usuario.getId_usuario());
                i = getIntent();
                direccion = i.getStringExtra(DispositivosBT.EXTRA_DEVICE_ADDRESS);

                break;

            case R.id.btn_config:
                intent = new Intent(this, Configuraciones.class);

                break;

            case R.id.btn_registro:
                intent = new Intent(this, registro_mensual.class);
                //----------------------------------------------------------------------------
                break;

            case R.id.btn_con:
                intent = new Intent(this, DispositivosBT.class);

                break;
        }

        intent.putExtra("direccion", direccion);
        intent.putExtra("idUsuario", usuario.getId_usuario());
        startActivity(intent);



    }




}
