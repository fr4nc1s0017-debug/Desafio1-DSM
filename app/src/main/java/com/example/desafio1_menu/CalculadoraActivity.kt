package com.example.desafio1_menu

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var a: EditText
    private lateinit var b: EditText
    private lateinit var btnSumar: Button
    private lateinit var btnRestar: Button
    private lateinit var btnMultiplicar: Button
    private lateinit var btnDividir: Button
    private lateinit var btnExponente: Button
    private lateinit var btnRaiz: Button
    private lateinit var btnHistorial: Button
    private lateinit var tvResultado: TextView
    private lateinit var tvNumero2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculadora)

        a = findViewById(R.id.etNumero1)
        b = findViewById(R.id.etNumero2)
        btnSumar = findViewById(R.id.btnSumar)
        btnRestar = findViewById(R.id.btnRestar)
        btnMultiplicar = findViewById(R.id.btnMultiplicar)
        btnDividir = findViewById(R.id.btnDividir)
        btnExponente = findViewById(R.id.btnExponente)
        btnRaiz = findViewById(R.id.btnRaiz)

        btnHistorial = findViewById(R.id.btnHistorial)
        tvResultado = findViewById(R.id.tvResultado)
        tvNumero2 = findViewById(R.id.tvNumero2)

        btnSumar.setOnClickListener {
            mostrarSegundoNumero(true)
            calcular('+')
        }
        btnRestar.setOnClickListener {
            mostrarSegundoNumero(true)
            calcular('-')
        }
        btnMultiplicar.setOnClickListener {
            mostrarSegundoNumero(true)
            calcular('*')
        }
        btnDividir.setOnClickListener {
            mostrarSegundoNumero(true)
            calcular('/')
        }

        btnExponente.setOnClickListener {
            mostrarSegundoNumero(true)
            calcular('^')
        }

        btnRaiz.setOnClickListener {
            mostrarSegundoNumero(false)
            calcular('√')
        }

        btnHistorial.setOnClickListener {
            mostrarHistorial()
        }
    }

    private fun mostrarSegundoNumero(mostrar: Boolean) {
        if (mostrar) {
            b.visibility = View.VISIBLE
            tvNumero2.visibility = View.VISIBLE
            b.hint = "Ingrese el segundo número"
        } else {
            b.visibility = View.GONE
            tvNumero2.visibility = View.GONE
            b.text?.clear()
        }
    }

    private fun calcular(operacion: Char) {
        val numero1 = a.text.toString().toDoubleOrNull()


        if (operacion == '√') {
            if (numero1 == null) {
                tvResultado.text = "Ingrese un número válido"
                return
            }
            if (numero1 < 0) {
                tvResultado.text =
                    "No se puede calcular raíz de número negativo"
                return
            }
            val resultado = String.format(
                "%.4f",
                sqrt(numero1)
            )
            val operacionRealizada =
                "√$numero1 = $resultado"
            tvResultado.text = operacionRealizada
            guardarHistorial(operacionRealizada)
            return
        }

        val numero2 = b.text.toString().toDoubleOrNull()

        if (numero1 == null || numero2 == null) {
            tvResultado.text = "Ingrese ambos números"
            return
        }

        val resultado = when (operacion) {
            '+' -> {
                val result = numero1 + numero2

                "$numero1 + $numero2 = ${
                    String.format("%.4f", result)
                }"
            }
            '-' -> {
                val result = numero1 - numero2

                "$numero1 - $numero2 = ${
                    String.format("%.4f", result)
                }"
            }
            '*' -> {
                val result = numero1 * numero2

                "$numero1 × $numero2 = ${
                    String.format("%.4f", result)
                }"
            }
            '/' -> {
                if (numero2 == 0.0) {
                    tvResultado.text =
                        "No se puede dividir entre cero"
                    return
                }
                val result = numero1 / numero2

                "$numero1 ÷ $numero2 = ${
                    String.format("%.4f", result)
                }"
            }
            '^' -> {
                val result = numero1.pow(numero2)

                "$numero1 ^ $numero2 = ${
                    String.format("%.4f", result)
                }"
            }

            else -> { "Operación no válida"
            }
        }
        tvResultado.text = resultado

        guardarHistorial(resultado)
    }
    private fun guardarHistorial(operacion: String) {

        openFileOutput(
            "historial.txt",
            MODE_APPEND
        ).use { archivo ->

            archivo.write(
                "$operacion\n".toByteArray()
            )
        }
    }
    private fun mostrarHistorial() {
        try {
            val historial = openFileInput(
                "historial.txt"
            )
                .bufferedReader()
                .use { it.readText() }
            if (historial.isBlank()) {

                tvResultado.text = "No hay operaciones en el historial"

            } else {

                tvResultado.text = historial
            }
        } catch (e: Exception) {

            tvResultado.text = "No hay operaciones en el historial"
        }
    }
}