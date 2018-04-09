package com.example.start.proyecto_i1;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class rutina extends AppCompatActivity implements View.OnClickListener{

    ImageButton btn_lrregresar;
    ImageButton btn_agrutina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutina);

        btn_lrregresar=findViewById(R.id.btn_lrregresar);
        btn_agrutina=findViewById(R.id.btn_agrutina);
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
