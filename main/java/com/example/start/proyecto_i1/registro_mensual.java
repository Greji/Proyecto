package com.example.start.proyecto_i1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class registro_mensual extends AppCompatActivity implements View.OnClickListener{

    Button btn_gfuerza;
    Button btn_gtiempo;
    ImageButton btn_rmregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_mensual);
        btn_gfuerza = findViewById(R.id.btn_gfuerza);
        btn_gtiempo = findViewById(R.id.btn_gtiempo);
        btn_rmregresar = findViewById(R.id.btn_rmregresar);

        btn_gfuerza.setOnClickListener(this);
        btn_gtiempo.setOnClickListener(this);
        btn_rmregresar.setOnClickListener(this);

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
