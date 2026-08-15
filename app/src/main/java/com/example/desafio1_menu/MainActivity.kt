package com.example.desafio1_menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Usa el layout XML que ya tienes

        // Botón para el Ejercicio 1 (Promedio)
        val btnEjercicio1 = findViewById<Button>(R.id.btnEjercicio1)
        btnEjercicio1.setOnClickListener {
            val intent = Intent(this, PromedioActivity::class.java)
            startActivity(intent)
        }

        // Botón para el Ejercicio 2 (Salario)
        val btnEjercicio2 = findViewById<Button>(R.id.btnEjercicio2)
        btnEjercicio2.setOnClickListener {
            val intent = Intent(this, SalarioActivity::class.java)
            startActivity(intent)
        }

        // Botón para el Ejercicio 3 (Calculadora)
        val btnEjercicio3 = findViewById<Button>(R.id.btnEjercicio3)
        btnEjercicio3.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }
    }
}