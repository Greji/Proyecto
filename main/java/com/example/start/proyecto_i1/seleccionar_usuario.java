package com.example.start.proyecto_i1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class seleccionar_usuario extends AppCompatActivity implements View.OnClickListener {

    Button usuario_1;
    ImageButton btn_suregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccionarusuario);

        btn_suregresar = (ImageButton)findViewById(R.id.btn_suregresar);
        usuario_1 = (Button) findViewById(R.id.btn_us1);

        btn_suregresar.setOnClickListener(this);
        usuario_1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        int btn_id = view.getId();
        Intent intent =new Intent(this, agregar_usuario.class);

        switch (btn_id){
            case R.id.btn_suregresar:
                intent = new Intent(this, MainActivity.class);
                break;

            case R.id.btn_us1:

                //Código SQLite

                intent = new Intent(this, opciones.class);
                break;



        }
        startActivity(intent);



    }


}
