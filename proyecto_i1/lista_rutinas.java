package com.example.start.proyecto_i1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.start.proyecto_i1.Adaptadores.ListaR_Adaptador;
import com.example.start.proyecto_i1.Controladores.RutinaControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.RutinaModelo;

public class lista_rutinas extends AppCompatActivity  implements View.OnClickListener{
    ImageButton btn_lrregresar;
    ImageButton btn_agrutina;
    ListView listarutinas;

    int idUsuario;
    boolean Flag = false;


    RutinaModelo rutina1 = new RutinaModelo(
            -1,
            "Simple",
            120,
            0,
            "Fácil");
    RutinaModelo rutina2 = new RutinaModelo(
            -2,
            "Vamos empezando",
            180,
            0,
            "Media");
    RutinaModelo rutina3 = new RutinaModelo(
            -3,
            "Un poco de brazo",
            180,
            0,
            "Media");
    RutinaModelo rutina4 = new RutinaModelo(
            -4,
            "Cardio",
            300,
            0,
            "Díficil");
    RutinaModelo rutina5 = new RutinaModelo(
            -5,
            "Resiste si puedes",
            300,
            0,
            "Díficil");

    ArrayList<RutinaModelo> listadeRutinas = new ArrayList<>();

    DBHelper admin=new DBHelper(this,"GRECS",null,1);
    RutinaControlador ruController = new RutinaControlador(admin);






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_rutinas);


        btn_lrregresar=findViewById(R.id.btn_lrregresar);
        btn_agrutina=findViewById(R.id.btn_agrutina);
        listarutinas = findViewById(R.id.listarutinas);

        if(idUsuario==-1){

            btn_agrutina.setEnabled(false);
            btn_agrutina.setVisibility(View.INVISIBLE);

        }

        btn_lrregresar.setOnClickListener(this);
        btn_agrutina.setOnClickListener(this);
        ArrayList<RutinaModelo> listaConsulta = ruController.getAllRutina(seleccionar_usuario.idUser);

        listadeRutinas.add(rutina1);
        listadeRutinas.add(rutina2);
        listadeRutinas.add(rutina3);
        listadeRutinas.add(rutina4);
        listadeRutinas.add(rutina5);

        for (int x=0; x<listaConsulta.size(); x++){
            listadeRutinas.add(listaConsulta.get(x));
        }

        final ListaR_Adaptador adaptadorRutina = new ListaR_Adaptador(this, listadeRutinas);
        listarutinas.setAdapter(adaptadorRutina);

        listarutinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idRut = listadeRutinas.get(position).getIdRutina();
                Intent i = new Intent(lista_rutinas.this, rutina.class);
                i.putExtra("id", idRut);
                startActivity(i);
            }
        });

        listarutinas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Flag = true;
                AlertDialog.Builder builder = new AlertDialog.Builder(lista_rutinas.this)
                        .setTitle("¡Atención!")
                        .setMessage("¿Seguro que quieres eliminar este usuario?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ruController.deleteRutina((listadeRutinas.get(position)));
                                listadeRutinas.remove(position);
                                adaptadorRutina.notifyDataSetChanged();
                                Flag = false;
                                Intent intent = new Intent(lista_rutinas.this, seleccionar_usuario.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Flag = false;
                                Intent intent = new Intent(lista_rutinas.this, seleccionar_usuario.class);
                                startActivity(intent);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                return false;
            }
        });





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
                intent = new Intent(this, agregar_rutinados.class);
                break;
        }
        startActivity(intent);

    }

}
