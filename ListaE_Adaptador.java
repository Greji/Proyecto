package com.example.start.proyecto_i1.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.start.proyecto_i1.R;
import com.example.start.proyecto_i1.modelos.EtapaModelo;

import java.util.ArrayList;

/**
 * Created by start on 04/06/2018.
 */

public class ListaE_Adaptador extends BaseAdapter{
    private Context context;
    private ArrayList<EtapaModelo> r;

    public ListaE_Adaptador(Context context, ArrayList<EtapaModelo> r) {
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
            rowView = inflater.inflate(R.layout.fila_metapa, parent, false);


        }

        // Set data into the view.
        TextView txtMostrarNumeroEtapa= (TextView) rowView.findViewById(R.id.txtMostrarNumeroEtapa);
        TextView txtMostrarTiempoEtapa= (TextView) rowView.findViewById(R.id.txtMostrarTiempoEtapa);
        TextView txtMostrarTiempoDescanso= (TextView) rowView.findViewById(R.id.txtMostrarTiempoDescanso);
        TextView txtMostrarEjercicio= (TextView) rowView.findViewById(R.id.txtMostrarEjercicio);
        TextView txtMostrarRepeticiones = (TextView) rowView.findViewById(R.id.txtMostrarRepeticiones);
        TextView txtMostrarEstimulo= (TextView) rowView.findViewById(R.id.txtMostrarEstimulo);



        EtapaModelo rutina = this.r.get(position);
        txtMostrarNumeroEtapa.setText("Numero Etapa "+String.valueOf(rutina.getNumeroEtapa()));
        txtMostrarTiempoEtapa.setText("Tiempo Etapa "+String.valueOf(rutina.getTiempoEtapa()));
        txtMostrarTiempoDescanso.setText("Tiempo Descanso"+String.valueOf(rutina.getTiempoDescanso()));
        txtMostrarEjercicio.setText("Id Ejercicio"+String.valueOf(rutina.getIdEjercicio()));
        txtMostrarRepeticiones.setText("Repeticiones" + String.valueOf(rutina.getRepeticiones()));
        //txtMostrarEstimulo.setText(rutina.getEstimulo());
        return rowView;
    }
}
