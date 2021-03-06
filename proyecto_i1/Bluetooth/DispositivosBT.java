
package com.example.start.proyecto_i1.Bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.start.proyecto_i1.R;
import com.example.start.proyecto_i1.opciones;

import java.util.Set;

public class DispositivosBT extends AppCompatActivity {

    //Depuración de LOGCAT
    private static final String TAG = "DispositivosBT";
    public static String address;

    //Declaración de ListView
    ListView IdLista;
    //String que se enviara a la actividad principal, mainactivity

    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dispositivos_bt);
    }

    @Override
    public void onResume(){

        super.onResume();
        VerificarEstadoBT();

        //Inicializa la array que contendra la lista de los dispositivos bluetooth vinculados
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.nombre_dispositivos);
        // Presenta los dispositivos vinculados en el ListView
        IdLista = (ListView) findViewById(R.id.IdLista);
        IdLista.setAdapter(mPairedDevicesArrayAdapter);
        IdLista.setOnItemClickListener(mDeviceClickListener);
        //Obtiene el adapatador local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        //Obtiene un conjunto de dispositivos actualmente emparejados y agrega a 'pairedDevices'
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        //Adiciona un dispositivo previo emparejado al array

        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices){
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }

    }

    //Configura un (on-click) para la lista
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView av, View v, int arg2, long arg3) {
            //Obtener la dirección MAC del dispositivo, que son los últimos 17 caracteres en la MAC
            String info = ((TextView) v).getText().toString();
            address = info.substring(info.length() - 17);

            //Realiza un intent para iniciar la siguiente actividad
            //mientras toma un EXTRA_DEVICE_ADDRESS que es la dirección MAC
            Intent i = new Intent(DispositivosBT.this, opciones.class);
            i.putExtra(EXTRA_DEVICE_ADDRESS, address);
            startActivity(i);
        }

    };

    private void VerificarEstadoBT(){
        //Comprueva que el dispositivo tiene Bluetooth y que está encendido
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter==null){
            Toast.makeText(getBaseContext(), "El dispositivo no soporta Bluetooth", Toast.LENGTH_SHORT);

        } else {
            if (mBtAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth Activado...");

            } else{
                //Solicita al usuario que active Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }
}