package com.example.fran.llamadaentrante

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

class LlamadaEntranteActivity : AppCompatActivity() {

    private val PERMISOS_INICIALES = arrayOf<String>(Manifest.permission.READ_PHONE_STATE)
    private val PETICION_INICIAL = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llamada_entrante)

        if (!hayPermiso(Manifest.permission.READ_PHONE_STATE)) {
            ActivityCompat.requestPermissions(this, PERMISOS_INICIALES, PETICION_INICIAL);
        }
    }

    private fun hayPermiso(perm: String): Boolean {
        return ContextCompat.checkSelfPermission(this, perm) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (!hayPermiso(Manifest.permission.READ_PHONE_STATE)) {
            Toast.makeText(this, "No se podrán capturar las llamadas, no has dado permiso", Toast.LENGTH_LONG).show()
        }
    }

}
