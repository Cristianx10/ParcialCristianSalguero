package com.example.ecosistemas.parcial_cristiansalguero;


import android.accessibilityservice.AccessibilityService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Receptor extends Thread{
    Socket socket;

    //Paso 2
    OnMessage observer;

    static int puerto = 5000;
    static String ip = "10.0.2.2";
    MainActivity activity;

    public Receptor(Socket socket, MainActivity activity) {
        this.socket = socket;
        this.activity = activity;
    }

    @Override
    public void run() {

        try {

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());


            while (true) {

                Usuario ux = null;
                Object obj  = in.readObject();

                if(obj instanceof Boolean){
                    activity.permiso = true;
                }


                //Paso 4: Solo funciona cuando no es nulo
                if(ux != null) {
                    observer.onReceived(ux);
                }

            }
        } catch (IOException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Observable
    //Paso 1
    public interface OnMessage{
        public void onReceived(Usuario msg);
    }

    //Paso 3
    public void setObserver(OnMessage observer){
        this.observer = observer;
    }

}
