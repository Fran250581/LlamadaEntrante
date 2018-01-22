package com.example.fran.llamadaentrante

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.PendingIntent
import android.app.NotificationManager
import android.telephony.TelephonyManager
import android.util.Log

class ReceptorLlamadas : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Sacamos información del intent
        var estado: String? = ""
        var numero: String? = ""
        val extras = intent.extras
        if (extras != null) {
            estado = extras.getString(TelephonyManager.EXTRA_STATE)
            if (estado == TelephonyManager.EXTRA_STATE_RINGING) {
                numero = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
            }
        }
        val info = estado + " " + numero
        Log.d("ReceptorAnuncio", info + " intent=" + intent)

        // Creamos Notificación
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val mIntent = Intent(context, LlamadaEntranteActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0)

        val notification = Notification.Builder(context)
                .setContentTitle("Llamada !!!")
                .setTicker("Llamada Entrante (PMDM) ")
                .setContentText(info)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .getNotification()

        nm.notify(1, notification)
    }
}
