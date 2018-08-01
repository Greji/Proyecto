package com.example.start.proyecto_i1.Bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;

import com.example.start.proyecto_i1.registro_dia;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.CharacterCodingException;
import java.util.UUID;


public class Bluetooth implements Serializable{
    //1)

    //-------------------------------------------
    Handler bluetoothIn;
    final int handlerState = 0;
    public BluetoothAdapter btAdapter = null;
    public BluetoothSocket btSocket = null;
    public StringBuilder DataStringIN = new StringBuilder();
    public ConnectedThread MyConexionBT;
    // Identificador unico de servicio - SPP UUID
    public static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // String para la direccion MAC
    public static String address = null;
    public String hola="";
    public char[] request;


    public String entrada_datos = "*";
    //-------------------------------------------

    public void entradabt(){

        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {

                if (msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    DataStringIN.append(readMessage);
                    System.out.println(DataStringIN);

                    int endOfLineIndex = DataStringIN.indexOf("#");

                    if (endOfLineIndex > 0) {
                        String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        entrada_datos += dataInPrint;
                        DataStringIN.delete(0, DataStringIN.length());

                        //IdBufferIn.setText("Dato: " + dataInPrint);//<-<- PARTE A MODIFICAR >->->



                    }
                }
            }
        };

    }


    public void inicializar() {
        btAdapter = BluetoothAdapter.getDefaultAdapter(); // get Bluetooth adapter
        VerificarEstadoBT();
    }


    public void desconectar() {
        if (btSocket != null) {
            try {

                btSocket.close();
            } catch (IOException e) {
                //Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }




    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException
    {
        //crea un conexion de salida segura para el dispositivo
        //usando el servicio UUID
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }

   public void crearConexion(){


        /*
        //Consigue la direccion MAC desde DeviceListActivity via intent
        Intent intent = getIntent();
        //Consigue la direccion MAC desde DeviceListActivity via EXTRA*/

        //address = intent.getStringExtra(DispositivosBT.EXTRA_DEVICE_ADDRESS);//<-<- PARTE A MODIFICAR >->->
        //Setea la direccion MAC
        BluetoothDevice device = btAdapter.getRemoteDevice(DispositivosBT.address);

        try
        {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            //Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
        }
        // Establece la conexión con el socket Bluetooth.
        try
        {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {}
        }
        MyConexionBT = new ConnectedThread(btSocket);
        MyConexionBT.execute();

    }


    public void sale()
    {
        try
        { // Cuando se sale de la aplicación esta parte permite
            // que no se deje abierto el socket
            btSocket.close();
        } catch (IOException e2) {}
    }

    //Comprueba que el dispositivo Bluetooth Bluetooth está disponible y solicita que se active si está desactivado
    private void VerificarEstadoBT() {

        if(btAdapter==null) {
            //Toast.makeText(getBaseContext(), "El dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                //startActivityForResult();
            }
        }
    }

    //Crea la clase que permite crear el evento de conexion
    public class ConnectedThread extends AsyncTask<Void, Boolean, char[]>
    {
        public final InputStream mmInStream;
        public final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket)
        {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try
            {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }



        //Envio de trama
        public void write(String input)
        {
            try {
                mmOutStream.write(input.getBytes());
                //Toast.makeText(getBaseContext(), "Hola", Toast.LENGTH_LONG).show();
            }
            catch (IOException e)
            {
                //si no es posible enviar datos se cierra la conexión
                //Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                //finish();
            }
        }

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected char[] doInBackground(Void... params) {
            byte[] buffer = new byte[1024];
            int bytes;
            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    hola = readMessage;
                    request = hola.toCharArray();
                    System.out.println(readMessage);
                //request = hola.toCharArray();

                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                    System.out.println("Corriendo Hilo");

                }
                catch (IOException e) {
                    System.out.println(e.toString());
                    break;
                }
            }
            return request;
        }

        @Override
        protected void onPostExecute(char[] datos) {
            registro_dia.datos_muñeco = datos;


        }
    }
}