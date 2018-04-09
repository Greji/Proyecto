package com.example.start.proyecto_i1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;

public class agregar_usuario extends AppCompatActivity implements View.OnClickListener{

    protected ImageButton btn_auregresar;
    protected Button btn_ag;
    protected EditText nuevonombre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_usuario);


        btn_auregresar = (ImageButton)findViewById(R.id.btn_auregresar);
        btn_ag = (Button)findViewById(R.id.btn_ag);
        nuevonombre=(EditText)findViewById(R.id.nuevonombre);

        btn_auregresar.setOnClickListener(this);
        btn_ag.setOnClickListener(this);
    }



    @Override
    public void onClick(View v){
        int id_btn = v.getId();
        Intent intent =new Intent(this, agregar_usuario.class);

        switch (id_btn){
            case R.id.btn_auregresar:
                intent = new Intent(this, MainActivity.class);
                break;

            case R.id.btn_ag:
                String nombre = nuevonombre.getText().toString();
                intent = new Intent(this, MainActivity.class);
                break;



        }

        startActivity(intent);

    }
}
