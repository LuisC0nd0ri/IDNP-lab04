package com.example.application1;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver
{
    private static Intent intentApp1 = new Intent();

    @Override
    public void onReceive(Context context, Intent intent) {
        //se invoca cuando se recibe un evento al que se ha suscrito la clase
        intentApp1.setClass(context.getApplicationContext(), MainActivity.class);

        String texto = "";
        String app1 = intent.getStringExtra("app1");
        String app2 = intent.getStringExtra("app2");

        //FILTRO DE APP2
        if(intent.getAction().equals("com.journaldev.broadcastreceiver.SOME_ACTION")) {
            //este filtro tambien podria funcionar para el app1
            if(app2 != null) {
                //mensaje de la app2
                texto = ("Aplicacion 2: " + app2);
                Toast.makeText(context, texto, Toast.LENGTH_SHORT).show();
            }
        }else{
            //FILTRO DE APP1
            if (intent.getAction().equals("com.Sending.Message.Sameapp")){
                if(app1 != null) {
                    //mensaje interno de app1
                    texto = ("Aplicacion 1: " + app1);
                    Toast.makeText(context, texto, Toast.LENGTH_SHORT).show();
                }
            }
        }

        //para imprimir el mensaje enviado en la app1
        intentApp1.putExtra("msj", texto);
        context.startActivity(intentApp1);
    }
}