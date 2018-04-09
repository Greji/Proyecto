package com.example.start.proyecto_i1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class registro_dia extends AppCompatActivity implements View.OnClickListener{

    protected ImageButton btn_diaregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_dia);

        btn_diaregresar = findViewById(R.id.btn_diaregresar);

        btn_diaregresar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v){

        int id_btn = v.getId();
        Intent intent =new Intent(this, MainActivity.class);

        switch (id_btn){

            case R.id.btn_diaregresar:
                intent = new Intent(this, seleccionar_usuario.class);
                break;

        }
        startActivity(intent);

    }
}
