package com.example.start.proyecto_i1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    protected Button btn_sel;
    protected Button btn_crear;
    protected Button btn_invi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sel = (Button)findViewById(R.id.btn_sel);
        btn_crear = (Button)findViewById(R.id.btn_crear);
        btn_invi = (Button)findViewById(R.id.btn_invi);

        btn_sel.setOnClickListener(this);
        btn_crear.setOnClickListener(this);
        btn_invi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        int btn_id = v.getId();
        Intent intent =new Intent(this, MainActivity.class);

        switch (btn_id){
            case R.id.btn_sel:
                intent = new Intent(this, seleccionar_usuario.class);
                break;

            case R.id.btn_crear:
                intent = new Intent(this, agregar_usuario.class);
                break;

            case R.id.btn_invi:
                intent = new Intent(this, opciones.class);
                break;


        }
        startActivity(intent);

    }

}


