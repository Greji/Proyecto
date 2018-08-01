package com.example.start.proyecto_i1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.start.proyecto_i1.Controladores.EjercicioControlador;
import com.example.start.proyecto_i1.Controladores.EtapaControlador;
import com.example.start.proyecto_i1.Controladores.RutinaControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.EtapaModelo;
import com.example.start.proyecto_i1.modelos.RutinaModelo;

public class agregarrutinauno extends AppCompatActivity implements View.OnClickListener{



    protected Button btnAgregarE;
    protected Button btnAgregarR;
    protected EditText editMinEtapa;
    protected EditText editSegEtapa;
    protected EditText editRepeticiones;
    protected EditText editMinDescanso;
    protected EditText editSegDescanso;
    protected ImageButton btnReRuUno;
    protected Spinner spnEjercicio;
    protected Switch swRutIzCab;
    protected Switch swRutDerCab;
    protected Switch swRutCara;
    protected Switch swRutPecho;
    protected Switch swRutIzTor;
    protected Switch swRutDerTor;
    protected Switch swRutEstomago;
    protected TextView txtNumeroEtapa;



    int nEtapa = 1;
    int idUsuario = seleccionar_usuario.idUser;

    private DBHelper admin = new DBHelper(this, "GRECS", null, 1);
    protected EjercicioControlador ejControlador = new EjercicioControlador(admin);
    protected RutinaControlador rControlador = new RutinaControlador(admin);
    protected EtapaControlador etControlador = new EtapaControlador(admin);
    protected RutinaModelo rutina = new RutinaModelo();

    ArrayList<EtapaModelo> listaEtapas= new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarrutinauno);

        inicioViews();

        //idUsuario = datosUsuario.getIntExtra("idUsuario", 0);


        Intent datosUsuario = getIntent();
        rutina = (RutinaModelo) getIntent().getSerializableExtra("rutina");
        System.out.println(rutina.getNombre());
    }



    @Override
    public void onClick(View v) {

        int id_btn = v.getId();
        Intent intent = new Intent(this, modo_manual.class);



        verificarCampos();

        switch (id_btn) {
            case R.id.btnReRuUno:
                intent = new Intent(this, modos.class);
                startActivity(intent);

                break;

            case R.id.btnAgregarE:

                if (Integer.parseInt(editMinEtapa.getText().toString())==0 && Integer.parseInt(editSegEtapa.getText().toString())==0) {
                    AlertDialog.Builder noRepeticiones = new AlertDialog.Builder(agregarrutinauno.this);
                    noRepeticiones.setTitle("ATENCIÓN");
                    noRepeticiones.setMessage("Por favor, asigna un tiempo a la etapa");
                    noRepeticiones.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    noRepeticiones.show();
                }else {
                    agregarEtapa();
                    limpiarValores();
                }

                break;

            case R.id.btnAgregarR:
                if (Integer.parseInt(editMinEtapa.getText().toString())==0 && Integer.parseInt(editSegEtapa.getText().toString())==0) {
                    AlertDialog.Builder noRepeticiones = new AlertDialog.Builder(agregarrutinauno.this);
                    noRepeticiones.setTitle("ATENCIÓN");
                    noRepeticiones.setMessage("Por favor, asigna un tiempo a la etapa");
                    noRepeticiones.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    noRepeticiones.show();
                }else {
                    agregarEtapa();
                    agregarRutina();
                    intent = new Intent(this, RutinaAgregada.class);
                    intent.putExtra("listaEtapas", listaEtapas);
                    intent.putExtra("id", rutina.getIdRutina());
                    startActivity(intent);
                }
                break;

        }


    }

    void agregarEtapa(){

        EtapaModelo etapa = new EtapaModelo();

        etapa.setNumeroEtapa(nEtapa);

        etapa.setTiempoEtapa(
                (Integer.parseInt(editMinEtapa.getText().toString())*60)+
                Integer.parseInt(editSegEtapa.getText().toString())
        );

        System.out.println(etapa.getTiempoEtapa());


        etapa.setTiempoDescanso(
                (Integer.parseInt(editMinDescanso.getText().toString())*60)+
                Integer.parseInt(editSegDescanso.getText().toString()));

        System.out.println(etapa.getTiempoDescanso());

        etapa.setEstimulo(configEstimulos());

        etapa.setIdEjercicio((ejControlador.getIdEjercicioByNombre(spnEjercicio.getSelectedItem().toString())));
        etapa.setRepeticiones(Integer.parseInt(editRepeticiones.getText().toString()));
        listaEtapas.add(etapa);

        System.out.println("El tamaño de la lista es");
        System.out.println(listaEtapas.size());



    }

    void agregarRutina(){
        int tiempo=0;
        int id;
        for (int x=0; x<listaEtapas.size(); x++){
            tiempo += (listaEtapas.get(x).getTiempoEtapa() + listaEtapas.get(x).getTiempoDescanso());
        }
        rutina.setTiempoTotal(tiempo);

        rControlador.setRutina(rutina);


        id = rControlador.getIdByNombre(rutina.getNombre());

        rutina.setIdRutina(id);
        System.out.println(rutina.getIdRutina());
        System.out.println(rutina.getNombre());
        System.out.println(rutina.getTiempoTotal());
        System.out.println(rutina.getDificultad());
        System.out.println(rutina.getIdUsuario());

        for (int x=0; x<listaEtapas.size(); x++){
            listaEtapas.get(x).setIdRutina(id);
        }

        etControlador.setEtapas(listaEtapas);
    }

    private String configEstimulos() {
        String estimulos = "";

        if (swRutCara.isChecked()){ estimulos+="1"; }
        else{ estimulos += "0";}

        if (swRutIzCab.isChecked()){ estimulos+="1"; }
        else{ estimulos += "0";}

        if (swRutDerCab.isChecked()){ estimulos+="1"; }
        else{ estimulos += "0";}

        if (swRutPecho.isChecked()){ estimulos+="1"; }
        else{ estimulos += "0";}

        if (swRutEstomago.isChecked()){ estimulos+="1"; }
        else{ estimulos += "0";}

        if (swRutIzTor.isChecked()){ estimulos+="1"; }
        else{ estimulos += "0";}

        if (swRutDerTor.isChecked()){estimulos+="1"; }
        else{ estimulos += "0";}

        return estimulos;
    }

    private void inicioViews(){
        editMinEtapa = (EditText) findViewById(R.id.editMinEtapa);
        editSegEtapa = (EditText) findViewById(R.id.editSegEtapa);
        editMinDescanso = (EditText) findViewById(R.id.editMinDescanso);
        editSegDescanso = (EditText) findViewById(R.id.editSegDescanso);
        editRepeticiones = (EditText) findViewById(R.id.editRepeticiones);
        btnAgregarE = (Button) findViewById(R.id.btnAgregarE);
        btnAgregarR = (Button) findViewById(R.id.btnAgregarR);
        btnReRuUno = (ImageButton)findViewById(R.id.btnReRuUno);
        spnEjercicio = (Spinner) findViewById(R.id.spnEjercicio);
        swRutIzCab = (Switch) findViewById(R.id.swRutIzCab);
        swRutDerCab = (Switch) findViewById(R.id.swRutDerCab);
        swRutCara = (Switch) findViewById(R.id.swRutCara);
        swRutPecho = (Switch) findViewById(R.id.swRutPecho);
        swRutIzTor = (Switch) findViewById(R.id.swRutIzTor);
        swRutDerTor = (Switch) findViewById(R.id.swRutDerTor);
        swRutEstomago = (Switch) findViewById(R.id.swRutEstomago);
        txtNumeroEtapa = (TextView) findViewById(R.id.txtNumeroEtapa);

        swRutIzCab.setOnClickListener(this);
        swRutDerCab.setOnClickListener(this);
        swRutPecho.setOnClickListener(this);
        swRutIzTor.setOnClickListener(this);
        swRutDerTor.setOnClickListener(this);
        swRutEstomago.setOnClickListener(this);
        btnReRuUno.setOnClickListener(this);
        btnAgregarE.setOnClickListener(this);
        btnAgregarR.setOnClickListener(this);

        swRutCara.setChecked(true);

        System.out.println(ejControlador.getNombreEjercicio().size());

        ArrayAdapter<String> spnAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ejControlador.getNombreEjercicio());
        spnAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEjercicio.setAdapter(spnAdaptador);


    }

    private void limpiarValores(){

        editMinEtapa.setText("00");
        editSegEtapa.setText("00");
        editMinDescanso.setText("00");
        editSegDescanso.setText("00");
        editRepeticiones.setText("00");

        swRutCara.setChecked(true);
        swRutIzCab.setChecked(false);
        swRutDerCab.setChecked(false);
        swRutPecho.setChecked(false);
        swRutIzTor.setChecked(false);
        swRutDerTor.setChecked(false);
        swRutEstomago.setChecked(false);

    }

    private void verificarCampos(){

        if (!swRutIzCab.isChecked() &&
                !swRutDerCab.isChecked() &&
                !swRutCara.isChecked() &&
                !swRutPecho.isChecked() &&
                !swRutEstomago.isChecked() &&
                !swRutIzTor.isChecked() &&
                !swRutDerTor.isChecked()) {

            swRutCara.setChecked(true);
        }

        if (editRepeticiones.getText().toString().isEmpty()) {
            editRepeticiones.setText("1");
        }

        if (editMinEtapa.getText().toString().isEmpty()) {
            editMinEtapa.setText("00");
        }
        if (editSegEtapa.getText().toString().isEmpty()) {
            editSegEtapa.setText("00");
        }
        if (editMinDescanso.getText().toString().isEmpty()) {
            editMinDescanso.setText("00");
        }
        if (editSegDescanso.getText().toString().isEmpty()) {
            editSegDescanso.setText("00");

        }
    }
}



