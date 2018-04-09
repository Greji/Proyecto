package com.example.start.proyecto_i1;

import android.content.Intent;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class modo_personalizado extends AppCompatActivity implements View.OnClickListener{

    protected ImageButton mperregresar;
    protected Button mper_visual;
    protected Button mper_audio;
    protected Button mper_play;
    protected Button mper_pause;
    protected Button mper_stop;
    protected EditText text_mpertestimulo;
    protected EditText mper_minutos;
    protected EditText mper_segundos;
    protected RadioButton mper_testimulo;
    protected Switch switch_mperizcab;
    protected Switch switch_mperdercab;
    protected Switch switch_mpercara;
    protected Switch switch_mperpecho;
    protected Switch switch_mperestomago;
    protected Switch switch_mperiztorso;
    protected Switch switch_mperdertorso;

    protected estandar_datos m_personalizado = new estandar_datos();

    protected CountDownTimer temporizador;

    Bluetooth conexion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modo_personalizado);

        conexion = new Bluetooth();
        conexion.entradabt();
        conexion.address = getIntent().getStringExtra("direccion");
        Toast toast2 = Toast.makeText(getApplicationContext(), conexion.address, Toast.LENGTH_SHORT);


        conexion.inicializar();

        mperregresar = (ImageButton)findViewById(R.id.mperregresar);
        mper_visual = (Button)findViewById(R.id.mper_visual);
        mper_audio = (Button)findViewById(R.id.mper_audio);
        mper_play = (Button)findViewById(R.id.mper_play);
        mper_pause = (Button)findViewById(R.id.mper_pause);
        mper_stop = (Button)findViewById(R.id.mper_stop);
        text_mpertestimulo = (EditText)findViewById(R.id.text_mpertestimulo);
        mper_minutos = (EditText)findViewById(R.id.mper_minutos);
        mper_segundos = (EditText) findViewById(R.id.mper_segundos);
        mper_testimulo = (RadioButton) findViewById(R.id.mper_testimulo);
        switch_mperizcab = (Switch) findViewById(R.id.switch_mperizcab);
        switch_mperdercab = (Switch) findViewById(R.id.switch_mperdercab);
        switch_mpercara = (Switch) findViewById(R.id.switch_mpercara);
        switch_mperpecho = (Switch) findViewById(R.id.switch_mperpecho);
        switch_mperestomago = (Switch) findViewById(R.id.switch_mperestomago);
        switch_mperiztorso = (Switch) findViewById(R.id.switch_mperiztorso);
        switch_mperdertorso = (Switch) findViewById(R.id.switch_mperdertorso);




        mperregresar.setOnClickListener(this);
        mper_visual.setOnClickListener(this);
        mper_audio.setOnClickListener(this);
        mper_play.setOnClickListener(this);
        mper_pause.setOnClickListener(this);
        mper_stop.setOnClickListener(this);
        switch_mperizcab.setOnClickListener(this);
        switch_mperdercab.setOnClickListener(this);
        switch_mpercara.setOnClickListener(this);
        switch_mperpecho.setOnClickListener(this);
        switch_mperestomago.setOnClickListener(this);
        switch_mperiztorso.setOnClickListener(this);
        switch_mperdertorso.setOnClickListener(this);





    }

    @Override
    public void onResume(){
        super.onResume();
        conexion.crearConexion();
    }

   @Override
    public void onPause(){
        super.onPause();
        conexion.sale();

    }

    public void onClick(View v){
        int id_btn = v.getId();
        Intent intent =new Intent(this, modos.class);

        if(!switch_mperizcab.isChecked() && !switch_mperdercab.isChecked() &&
                !switch_mpercara.isChecked() && !switch_mperpecho.isChecked() &&
                !switch_mperestomago.isChecked() && !switch_mperdertorso.isChecked() &&
                !switch_mperiztorso.isChecked()){

            switch_mpercara.setChecked(true);

        }

        switch (id_btn) {
            case R.id.mperregresar:
                intent = new Intent(this, modos.class);
                startActivity(intent);
                break;

            case R.id.mper_visual:
                m_personalizado.tipo_estimulo = "1";
                break;

            case R.id.mper_audio:
                m_personalizado.tipo_estimulo = "0";
                break;

            case R.id.mper_play:

                mper_play.setEnabled(false);
                mper_pause.setEnabled(true);
                mper_stop.setEnabled(true);
                mper_minutos.setEnabled(false);
                mper_segundos.setEnabled(false);


                conf_play();

                Toast toast1;
                toast1 = Toast.makeText(getApplicationContext(),m_personalizado.juntar(), Toast.LENGTH_SHORT);
                toast1.show();

                conexion.MyConexionBT.write(m_personalizado.juntar());


                int minutos= Integer.parseInt(mper_minutos.getText().toString())*60;
                int tiempo = minutos + (Integer.parseInt(mper_segundos.getText().toString()));
                tiempo*=1000;
                temporizador = new CountDownTimer( tiempo, 1000) {

                    public void onTick(long millisUntilFinished) {
                        int min;

                        mper_minutos.setText(""+ (int)(((millisUntilFinished / 1000)/60)));
                        min = (int)(millisUntilFinished/1000)/60;
                        mper_segundos.setText("" + (int)((millisUntilFinished/1000) - (min*60)));


                    }

                    public void onFinish() {

                        mper_play.setEnabled(true);
                        mper_pause.setEnabled(false);
                        mper_stop.setEnabled(false);
                        mper_minutos.setText("00");
                        mper_segundos.setText("00");
                        mper_minutos.setEnabled(true);
                        mper_minutos.setEnabled(true);
                        mperregresar.setEnabled(true);

                    }

                }.start();



                break;

            case R.id.mper_pause:
                temporizador.cancel();
                mper_play.setEnabled(true);
                mper_pause.setEnabled(false);
                mper_stop.setEnabled(true);


                break;

            case R.id.mper_stop:
                temporizador.cancel();
                mper_minutos.setText("00");
                mper_segundos.setText("00");
                mper_minutos.setEnabled(true);
                mper_segundos.setEnabled(true);
                mper_play.setEnabled(true);
                mper_pause.setEnabled(false);
                mper_stop.setEnabled(false);


                break;

        }

    }

    public void conf_play(){
                                                                                            //Tiempo entre estímulo
        if(mper_testimulo.isChecked()){
            m_personalizado.t_estimulo = text_mpertestimulo.getText().toString();
        }
        else{
            m_personalizado.t_estimulo = "0";
        }

                                                                                            //Estimulos que estarán activos
        if (switch_mperizcab.isChecked()){ m_personalizado.estimulo[0]='1'; }
        else{ m_personalizado.estimulo[0]='0';}

        if (switch_mperdercab.isChecked()){ m_personalizado.estimulo[1]='1'; }
        else{ m_personalizado.estimulo[1]='0';}
        if (switch_mpercara.isChecked()){ m_personalizado.estimulo[2]='1'; }
        else{ m_personalizado.estimulo[2]='0';}

        if (switch_mperpecho.isChecked()){ m_personalizado.estimulo[3]='1'; }
        else{ m_personalizado.estimulo[3]='0';}

        if (switch_mperestomago.isChecked()){ m_personalizado.estimulo[4]='1'; }
        else{ m_personalizado.estimulo[4]='0';}

        if (switch_mperiztorso.isChecked()){ m_personalizado.estimulo[5]='1'; }
        else{ m_personalizado.estimulo[5]='0';}

        if (switch_mperdertorso.isChecked()){ m_personalizado.estimulo[6]='1'; }
        else{ m_personalizado.estimulo[6]='0';}

    }
}


