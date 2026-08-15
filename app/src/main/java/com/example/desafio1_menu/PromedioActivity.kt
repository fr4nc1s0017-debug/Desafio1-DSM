package com.example.desafio1_menu

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class PromedioActivity : AppCompatActivity(){

    private lateinit var Nombre_Estudiante: EditText
    private lateinit var Nota1: EditText
    private lateinit var Nota2: EditText
    private lateinit var Nota3: EditText
    private lateinit var Nota4: EditText
    private lateinit var Nota5: EditText
    private lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.promedio)

        Nombre_Estudiante = findViewById(R.id.etEstudiante)
        Nota1 = findViewById(R.id.etNota1)
        Nota2 = findViewById(R.id.etNota2)
        Nota3 = findViewById(R.id.etNota3)
        Nota4 = findViewById(R.id.etNota4)
        Nota5 = findViewById(R.id.etNota5)
        btnCalcular = findViewById(R.id.btnCalcular)
    }
}
