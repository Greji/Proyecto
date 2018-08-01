package com.example.start.proyecto_i1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.start.proyecto_i1.Controladores.EjercicioControlador;
import com.example.start.proyecto_i1.Controladores.RegistroControlador;
import com.example.start.proyecto_i1.Controladores.RutinaControlador;
import com.example.start.proyecto_i1.Controladores.UsuarioControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.EjerciciosModelo;
import com.example.start.proyecto_i1.modelos.RegistroModelo;
import com.example.start.proyecto_i1.modelos.RutinaModelo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    protected Button btnSeleccionar;
    protected Button btnCrear;
    protected Button btnInvitado;



    protected DBHelper admin=new DBHelper(this,"GRECS",null,1);
    protected UsuarioControlador uController = new UsuarioControlador(admin);
    protected EjercicioControlador ejController = new EjercicioControlador(admin);
    protected RegistroControlador regController = new RegistroControlador(admin);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //deleteDatabase("GRECS");

        //ejController.setEjercicio(new EjerciciosModelo(1, "Abdominales", "abdominal.jpg"));
        //ejController.setEjercicio(new EjerciciosModelo(2, "Lagartijas", "lagartija.jpg"));
        //ejController.setEjercicio(new EjerciciosModelo(3, "Sentadillas", "sentadilla.jpg"));
       // ejController.setEjercicio(new EjerciciosModelo(4, "Burpees", "burpee.jpg"));

        RegistroModelo a = new RegistroModelo(1,
                18,
                6,
                8,
                .5,
                6,
                8,
                10,
                12,
                1);

        RegistroModelo b = new RegistroModelo(2,
                19,
                6,
                10,
                .5,
                5,
                10.5,
                10,
                12,
                1);

        RegistroModelo c = new RegistroModelo(3,
                20,
                6,
                9,
                .4,
                4,
                10,
                7,
                7,
                1);

        //regController.deleteRegistros();

        /*
        regController.setRegistro(a);
        regController.setRegistro(b);
        regController.setRegistro(c);
        */



        btnSeleccionar = (Button)findViewById(R.id.btnSeleccionar);
        btnCrear = (Button)findViewById(R.id.btnCrear);
        btnInvitado = (Button)findViewById(R.id.btnInvitado);

        btnSeleccionar.setOnClickListener(this);
        btnCrear.setOnClickListener(this);
        btnInvitado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        
        int idBoton = v.getId();
        Intent intent ;

        switch (idBoton){
            case R.id.btnSeleccionar:
                intent = new Intent(this, seleccionar_usuario.class);
                startActivity(intent);
                break;

            case R.id.btnCrear:

                if(uController.getNumeroUsuarios()==5) {
                    AlertDialog.Builder noMasUsuarios = new AlertDialog.Builder(MainActivity.this);
                    noMasUsuarios.setTitle("ATENCIÓN");
                    noMasUsuarios.setMessage("No puedes agregar más usuarios");
                    noMasUsuarios.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    noMasUsuarios.show();
                }else{
                    intent = new Intent(this, agregar_usuario.class);
                    startActivity(intent);
                }


                break;

            case R.id.btnInvitado:
                SharedPreferences datosUsuario = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = datosUsuario.edit();
                editor.putInt("idUsuario", -1);
                editor.putString("nombre", "Invitado");
                editor.putString("configuracion", "00DO-RE-MI-FA-SOL-LA-SI#");
                editor.commit();
                intent = new Intent(this, opciones.class);
                startActivity(intent);
                break;


        }


    }

}


