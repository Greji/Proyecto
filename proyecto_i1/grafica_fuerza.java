package com.example.start.proyecto_i1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.start.proyecto_i1.Controladores.RegistroControlador;
import com.example.start.proyecto_i1.GRECSBD.DBHelper;
import com.example.start.proyecto_i1.modelos.RegistroModelo;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;

public class grafica_fuerza extends AppCompatActivity {

    DBHelper admin=new DBHelper(this,"GRECS",null,1);
    RegistroControlador regController = new RegistroControlador(admin);

    ArrayList<RegistroModelo> listaRegistro = new ArrayList<>();
    DataPoint[] puntosFuerza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafica_fuerza);

        listaRegistro= regController.getAllRegistros();
        puntosFuerza = new DataPoint[listaRegistro.size()];



        for(int i=0; i<listaRegistro.size(); i++){
            int x = listaRegistro.get(i).getDia();
            double y = listaRegistro.get(i).getFuerza();
            puntosFuerza[i] = new DataPoint(x, y);
        }

        GraphView graph = (GraphView) findViewById(R.id.graphForce);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(puntosFuerza);

        graph.addSeries(series);
    }
}
