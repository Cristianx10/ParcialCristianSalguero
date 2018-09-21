package com.example.ecosistemas.parcial_cristiansalguero;


import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Cliente extends Thread {


    static int puerto = 5000;
    static String ip = "10.0.2.2";

    Socket socket;


Receptor receptor;
    MainActivity activity;


    public Cliente(MainActivity activity) {
        this.activity = activity;

    }

    @Override
    public void run() {


        try {
            System.out.println("Peticion enviada");
            socket = new Socket(ip, puerto);
            System.out.println("Se envio");

            receptor = new Receptor(socket, activity);
            receptor.setObserver(activity);


        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    public void enviar(final String usuario, final String clave) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {



                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                    Usuario ux = new Usuario();
                    ux.nombre = usuario;
                    ux.clave = clave;

                    out.writeObject(ux);
                    out.flush();
                    out.reset();


                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }).start();


    }

}
