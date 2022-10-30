package com.example.application2;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected  IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enviando mensaje interno********************************
        intentFilter = new IntentFilter("com.journaldev.broadcastreceiver.SOME_ACTION");

        Button enviarmensajeinterno = (Button) findViewById(R.id.botonEnviar);
        EditText mensajeEscrito = findViewById(R.id.escribirMensaje);

        enviarmensajeinterno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.journaldev.broadcastreceiver.SOME_ACTION");
                intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES); //seguridad
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //esta linea evita los duplicados
                intent.putExtra("app2", String.valueOf(mensajeEscrito.getText()));
                sendBroadcast(intent);

                mensajeEscrito.setText("");
            }
        });
        //******************************************************
    }
}