package com.example.desafio1_menu

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.text.DecimalFormat

class PromedioActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etNota1: EditText
    private lateinit var etNota2: EditText
    private lateinit var etNota3: EditText
    private lateinit var etNota4: EditText
    private lateinit var etNota5: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.promedio)

        etNombre = findViewById(R.id.etEstudiante)
        etNota1 = findViewById(R.id.etNota1)
        etNota2 = findViewById(R.id.etNota2)
        etNota3 = findViewById(R.id.etNota3)
        etNota4 = findViewById(R.id.etNota4)
        etNota5 = findViewById(R.id.etNota5)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvResultado = findViewById(R.id.tvResultado)

        btnCalcular.setOnClickListener {
            calcularPromedio()
        }

        crearCanalNotificacion()
    }

    private fun calcularPromedio() {
        val nombre = etNombre.text.toString().trim()

        if (nombre.isEmpty()) {
            tvResultado.text = getString(R.string.error_nombre)
            return
        }

        val n1 = etNota1.text.toString().toDoubleOrNull()
        val n2 = etNota2.text.toString().toDoubleOrNull()
        val n3 = etNota3.text.toString().toDoubleOrNull()
        val n4 = etNota4.text.toString().toDoubleOrNull()
        val n5 = etNota5.text.toString().toDoubleOrNull()

        if (n1 == null || n2 == null || n3 == null || n4 == null || n5 == null) {
            tvResultado.text = getString(R.string.error_notas)
            return
        }

        val notas = listOf(n1, n2, n3, n4, n5)

        if (notas.any { it < 0 || it > 10 }) {
            tvResultado.text = getString(R.string.error_rango)
            return
        }

        val promedio = notas.sum() / 5
        val df = DecimalFormat("#.00")
        val promedioFormateado = df.format(promedio)

        val estado = if (promedio >= 7) {
            getString(R.string.estado_aprobado)
        } else {
            getString(R.string.estado_reprobado)
        }

        // Usando string con formato desde resources
        tvResultado.text = getString(
            R.string.resultado_formato,
            nombre,
            promedioFormateado,
            estado
        )

        enviarNotificacion(nombre, estado, promedioFormateado)
    }

    private fun crearCanalNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "promedio_channel",
                "Canal de Promedios",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun enviarNotificacion(nombre: String, estado: String, promedio: String) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, "promedio_channel")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(getString(R.string.notificacion_titulo))
            .setContentText(getString(R.string.notificacion_texto, nombre, estado, promedio))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notification)
    }
}