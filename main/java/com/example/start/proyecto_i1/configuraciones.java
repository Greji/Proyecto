package com.example.start.proyecto_i1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

public class configuraciones extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn_conregresar;
    Switch switch_sensorprox;
    Switch switch_sensorfuerza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuraciones);

        btn_conregresar = findViewById(R.id.btn_conregresar);
        switch_sensorprox = findViewById(R.id.switch_sensorprox);
        switch_sensorfuerza = findViewById(R.id.switch_sensorfuerza);

        btn_conregresar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){

        int btn_id = v.getId();
        Intent intent =new Intent(this, MainActivity.class);

        switch (btn_id){
            case R.id.btn_conregresar:
                intent = new Intent(this, opciones.class);
                break;


        }
        startActivity(intent);

    }
}
