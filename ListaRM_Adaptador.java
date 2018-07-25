package com.example.start.proyecto_i1.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.start.proyecto_i1.R;
import com.example.start.proyecto_i1.modelos.RegistroModelo;
import com.example.start.proyecto_i1.modelos.RutinaModelo;

import java.util.ArrayList;

/**
 * Created by start on 06/06/2018.
 */

public class ListaRM_Adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<RegistroModelo> r;


    public ListaRM_Adaptador(Context context, ArrayList<RegistroModelo> r) {
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
            rowView = inflater.inflate(R.layout.fila_registromensual, parent, false);


        }


        TextView txtFecha= (TextView) rowView.findViewById(R.id.txtFecha);
        TextView txtTiempo= (TextView) rowView.findViewById(R.id.txtTiempo);




        RegistroModelo registro = this.r.get(position);
        txtFecha.setText("Fecha: "+ ((String.valueOf(registro.getDia()) + "/" + (String.valueOf(registro.getMes())))));
        txtTiempo.setText("Tiempo" + String.valueOf(registro.getTiempo()));



        return rowView;
    }
}
