package com.example.start.proyecto_i1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.start.proyecto_i1.Adaptadores.Usuario_Adaptador;
import com.example.start.proyecto_i1.Controladores.UsuarioControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;

import java.util.ArrayList;

import com.example.start.proyecto_i1.modelos.UsuarioModelo;


public class seleccionar_usuario extends AppCompatActivity implements View.OnClickListener {


    ImageButton btn_suregresar;
    ListView listaUsuarios;
    DBHelper admin=new DBHelper(this,"GRECS",null,1);
    UsuarioControlador usuarioControlador = new UsuarioControlador(admin);
    Usuario_Adaptador usuarioAdaptador;
    ArrayList<UsuarioModelo>  listadeUsuarios= new ArrayList<>();
    boolean Flag = false;

    public static int idUser;
    public static String userName;
    public static String configuration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccionarusuario);

        btn_suregresar = (ImageButton)findViewById(R.id.btn_suregresar);
        listaUsuarios = findViewById(R.id.listaUsuarios);
        btn_suregresar.setOnClickListener(this);


        listadeUsuarios = usuarioControlador.getAllUsuarios();
        usuarioAdaptador = new Usuario_Adaptador(this, listadeUsuarios);
        listaUsuarios.setAdapter(usuarioAdaptador);



        //listaUsuarios.setOnItemClickListener();

        listaUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idUsuario = usuarioControlador.getIdByNombre(listadeUsuarios.get(position).getNombre());
                String nombre = listadeUsuarios.get(position).getNombre();
                String configuracion = usuarioControlador.getConfiguracionById(idUsuario);


                Intent intent = new Intent(getApplicationContext(), opciones.class);

                idUser = idUsuario;
                userName = nombre;
                configuration = configuracion;


                SharedPreferences datosUsuario = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = datosUsuario.edit();
                editor.putInt("idUsuario", idUsuario);
                editor.putString("nombre", nombre);
                editor.putString("configuracion", configuracion);
                editor.commit();



                if(Flag==false){
                    startActivity(intent);
                }

            }
        });

        listaUsuarios.setOnItemLongClickListener (new AdapterView.OnItemLongClickListener() {
            @SuppressWarnings("rawtypes")
            public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
                Flag = true;
                AlertDialog.Builder builder = new AlertDialog.Builder(seleccionar_usuario.this)
                        .setTitle("¡Atención!")
                        .setMessage("¿Seguro que quieres eliminar este usuario?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                usuarioControlador.deleteUsuario((listadeUsuarios.get(position).getId_usuario()));
                                listadeUsuarios.remove(position);
                                usuarioAdaptador.notifyDataSetChanged();
                                Flag = false;
                                Intent intent = new Intent(seleccionar_usuario.this, seleccionar_usuario.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Flag = false;
                                Intent intent = new Intent(seleccionar_usuario.this, seleccionar_usuario.class);
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
    public void onClick(View view){
        int btn_id = view.getId();


        Intent intent =new Intent(this, agregar_usuario.class);

        switch (btn_id){
            case R.id.btn_suregresar:
                intent = new Intent(this, MainActivity.class);
                //intent.putExtra("idusuario", usuario.getNombre());
                break;


        }
        startActivity(intent);

    }

    public void eliminarUsuario(String nombre){
        int id;
        id = usuarioControlador.getIdByNombre(nombre);
        usuarioControlador.deleteUsuario(id);


    }

}



