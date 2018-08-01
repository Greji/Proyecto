package com.example.start.proyecto_i1.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.start.proyecto_i1.R;
import com.example.start.proyecto_i1.modelos.RutinaModelo;

import org.w3c.dom.Text;

/**
 * Created by start on 23/04/2018.
 */

public class ListaR_Adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<RutinaModelo> r;


    public ListaR_Adaptador(Context context, ArrayList<RutinaModelo> r) {
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
            rowView = inflater.inflate(R.layout.vista_fila1, parent, false);


        }

        // Set data into the view.
        TextView txtNombre= (TextView) rowView.findViewById(R.id.txtNombre);
        TextView txtDificultad= (TextView) rowView.findViewById(R.id.txtDificultad);
        TextView txtDuracion= (TextView) rowView.findViewById(R.id.txtDuracion);
        TextView txtEjercicio= (TextView) rowView.findViewById(R.id.txtEjercicio);



        RutinaModelo rutina = this.r.get(position);
        txtNombre.setText(rutina.getNombre());
        txtDificultad.setText(rutina.getDificultad());
        txtDuracion.setText(rutina.getTiempoTotal()/60 + "minutos");







        return rowView;
    }
}
