package com.example.start.proyecto_i1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class modo_manual extends AppCompatActivity implements View.OnClickListener{

    ImageButton btn_mmregresar;
    Button mm_btnizcabeza;
    Button mm_btndercabeza;
    Button mm_btncara;
    Button mm_btnpecho;
    Button mm_btnestomago;
    Button mm_btniztorso;
    Button mm_btndertorso;
    Button mm_play;
    Button mm_pause;
    Button mm_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modo_manual);

        btn_mmregresar = (ImageButton)findViewById(R.id.btn_mmregresar);
        mm_btnizcabeza = (Button)findViewById(R.id.mm_btnizcabeza);
        mm_btndercabeza = (Button)findViewById(R.id.mm_btndercabeza);
        mm_btncara = (Button)findViewById(R.id.mm_btncara);
        mm_btnpecho = (Button)findViewById(R.id.mm_btnpecho);
        mm_btnestomago = (Button)findViewById(R.id.mm_btnestomago);
        mm_btniztorso = (Button)findViewById(R.id.mm_btniztorso);
        mm_btndertorso = (Button)findViewById(R.id.mm_btndertorso);
        mm_play = (Button)findViewById(R.id.mm_play);
        mm_pause = (Button)findViewById(R.id.mm_pause);
        mm_stop = (Button)findViewById(R.id.mm_stop);




        btn_mmregresar.setOnClickListener(this);
        mm_btnizcabeza.setOnClickListener(this);
        mm_btndercabeza.setOnClickListener(this);
        mm_btncara.setOnClickListener(this);
        mm_btncara.setOnClickListener(this);
        mm_btnpecho.setOnClickListener(this);
        mm_btnestomago.setOnClickListener(this);
        mm_btniztorso.setOnClickListener(this);
        mm_btndertorso.setOnClickListener(this);
        mm_play.setOnClickListener(this);
        mm_pause.setOnClickListener(this);
        mm_stop.setOnClickListener(this);

        mm_btnizcabeza.setEnabled(false);
        mm_btndercabeza.setEnabled(false);
        mm_btncara.setEnabled(false);
        mm_btnpecho.setEnabled(false);
        mm_btnestomago.setEnabled(false);
        mm_btniztorso.setEnabled(false);
        mm_btndertorso.setEnabled(false);



    }

    @Override
    public void onClick(View v){

        int id_btn = v.getId();

        Intent intent =new Intent(this, modo_manual.class);
        Toast toast1;


        switch (id_btn){
            case R.id.btn_mmregresar:
                intent = new Intent(this, modos.class);
                startActivity(intent);
                break;

            case R.id.mm_btnizcabeza:
                toast1 = Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT);
                toast1.show();

                break;

            case R.id.mm_btndercabeza:
                toast1 = Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT);
                toast1.show();

                break;

            case R.id.mm_btncara:
                toast1 = Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.mm_btnpecho:
                toast1 = Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.mm_btnestomago:
                toast1 = Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.mm_btniztorso:
                toast1 = Toast.makeText(getApplicationContext(), "6", Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.mm_btndertorso:
                toast1 = Toast.makeText(getApplicationContext(), "7", Toast.LENGTH_SHORT);
                toast1.show();

                break;

            case R.id.mm_play:
                mm_btnizcabeza.setEnabled(true);
                mm_btndercabeza.setEnabled(true);
                mm_btncara.setEnabled(true);
                mm_btnpecho.setEnabled(true);
                mm_btnestomago.setEnabled(true);
                mm_btniztorso.setEnabled(true);
                mm_btndertorso.setEnabled(true);


                break;

            case R.id.mm_pause:
                mm_btnizcabeza.setEnabled(false);
                mm_btndercabeza.setEnabled(false);
                mm_btncara.setEnabled(false);
                mm_btnpecho.setEnabled(false);
                mm_btnestomago.setEnabled(false);
                mm_btniztorso.setEnabled(false);
                mm_btndertorso.setEnabled(false);


                break;

            case R.id.mm_stop:
                mm_btnizcabeza.setEnabled(false);
                mm_btndercabeza.setEnabled(false);
                mm_btncara.setEnabled(false);
                mm_btnpecho.setEnabled(false);
                mm_btnestomago.setEnabled(false);
                mm_btniztorso.setEnabled(false);
                mm_btndertorso.setEnabled(false);

                break;



        }


    }



}
