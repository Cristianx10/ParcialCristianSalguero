package com.example.ecosistemas.parcial_cristiansalguero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Socket;

public class MainActivity extends AppCompatActivity implements Receptor.OnMessage{

    Boolean permiso = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Cliente cliente = new Cliente(MainActivity.this);
        cliente.start();

        final EditText et_nombre = findViewById(R.id.et_nombre);
        final EditText et_clave = findViewById(R.id.et_password);

        Button btn_registrar = findViewById(R.id.btn_registrar);
        Button btn_entrar = findViewById(R.id.btn_entrar);




        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = et_nombre.getText().toString();
                String clave = et_nombre.getText().toString();

                cliente.enviar(name, clave);


            }
        });

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(permiso) {
                    Intent i = new Intent(getApplicationContext(), Entrada.class);
                    startActivity(i);
                }

            }
        });


    }

    @Override
    public void onReceived(final Usuario msg) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {




            }
        });

    }
}
