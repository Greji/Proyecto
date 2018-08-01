package com.example.start.proyecto_i1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class modos extends AppCompatActivity implements View.OnClickListener{

    protected Button btn_predeterminado;
    protected Button btn_manual;
    protected Button btn_personalizado;
    protected ImageButton btn_mregresar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.modos);

        btn_mregresar = findViewById(R.id.btn_mregresar);
        btn_predeterminado = (Button)findViewById(R.id.btn_predeterminado);
        btn_manual = (Button)findViewById(R.id.btn_manual);
        btn_personalizado = (Button)findViewById(R.id.btn_personalizado);


        btn_mregresar.setOnClickListener(this);
        btn_predeterminado.setOnClickListener(this);
        btn_manual.setOnClickListener(this);
        btn_personalizado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){


        int id_btn = v.getId();

        Intent intent =new Intent(this, modos.class);


        switch (id_btn){
            case R.id.btn_mregresar:
                intent = new Intent(this, opciones.class);
                break;

            case R.id.btn_predeterminado:
                intent = new Intent(this, modo_predeterminado.class);
                break;

            case R.id.btn_manual:
                intent = new Intent(this, modo_manual.class);
                break;

            case R.id.btn_personalizado:
                intent = new Intent(this, modo_personalizado.class);

                break;

        }

        String direccion = getIntent().getStringExtra("direccion");
        intent.putExtra("direccion", direccion);
        Toast toast2 = Toast.makeText(getApplicationContext(), direccion, Toast.LENGTH_SHORT);
        startActivity(intent);



    }
}
