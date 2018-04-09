package com.example.start.proyecto_i1;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.os.SystemClock;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class modo_predeterminado extends AppCompatActivity implements View.OnClickListener{


    protected ImageButton btn_mpreregresar;
    protected Button mpre_visual;
    protected Button mpre_audio;
    protected Button btn_baja;
    protected Button btn_media;
    protected Button btn_alta;
    protected Button btn_muyalta;
    protected ImageButton mpre_play;
    protected ImageButton mpre_pause;
    protected ImageButton mpre_stop;
    protected EditText mpre_segundos;
    protected EditText mpre_minutos;


    private static final long START_TIME_IN_MILLIS = 600000;
    protected CountDownTimer temporizador;
    private boolean mTimeRunning;
    private long mTimeLeftMilllis = START_TIME_IN_MILLIS;

    protected estandar_datos m_predeterminado = new estandar_datos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modo_predeterminado);

        btn_mpreregresar = (ImageButton) findViewById(R.id.btn_mpreregresar);
        mpre_visual = (Button) findViewById(R.id.mpre_visual);
        mpre_audio = (Button) findViewById(R.id.mpre_audio);
        btn_baja = (Button) findViewById(R.id.btn_baja);
        btn_media = (Button) findViewById(R.id.btn_media);
        btn_alta = (Button) findViewById(R.id.btn_alta);
        btn_muyalta = (Button) findViewById(R.id.btn_muyalta);
        mpre_play = (ImageButton) findViewById(R.id.mpre_play);
        mpre_pause = (ImageButton) findViewById(R.id.mpre_pause);
        mpre_stop = (ImageButton) findViewById(R.id.mpre_stop);





        btn_mpreregresar.setOnClickListener(this);
        mpre_visual.setOnClickListener(this);
        mpre_audio.setOnClickListener(this);
        btn_baja.setOnClickListener(this);
        btn_media.setOnClickListener(this);
        btn_alta.setOnClickListener(this);
        btn_muyalta.setOnClickListener(this);
        mpre_play.setOnClickListener(this);
        mpre_pause.setOnClickListener(this);
        mpre_stop.setOnClickListener(this);
    }



        @Override
        public void onClick(View v){

            int id_btn = v.getId();

            Intent intent =new Intent(this, modo_manual.class);

            switch (id_btn) {


                case R.id.btn_mpreregresar:
                    intent = new Intent(this, modos.class);
                    startActivity(intent);
                    break;

                case R.id.btn_baja:
                    conf_nivelbajo();
                    break;

                case R.id.btn_media:
                    m_predeterminado.nivel = "1";
                    m_predeterminado.t_estimulo = "7";
                    break;

                case R.id.btn_alta:
                    conf_nivelalto();
                    break;

                case R.id.btn_muyalta:
                    conf_nivelmuyalto();
                    break;


                case R.id.mpre_play:
                    m_predeterminado.tiempo ="00:00";
                    mpre_play.setEnabled(false);
                    mpre_pause.setEnabled(true);
                    mpre_stop.setEnabled(true);

                    btn_mpreregresar.setEnabled(false);
                    mpre_visual.setEnabled(false);
                    mpre_audio.setEnabled(false);
                    btn_baja.setEnabled(false);
                    btn_media.setEnabled(false);
                    btn_alta.setEnabled(false);
                    btn_muyalta.setEnabled(false);





                    Toast toast1;
                    toast1 = Toast.makeText(getApplicationContext(),m_predeterminado.juntar(), Toast.LENGTH_SHORT);
                    toast1.show();


                    break;

                case R.id.mpre_pause:
                    mpre_play.setEnabled(true);
                    mpre_pause.setEnabled(false);
                    mpre_pause.setEnabled(true);
                    break;

                case R.id.mpre_stop:
                    mpre_play.setEnabled(true);
                    mpre_pause.setEnabled(false);
                    mpre_pause.setEnabled(false);

                    btn_baja.setEnabled(true);
                    btn_media.setEnabled(true);
                    btn_alta.setEnabled(true);
                    btn_muyalta.setEnabled(true);
                    mpre_audio.setEnabled(true);
                    mpre_visual.setEnabled(true);
                    btn_mpreregresar.setEnabled(true);

                    //estado = '2';
                    break;

            }





    }

    public void conf_nivelbajo(){
        m_predeterminado.nivel = "0";
        m_predeterminado.t_estimulo = "10";
        btn_baja.setBackgroundColor(Color.parseColor("#8AD9D2"));
        btn_media.setBackgroundColor(Color.parseColor("#08AE9E"));
        btn_alta.setBackgroundColor(Color.parseColor("#08AE9E"));
        btn_muyalta.setBackgroundColor(Color.parseColor("#08AE9E"));

    }


    public void conf_nivelmedio(){

    }

    public void conf_nivelalto(){
        m_predeterminado.nivel = "2";
        m_predeterminado.t_estimulo = "5";
        btn_baja.setBackgroundColor(Color.parseColor("#08AE9E"));
        btn_media.setBackgroundColor(Color.parseColor("#08AE9E"));
        btn_alta.setBackgroundColor(Color.parseColor("#8AD9D2"));
        btn_muyalta.setBackgroundColor(Color.parseColor("#08AE9E"));
    }

    public void conf_nivelmuyalto(){
        m_predeterminado.nivel = "3";
        m_predeterminado.t_estimulo = "3";
        btn_baja.setBackgroundColor(Color.parseColor("#08AE9E"));
        btn_media.setBackgroundColor(Color.parseColor("#08AE9E"));
        btn_alta.setBackgroundColor(Color.parseColor("#08AE9E"));
        btn_muyalta.setBackgroundColor(Color.parseColor("#8AD9D2"));
    }


}
