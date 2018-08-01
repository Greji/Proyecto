package com.example.start.proyecto_i1;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.start.proyecto_i1.Bluetooth.Bluetooth;
import com.example.start.proyecto_i1.Bluetooth.DispositivosBT;
import com.example.start.proyecto_i1.Controladores.UsuarioControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;

import java.io.IOException;

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

    protected CountDownTimer temporizador;

    Bluetooth conexion;
    DBHelper admin=new DBHelper(this,"GRECS",null,1);
    UsuarioControlador usuarioControlador = new UsuarioControlador(admin);

    boolean pausado = false;

    protected estandar_datos modoPredeterminado = new estandar_datos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modo_predeterminado);
        modoPredeterminado.modo = "1";

        conexion = new Bluetooth();
        conexion.entradabt();
        conexion.address = DispositivosBT.address;
        conexion.inicializar();



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
        mpre_segundos = (EditText)findViewById(R.id.mpre_segundos);
        mpre_minutos = (EditText)findViewById(R.id.mpre_minutos);






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


        mpre_minutos.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                int minutes = Integer.parseInt(mpre_minutos.getText().toString());
                if( minutes>30 ){
                    mpre_minutos.setText("30");
                }

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}



        });

        mpre_segundos.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                if(mpre_segundos.getText().toString().isEmpty()){
                    mpre_segundos.setText("00");
                }
                int seconds = Integer.parseInt(mpre_segundos.getText().toString());

                if( seconds>59 ){
                    mpre_segundos.setText("59");
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}



        });

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

        @Override
        public void onClick(View v){

            int id_btn = v.getId();

            Intent intent =new Intent(this, modo_manual.class);

            switch (id_btn) {


                case R.id.btn_mpreregresar:
                    intent = new Intent(this, modos.class);
                    startActivity(intent);
                    break;

                case R.id.mpre_visual:
                    modoPredeterminado.tipo_estimulo="1";
                    confTipoEstimulo("1");
                    break;

                case R.id.mpre_audio:
                    modoPredeterminado.tipo_estimulo="0";
                    confTipoEstimulo("0");
                    break;

                case R.id.btn_baja:
                    conf_nivelbajo();
                    break;

                case R.id.btn_media:
                    conf_nivelmedio();
                    break;

                case R.id.btn_alta:
                    conf_nivelalto();
                    break;

                case R.id.btn_muyalta:
                    conf_nivelmuyalto();
                    break;


                case R.id.mpre_play:

                    mpre_play.setEnabled(false);
                    mpre_pause.setEnabled(true);
                    mpre_stop.setEnabled(true);
                    mpre_minutos.setEnabled(false);
                    mpre_segundos.setEnabled(false);


                    if(pausado==false) {
                        conexion.MyConexionBT.write(String.valueOf(modoPredeterminado.modo)+
                                modoPredeterminado.tipo_estimulo +
                                usuarioControlador.getConfiguracionById(seleccionar_usuario.idUser) +
                                modoPredeterminado.estimulo + modoPredeterminado.tiempoEstimulo
                        );

                    }
                    else if(pausado==true){
                        conexion.MyConexionBT.write("I");
                        pausado = false;
                    }



                    int minutos= Integer.parseInt(mpre_minutos.getText().toString())*60;
                    int tiempo = minutos + (Integer.parseInt(mpre_segundos.getText().toString()));
                    tiempo*=1000;
                    temporizador = new CountDownTimer( tiempo, 1000) {

                        public void onTick(long millisUntilFinished) {
                            int min;

                            mpre_minutos.setText(""+ (int)(((millisUntilFinished / 1000)/60)));
                            min = (int)(millisUntilFinished/1000)/60;
                            mpre_segundos.setText("" + (int)((millisUntilFinished/1000) - (min*60)));


                        }

                        public void onFinish() {

                            mpre_play.setEnabled(true);
                            mpre_pause.setEnabled(false);
                            mpre_stop.setEnabled(false);
                            mpre_minutos.setText("00");
                            mpre_segundos.setText("00");
                            mpre_minutos.setEnabled(true);
                            mpre_segundos.setEnabled(true);
                            btn_mpreregresar.setEnabled(true);

                            Toast toast1 = Toast.makeText(getApplicationContext(),"1", Toast.LENGTH_SHORT);
                            toast1.show();

                            conexion.MyConexionBT.write("T");
                            AlertDialog.Builder terminoModo = new AlertDialog.Builder(modo_predeterminado.this);
                            terminoModo.setTitle("MODO PREDETERMINADO TERMINADO");
                            terminoModo.setMessage("Se ha completado el modo predeterminado");
                            terminoModo.setPositiveButton("VER RESULTADOS", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    esto_termino();
                                }
                            });
                            terminoModo.show();



                        }

                    }.start();
                    

                    break;

                case R.id.mpre_pause:

                    temporizador.cancel();
                    mpre_play.setEnabled(true);
                    mpre_pause.setEnabled(false);
                    mpre_stop.setEnabled(true);
                    conexion.MyConexionBT.write("P");
                    pausado = true;


                    break;

                case R.id.mpre_stop:
                    temporizador.cancel();
                    mpre_minutos.setText("00");
                    mpre_segundos.setText("00");
                    mpre_minutos.setEnabled(true);
                    mpre_segundos.setEnabled(true);
                    mpre_play.setEnabled(true);
                    mpre_pause.setEnabled(false);
                    mpre_stop.setEnabled(false);
                    conexion.MyConexionBT.write("T");


                    break;

            }





    }

    public void confTipoEstimulo(String tipo){
            if(tipo=="0"){
                mpre_audio.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorAccent));
                mpre_visual.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));
            }
            if(tipo=="1"){
                mpre_visual.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorAccent));
                mpre_audio.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));
            }

    }

    public void conf_nivelbajo(){
        modoPredeterminado.tiempoEstimulo = "10";

        btn_baja.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorAccent));
        btn_media.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));
        btn_alta.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));
        btn_muyalta.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));


    }


    public void conf_nivelmedio(){
        modoPredeterminado.tiempoEstimulo = "7";
        btn_baja.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));
        btn_media.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorAccent));
        btn_alta.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));
        btn_muyalta.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));

    }

    public void conf_nivelalto(){
        modoPredeterminado.tiempoEstimulo = "5";
        btn_baja.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));
        btn_media.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));
        btn_alta.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorAccent));
        btn_muyalta.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));
    }

    public void conf_nivelmuyalto(){
        modoPredeterminado.tiempoEstimulo = "3";
        btn_baja.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));
        btn_media.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));
        btn_alta.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorPrimaryDark));
        btn_muyalta.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorAccent));
    }

    void esto_termino(){
        Intent intent1 = new Intent(this, registro_dia.class);
        String a = conexion.hola;

        intent1.putExtra("datos",conexion.request);
        startActivity(intent1);


    }


}
