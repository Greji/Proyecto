package com.example.start.proyecto_i1.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.start.proyecto_i1.R;
import com.example.start.proyecto_i1.modelos.UsuarioModelo;

/**
 * Created by start on 06/05/2018.
 */

public class Usuario_Adaptador  extends BaseAdapter{
    private Context context;
    private ArrayList<UsuarioModelo> r;

    public Usuario_Adaptador(Context context, ArrayList<UsuarioModelo> r) {
        this.context = context;
        this.r = r;
    }

    @Override
    public int getCount() {
        return this.r.size();
    }

    @Override
    public Object getItem(int position) {
        return this.r.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.fila_usuarios, parent, false);
        }

        // Set data into the view.
        TextView nombre= (TextView) rowView.findViewById(R.id.txtNombreUsuario);

        UsuarioModelo usuario = this.r.get(position);
        System.out.println(position);
        System.out.println(usuario.getNombre());
        nombre.setText(usuario.getNombre());


        return rowView;
    }
}
