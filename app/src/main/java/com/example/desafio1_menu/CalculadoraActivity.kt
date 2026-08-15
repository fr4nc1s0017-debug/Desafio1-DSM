package com.example.desafio1_menu

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var etNumero1: EditText
    private lateinit var etNumero2: EditText
    private lateinit var btnSumar: Button
    private lateinit var btnRestar: Button
    private lateinit var btnMultiplicar: Button
    private lateinit var btnDividir: Button
    private lateinit var btnExponente: Button
    private lateinit var btnRaiz: Button

    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculadora)

        etNumero1 = findViewById(R.id.etNumero1)
        etNumero2 = findViewById(R.id.etNumero2)

        btnSumar = findViewById(R.id.btnSumar)
        btnRestar = findViewById(R.id.btnRestar)
        btnMultiplicar = findViewById(R.id.btnMultiplicar)
        btnDividir = findViewById(R.id.btnDividir)
        btnExponente = findViewById(R.id.btnExponente)
        btnRaiz = findViewById(R.id.btnRaiz)

        tvResultado = findViewById(R.id.tvResultado)

        btnSumar.setOnClickListener {
            calcular('+')
        }

        btnRestar.setOnClickListener {
            calcular('-')
        }

        btnMultiplicar.setOnClickListener {
            calcular('*')
        }

        btnDividir.setOnClickListener {
            calcular('/')
        }

        btnExponente.setOnClickListener {
            calcular('^')
        }

        btnRaiz.setOnClickListener {
            calcular('√')
        }
    }

    private fun calcular(operacion: Char) {

        val numero1 = etNumero1.text.toString().toDoubleOrNull()
        val numero2 = etNumero2.text.toString().toDoubleOrNull()

        if (numero1 == null || numero2 == null) {
            tvResultado.text = "Ingrese ambos números"
            return
        }

        val resultado = when (operacion) {
            '+' -> numero1 + numero2
            '-' -> numero1 - numero2
            '*' -> numero1 * numero2
            '/' -> {
                if (numero2 == 0.0) {
                    tvResultado.text = "No se puede dividir entre cero"
                    return
                }

                numero1 / numero2
            }

            '^' -> Math.pow(numero1, numero2)
            '√' -> Math.sqrt(numero1)
            else -> 0.0
        }

        tvResultado.text = "Resultado: $resultado"
    }
}