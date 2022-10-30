package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected  static MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();;
    protected  IntentFilter intentFilterapp1;
    protected  IntentFilter intentFilterapp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilterapp2 = new IntentFilter("com.journaldev.broadcastreceiver.SOME_ACTION");

        //Enviando mensaje interno********************************
        intentFilterapp1 = new IntentFilter("com.Sending.Message.Sameapp");

        Button enviarmensajeinterno = (Button) findViewById(R.id.botonEnviar);
        EditText mensajeEscrito = findViewById(R.id.escribirMensaje);

        enviarmensajeinterno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.Sending.Message.Sameapp");
                intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES); //seguridad
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //esta linea evita los duplicados
                intent.putExtra("app1", String.valueOf(mensajeEscrito.getText()));
                sendBroadcast(intent);

                mensajeEscrito.setText("");
            }
        });
        //******************************************************

        //para imprimir los mensajes en la app1
        String mensajePorImprimir = getIntent().getStringExtra("msj");
        TextView imprimir = (TextView) findViewById(R.id.print);

        if (mensajeEscrito != null) {
            imprimir.setText(mensajePorImprimir);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcastReceiver, intentFilterapp1);
        registerReceiver(myBroadcastReceiver, intentFilterapp2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
        unregisterReceiver(myBroadcastReceiver);
    }
}