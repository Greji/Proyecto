package com.example.start.proyecto_i1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.start.proyecto_i1.Adaptadores.ListaRM_Adaptador;
import com.example.start.proyecto_i1.Controladores.RegistroControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.RegistroModelo;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class grafica_tiempo extends AppCompatActivity {

    DBHelper admin=new DBHelper(this,"GRECS",null,1);
    RegistroControlador regController = new RegistroControlador(admin);

    ArrayList<RegistroModelo> listaRegistro = new ArrayList<>();
    DataPoint[] puntosTiempo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafica_tiempo);

        listaRegistro= regController.getAllRegistros();
        puntosTiempo = new DataPoint[listaRegistro.size()];
        for(int i=0; i<listaRegistro.size(); i++){
            int x = listaRegistro.get(i).getDia();
            double y = listaRegistro.get(i).getTiempo();
            puntosTiempo[i] = new DataPoint(x, y);
        }

        GraphView graph = (GraphView) findViewById(R.id.graphTime);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(puntosTiempo);
        graph.addSeries(series);
    }
}
