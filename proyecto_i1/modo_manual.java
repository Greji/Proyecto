package com.example.start.proyecto_i1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.start.proyecto_i1.Bluetooth.Bluetooth;
import com.example.start.proyecto_i1.Bluetooth.DispositivosBT;
import com.example.start.proyecto_i1.Controladores.UsuarioControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;

public class modo_manual extends AppCompatActivity implements View.OnClickListener{

    ImageButton btnMmRegresar;
    Button btnMmCabezaIz;
    Button btnMmCabezaDer;
    Button btnMmCara;
    Button btnMmPecho;
    Button btnMmEstomago;
    Button btnMmTorsoIz;
    Button btnMmTorsoDer;
    ImageButton btnMmPlay;
    ImageButton btnMmPause;
    ImageButton btnMmStop;
    Button btnMmAudio;
    Button btnMmVisual;
    EditText editMmMinutos;
    EditText editMmSegundos;


    protected CountDownTimer temporizador;

    Bluetooth conexion;
    estandar_datos modoManual = new estandar_datos();
    boolean pausado=false;


    DBHelper admin = new DBHelper(this, "GRECS", null, 1);
    UsuarioControlador usuarioControlador = new UsuarioControlador(admin);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modo_manual);

        modoManual.modo="2";

        conexion = new Bluetooth();
        conexion.entradabt();
        conexion.address = DispositivosBT.address;
        conexion.inicializar();


        Toast toast2 = Toast.makeText(getApplicationContext(), conexion.address, Toast.LENGTH_SHORT);

        btnMmRegresar = (ImageButton)findViewById(R.id.btnMmRegresar);
        btnMmCabezaIz = (Button)findViewById(R.id.btnMmCabezaIz);
        btnMmCabezaDer = (Button)findViewById(R.id.btnMmCabezaDer);
        btnMmCara = (Button)findViewById(R.id.btnMmCara);
        btnMmPecho = (Button)findViewById(R.id.btnMmPecho);
        btnMmEstomago = (Button)findViewById(R.id.btnMmEstomago);
        btnMmTorsoIz = (Button)findViewById(R.id.btnMmTorsoIz);
        btnMmTorsoDer = (Button)findViewById(R.id.btnMmTorsoDer);
        btnMmPlay = (ImageButton)findViewById(R.id.btnMmPlay);
        btnMmPause = (ImageButton)findViewById(R.id.btnMmPause);
        btnMmStop = (ImageButton)findViewById(R.id.btnMmStop);
        btnMmAudio = (Button)findViewById(R.id.btnMmAudio);
        btnMmVisual = (Button)findViewById(R.id.btnMmVisual);
        editMmMinutos = (EditText)findViewById(R.id.editMmMinutos);
        editMmSegundos = (EditText)findViewById(R.id.editMmSegundos);




        btnMmRegresar.setOnClickListener(this);
        btnMmCabezaIz.setOnClickListener(this);
        btnMmCabezaDer.setOnClickListener(this);
        btnMmCara.setOnClickListener(this);
        btnMmCara.setOnClickListener(this);
        btnMmPecho.setOnClickListener(this);
        btnMmEstomago.setOnClickListener(this);
        btnMmTorsoIz.setOnClickListener(this);
        btnMmTorsoDer.setOnClickListener(this);
        btnMmPlay.setOnClickListener(this);
        btnMmPause.setOnClickListener(this);
        btnMmStop.setOnClickListener(this);
        btnMmVisual.setOnClickListener(this);
        btnMmAudio.setOnClickListener(this);

        btnMmCabezaIz.setEnabled(false);
        btnMmCabezaDer.setEnabled(false);
        btnMmCara.setEnabled(false);
        btnMmPecho.setEnabled(false);
        btnMmEstomago.setEnabled(false);
        btnMmTorsoIz.setEnabled(false);
        btnMmTorsoDer.setEnabled(false);

        editMmMinutos.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                if(editMmSegundos.getText().toString().isEmpty()){
                    editMmSegundos.setText("00");
                }

                if(editMmMinutos.getText().toString().isEmpty()){
                    editMmMinutos.setText("00");
                }

                int minutes = Integer.parseInt(editMmMinutos.getText().toString());
                if( minutes>30 ){
                    editMmMinutos.setText("30");
                }

                int seconds = Integer.parseInt(editMmMinutos.getText().toString());
                if( seconds>59 ){
                    editMmMinutos.setText("59");
                }

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}



        });
        editMmSegundos.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                int seconds = Integer.parseInt(editMmSegundos.getText().toString());
                if( seconds>59 ){
                    editMmSegundos.setText("59");
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
        Toast toast1;


        switch (id_btn){
            case R.id.btnMmRegresar:
                intent = new Intent(this, modos.class);
                startActivity(intent);
                break;

            case R.id.btnMmVisual:
                modoManual.tipo_estimulo = "1";
                break;

            case R.id.btnMmAudio:
                modoManual.tipo_estimulo = "0";
                break;

            case R.id.btnMmCara:
                conexion.MyConexionBT.write("1");
                break;

            case R.id.btnMmCabezaIz:
                conexion.MyConexionBT.write("2");

                break;

            case R.id.btnMmCabezaDer:
                conexion.MyConexionBT.write("3");
                break;

            case R.id.btnMmPecho:
                conexion.MyConexionBT.write("4");
                break;



            case R.id.btnMmTorsoIz:
                conexion.MyConexionBT.write("5");
                break;

            case R.id.btnMmTorsoDer:
                conexion.MyConexionBT.write("6");
                break;

            case R.id.btnMmEstomago:
                conexion.MyConexionBT.write("7");
                break;



            case R.id.btnMmPlay:
                btnMmPlay.setEnabled(false);
                btnMmPause.setEnabled(true);
                btnMmStop.setEnabled(true);
                editMmMinutos.setEnabled(false);
                editMmSegundos.setEnabled(false);
                btnMmCabezaIz.setEnabled(true);
                btnMmCabezaDer.setEnabled(true);
                btnMmCara.setEnabled(true);
                btnMmPecho.setEnabled(true);
                btnMmEstomago.setEnabled(true);
                btnMmTorsoIz.setEnabled(true);
                btnMmTorsoDer.setEnabled(true);

                intent = new Intent(this, registro_dia.class);




                if(pausado==false) {
                    conexion.MyConexionBT.write(String.valueOf(modoManual.modo)+
                            String.valueOf(modoManual.tipo_estimulo) +
                            usuarioControlador.getConfiguracionById(seleccionar_usuario.idUser) +
                                    modoManual.estimulo + modoManual.tiempoEstimulo
                            );
                }
                else if(pausado==true){
                    conexion.MyConexionBT.write("I");
                    pausado = false;
                }

                int minutos= Integer.parseInt(editMmMinutos.getText().toString())*60;
                int tiempo = minutos + (Integer.parseInt(editMmSegundos.getText().toString()));
                tiempo*=1000;
                temporizador = new CountDownTimer( tiempo, 1000) {

                    public void onTick(long millisUntilFinished) {
                        int min;

                        editMmMinutos.setText(""+ (int)(((millisUntilFinished / 1000)/60)));
                        min = (int)(millisUntilFinished/1000)/60;
                        editMmSegundos.setText("" + (int)((millisUntilFinished/1000) - (min*60)));


                    }

                    public void onFinish() {

                        btnMmPlay.setEnabled(true);
                        btnMmPause.setEnabled(false);
                        btnMmStop.setEnabled(false);
                        editMmMinutos.setText("00");
                        editMmSegundos.setText("00");
                        editMmMinutos.setEnabled(true);
                        editMmSegundos.setEnabled(true);
                        btnMmRegresar.setEnabled(true);

                        conexion.MyConexionBT.write("T");
                        AlertDialog.Builder terminoModo = new AlertDialog.Builder(modo_manual.this);
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

            case R.id.btnMmPause:
                temporizador.cancel();
                btnMmStop.setEnabled(true);
                btnMmPlay.setEnabled(true);
                btnMmPause.setEnabled(false);
                btnMmCabezaIz.setEnabled(false);
                btnMmCabezaDer.setEnabled(false);
                btnMmCara.setEnabled(false);
                btnMmPecho.setEnabled(false);
                btnMmEstomago.setEnabled(false);
                btnMmTorsoIz.setEnabled(false);
                btnMmTorsoDer.setEnabled(false);


                conexion.MyConexionBT.write("P");

                pausado = true;


                break;

            case R.id.btnMmStop:
                temporizador.cancel();
                btnMmCabezaIz.setEnabled(false);
                btnMmCabezaDer.setEnabled(false);
                btnMmCara.setEnabled(false);
                btnMmPecho.setEnabled(false);
                btnMmEstomago.setEnabled(false);
                btnMmTorsoIz.setEnabled(false);
                btnMmTorsoDer.setEnabled(false);
                editMmMinutos.setEnabled(true);
                editMmSegundos.setEnabled(true);
                btnMmStop.setEnabled(false);
                btnMmPlay.setEnabled(true);
                btnMmPause.setEnabled(false);
                conexion.MyConexionBT.write("T");

                break;



        }


    }

    void esto_termino(){
        Intent intent1 = new Intent(this, registro_dia.class);
        intent1.putExtra("datos", conexion.entrada_datos);
        startActivity(intent1);


    }



}


