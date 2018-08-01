package com.example.start.proyecto_i1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.start.proyecto_i1.Bluetooth.Bluetooth;
import com.example.start.proyecto_i1.Controladores.RegistroControlador;
import com.example.start.proyecto_i1.Controladores.UsuarioControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.RegistroModelo;

import java.util.Calendar;
import java.util.Date;

public class registro_dia extends AppCompatActivity implements View.OnClickListener{

    protected ImageButton btn_diaregresar;
    protected TextView txtfuerzamax;
    protected TextView txtfuerzamin;
    protected TextView txtfuerzaprom;
    protected TextView txttiempoprom;
    protected TextView txtgolpes;

    RegistroModelo registroDia = new RegistroModelo();
    DBHelper admin=new DBHelper(this,"GRECS",null,1);
    UsuarioControlador uController = new UsuarioControlador(admin);
    RegistroControlador registroControlador = new RegistroControlador(admin);

    public static char[] datos_muñeco;
    final Calendar calendario = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_dia);




        datos_muñeco = getIntent().getCharArrayExtra("datos");

        if(datos_muñeco==null) {

            convertir();

        }

        if(registroControlador.isSetRegistro(calendario.get(Calendar.DAY_OF_MONTH), calendario.get(Calendar.MONTH))){

            RegistroModelo auxiliar = registroControlador.getRegistro(calendario.get(Calendar.DAY_OF_MONTH), calendario.get(Calendar.MONTH));

            if(registroDia.getTiempoMax()< auxiliar.getTiempoMax()){
                registroDia.setTiempoMax(auxiliar.getTiempoMax());
            }

            if (registroDia.getTiempoMin()> auxiliar.getTiempoMin()){
                registroDia.setTiempoMin(auxiliar.getTiempoMin());
            }

            registroDia.setTiempo((registroDia.getTiempo() + auxiliar.getTiempo())/2);
            registroDia.setFuerza((registroDia.getFuerza() + auxiliar.getFuerza())/2);

            registroDia.setGolpesAcertados(registroDia.getGolpesAcertados()+auxiliar.getGolpesAcertados());
            registroDia.setGolpesTotales(registroDia.getGolpesTotales()+auxiliar.getGolpesTotales());

        }

        registroDia.setIdUsuario(seleccionar_usuario.idUser);

        registroControlador.setRegistro(registroDia);


        btn_diaregresar = findViewById(R.id.btn_diaregresar);
        btn_diaregresar.setOnClickListener(this);
        txtfuerzamax = (TextView)findViewById(R.id.txtfuerzamax);
        txtfuerzamin = (TextView)findViewById(R.id.txtfuerzamin);
        txtfuerzaprom = (TextView)findViewById(R.id.txtfuerzaprom);
        txttiempoprom = (TextView)findViewById(R.id.txttiempoprom);
        txtgolpes = (TextView)findViewById(R.id.txtgolpes);
//        txtfuerzamax.setText(datos_muñeco[1]);




    }

    @Override
    public void onClick(View v){

        int id_btn = v.getId();
        Intent intent =new Intent(this, MainActivity.class);

        switch (id_btn){

            case R.id.btn_diaregresar:
                intent = new Intent(this, seleccionar_usuario.class);
                break;

        }
        startActivity(intent);

    }

    void convertir() {

        registroDia.setDia(calendario.get(Calendar.DAY_OF_MONTH));
        registroDia.setMes(calendario.get(Calendar.MONTH));
        registroDia.setYear(calendario.get(Calendar.YEAR));
        registroDia.setTiempoMax(Character.getNumericValue(datos_muñeco[1]));
        registroDia.setTiempoMin(Character.getNumericValue(datos_muñeco[2]));
        registroDia.setFuerza(Character.getNumericValue(datos_muñeco[3]));
        registroDia.setTiempo(Character.getNumericValue(datos_muñeco[4]));
        registroDia.setGolpesAcertados(Character.getNumericValue(datos_muñeco[5]));
        registroDia.setGolpesTotales(Character.getNumericValue(datos_muñeco[6]));



        txtfuerzamax.setText("Fuerza mayor: " + registroDia.getTiempoMax());
        txtfuerzamin.setText("Fuerza menor: " + registroDia.getTiempoMin());
        txtfuerzaprom.setText("Fuerza promedio: " + registroDia.getFuerza());
        txttiempoprom.setText("Tiempo promedio: " + registroDia.getTiempo());
        txtgolpes.setText("Golpes acertados: " + registroDia.getGolpesAcertados() + "/" + registroDia.getGolpesTotales());

    }
}
