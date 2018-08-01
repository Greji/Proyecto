package com.example.start.proyecto_i1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.start.proyecto_i1.Bluetooth.Bluetooth;
import com.example.start.proyecto_i1.Bluetooth.DispositivosBT;
import com.example.start.proyecto_i1.Controladores.EjercicioControlador;
import com.example.start.proyecto_i1.Controladores.EtapaControlador;
import com.example.start.proyecto_i1.Controladores.RutinaControlador;
import com.example.start.proyecto_i1.Controladores.UsuarioControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.EjerciciosModelo;
import com.example.start.proyecto_i1.modelos.EtapaModelo;
import com.example.start.proyecto_i1.modelos.RutinaModelo;
import com.example.start.proyecto_i1.modelos.UsuarioModelo;

import java.util.ArrayList;

public class rutina extends AppCompatActivity implements View.OnClickListener {


    Button btnARutAudio;
    Button btnARutVisual;
    ImageButton btnRutinaRegresar;
    ImageButton btnPlayRutina;
    ImageButton btnPauseRutina;
    ImageButton btnStopRutina;
    ImageView imgEjercicio;
    TextView txtEtapaMinutos;
    TextView txtEtapaSegundos;
    TextView txtEtapa;
    TextView txtEjercicio;
    TextView txtRepeticiones;

    CountDownTimer temporizador;
    CountDownTimer temporizadorDescanso;

    int numeroEtapa = 0;
    boolean pausa = false;
    boolean isEtapa = true;
    String envioRutina="4";


    DBHelper admin = new DBHelper(this, "GRECS", null, 1);

    RutinaControlador rutinaControlador = new RutinaControlador(admin);
    EtapaControlador etapaControlador = new EtapaControlador(admin);
    EjercicioControlador ejercicioControlador = new EjercicioControlador(admin);
    UsuarioControlador usuarioControlador = new UsuarioControlador(admin);

    UsuarioModelo usuario = new UsuarioModelo();
    RutinaModelo rutina = new RutinaModelo();
    EtapaModelo etapa = new EtapaModelo();
    EjerciciosModelo ejercicio = new EjerciciosModelo();
    ArrayList<EtapaModelo> etapas = new ArrayList<>();




    Bluetooth conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutina);


        conexion = new Bluetooth();
        conexion.entradabt();
        conexion.address = DispositivosBT.address;
        conexion.inicializar();



        btnARutAudio = findViewById(R.id.btnRutAudio);
        btnARutVisual = findViewById(R.id.btnRutVisual);
        btnRutinaRegresar = findViewById(R.id.btnRutinaRegresar);
        btnPlayRutina = findViewById(R.id.btnPlayRutina);
        btnStopRutina = findViewById(R.id.btnStopRutina);
        btnPauseRutina = findViewById(R.id.btnPauseRutina);
        txtEtapaMinutos = findViewById(R.id.txtEtapaMinutos);
        txtEtapaSegundos = findViewById(R.id.txtEtapaSegundos);
        txtEtapa = findViewById(R.id.txtEtapa);
        txtEjercicio = findViewById(R.id.txtEjercicio);
        txtRepeticiones = findViewById(R.id.txtRepeticiones);
        imgEjercicio = findViewById(R.id.imgEjercicio);

        Intent obtenerId = getIntent();
        rutina.setIdRutina(obtenerId.getIntExtra("id", 0));
        System.out.println(rutina.getIdRutina());
        if (rutina.getIdRutina()>0){
            rutina = rutinaControlador.getRutinaById(rutina.getIdRutina(), seleccionar_usuario.idUser);
            etapas = etapaControlador.getAllEtapas(rutina.getIdRutina());
            int m = (Integer)etapas.get(0).getTiempoEtapa()/60;
            int s = (Integer) (etapas.get(0).getTiempoEtapa() - m*60);
            txtEtapaMinutos.setText(Integer.toString(m));
            txtEtapaSegundos.setText(Integer.toString(s));
            System.out.println(etapas.size());
        }
        else{}

        btnARutAudio.setOnClickListener(this);
        btnARutVisual.setOnClickListener(this);
        btnPlayRutina.setOnClickListener(this);
        btnPauseRutina.setOnClickListener(this);
        btnStopRutina.setOnClickListener(this);
        btnRutinaRegresar.setOnClickListener(this);





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
    public void onClick(View v) {

        int btn_id = v.getId();
        Intent intent = new Intent(this, lista_rutinas.class);

        switch (btn_id) {
            case R.id.btnRutinaRegresar:
                intent = new Intent(this, opciones.class);
                startActivity(intent);
                break;


            case R.id.btnPlayRutina:

                btnPlayRutina.setEnabled(false);
                btnPauseRutina.setEnabled(true);
                btnStopRutina.setEnabled(true);
                txtEtapaMinutos.setEnabled(false);
                txtEtapaSegundos.setEnabled(false);

                envioRutina += "1";
                envioRutina += usuarioControlador.getConfiguracionById(seleccionar_usuario.idUser);
                envioRutina+="#";

                for (EtapaModelo etapaX : etapas) {


                    envioRutina+=etapaX.getEstimulo();
                    envioRutina+=String.valueOf(etapaX.getTiempoEtapa());
                    envioRutina+="-";
                    envioRutina+=String.valueOf(etapaX.getTiempoDescanso());
                    envioRutina+="#";
                }
                controlTimer();

                conexion.MyConexionBT.write(envioRutina);

                pausa = false;

                break;

            case R.id.btnPauseRutina:
                temporizador.cancel();
                btnPlayRutina.setEnabled(true);
                btnPauseRutina.setEnabled(false);
                btnStopRutina.setEnabled(true);
                pausa = true;
                //conexion.MyConexionBT.write("P");


                break;

            case R.id.btnStopRutina:
                temporizador.cancel();
                txtEtapaMinutos.setText("00");
                txtEtapaSegundos.setText("00");
                txtEtapaMinutos.setEnabled(true);
                txtEtapaSegundos.setEnabled(true);
                btnPlayRutina.setEnabled(true);
                btnPauseRutina.setEnabled(false);
                btnStopRutina.setEnabled(false);


                conexion.MyConexionBT.write("T"); //Si es un usuario guarda lo que regrese el
                while (conexion.entrada_datos == "") {
                }


                break;

        }

    }

    private void rutinaTerminada() {
        btnPauseRutina.setEnabled(false);
        btnStopRutina.setEnabled(false);
        btnPlayRutina.setEnabled(true);

        numeroEtapa = 0;
        AlertDialog.Builder alertTermino = new AlertDialog.Builder(this);
        alertTermino.setTitle("GRECS");
        alertTermino.setMessage("¡EXCELENTE! \n HAS COMPLETADO LA RUTINA");
        alertTermino.setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(rutina.this, lista_rutinas.class);
                startActivity(intent);
            }
        });
        alertTermino.show();

    }

    private void controlTimer(){



        System.out.println(isEtapa);
        System.out.println(etapas.get(numeroEtapa).getIdEjercicio());
        String ejercicio = ejercicioControlador.getEjercicio(etapas.get(numeroEtapa).getIdEjercicio());


        if(isEtapa) {
            txtEjercicio.setText(ejercicio);
            txtEtapa.setText("Etapa "+ Integer.toString(etapas.get(numeroEtapa).getNumeroEtapa()));
            txtRepeticiones.setText("x"+Integer.toString(etapas.get(numeroEtapa).getRepeticiones()));
        }else {
            txtEtapa.setText("Tiempo de Descanso");
            txtEjercicio.setText("Descansa, toma aire y prepárate");
        }


        int minutos = Integer.parseInt(txtEtapaMinutos.getText().toString()) * 60;
        int tiempo = minutos + (Integer.parseInt(txtEtapaSegundos.getText().toString()));
        tiempo *= 1000;

        if(!pausa) {
            int t = 0;
            if(isEtapa){
                t = etapas.get(numeroEtapa).getTiempoEtapa();
            } else{
                t = etapas.get(numeroEtapa).getTiempoDescanso();
            }
            int m = (Integer)t/60;
            int s = t - (m*60);
            txtEtapaMinutos.setText(Integer.toString(m));
            txtEtapaSegundos.setText(Integer.toString(s));
            //conexion.MyConexionBT.write(m_predeterminado.juntar());
        }else{

            //conexion.MyConexionBT.write("I");
            pausa = false;
        }

        temporizador = new CountDownTimer(tiempo, 1000) {



            public void onTick(long millisUntilFinished) {
                int min;

                txtEtapaMinutos.setText("" + (int) (((millisUntilFinished / 1000) / 60)));
                min = (int) (millisUntilFinished / 1000) / 60;
                txtEtapaSegundos.setText("" + (int) ((millisUntilFinished / 1000) - (min * 60)));


            }

            public void onFinish() {
                int tiempo;
                isEtapa =! isEtapa;
                txtEtapaMinutos.setText("00");
                txtEtapaSegundos.setText("00");
                btnRutinaRegresar.setEnabled(true);


                if (numeroEtapa+1 == etapas.size() && isEtapa) {
//                    conexion.MyConexionBT.write("T");
                    //while (conexion.entrada_datos != "") {
                        //conexion.MyConexionBT.write("T");
                    //}
                    rutinaTerminada();

                } else {
                    if(isEtapa) {
                        numeroEtapa++;
                        tiempo = etapas.get(numeroEtapa).getTiempoEtapa();
                    }
                    else{
                        tiempo = etapas.get(numeroEtapa).getTiempoDescanso();
                    }
                    int minutos = (Integer) tiempo / 60;
                    int segundos = tiempo - (minutos * 60);
                    txtEtapaMinutos.setText(Integer.toString(minutos));
                    txtEtapaSegundos.setText(Integer.toString(segundos));


                    controlTimer();
                }


            }
             //Toast para prueba

        }.start();
    }
}
