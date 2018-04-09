package com.example.start.proyecto_i1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class lista_rutinas extends AppCompatActivity  implements View.OnClickListener{
    ImageButton btn_lrregresar;
    ImageButton btn_agrutina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_rutinas);
        btn_lrregresar=findViewById(R.id.btn_lrregresar);
        btn_agrutina=findViewById(R.id.btn_agrutina);


        btn_lrregresar.setOnClickListener(this);
        btn_agrutina.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        int btn_id = v.getId();
        Intent intent =new Intent(this, MainActivity.class);

        switch (btn_id){
            case R.id.btn_lrregresar:
                intent = new Intent(this, opciones.class);
                break;

            case R.id.btn_agrutina:
                intent = new Intent(this, agregarrutinauno.class);
                break;





        }
        startActivity(intent);

    }
}
