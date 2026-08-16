package com.example.desafio1_menu

import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SalarioActivity  : AppCompatActivity(){
    private lateinit var etNombre: EditText
    private lateinit var etSalario: EditText
    private lateinit var tvAFP: TextView
    private lateinit var tvISSS: TextView
    private lateinit var tvRenta: TextView
    private lateinit var tvSalarioNeto: TextView
    private lateinit var tvResultado: TextView
    private lateinit var btnCalcular: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.salario)

        etNombre = findViewById(R.id.etNombre)
        etSalario = findViewById(R.id.etSalario)
        tvAFP = findViewById(R.id.tvAFP)
        tvISSS = findViewById(R.id.tvISSS)
        tvRenta = findViewById(R.id.tvRenta)
        tvSalarioNeto = findViewById(R.id.tvSalarioNeto)
        tvResultado = findViewById(R.id.tvResultado)

        btnCalcular = findViewById(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            calcularSalario()
        }

    }
    private fun calcularSalario() {

        val nombre = etNombre.text.toString()
        val salario = etSalario.text.toString().toDoubleOrNull()
        if (nombre.isEmpty()) {
            etNombre.error = "Ingrese un nombre"
            vibrar()
            return
        }
        if (salario == null || salario <= 0) {
            etSalario.error = "Salario inválido"
            vibrar()
            return
        }
        val afp = salario * 0.0725
        val isss = salario * 0.03
        val renta = calcularRenta(salario)
        val salarioNeto = salario - afp - isss - renta
        tvAFP.text = "Descuento AFP: $${String.format("%.2f", afp)}"
        tvISSS.text = "Descuento ISSS: $${String.format("%.2f", isss)}"
        tvRenta.text = "Descuento Renta: $${String.format("%.2f", renta)}"
        tvSalarioNeto.text = "Salario Neto: $${String.format("%.2f", salarioNeto)}"
        tvResultado.text = """
            Empleado: $nombre
            Salario Base: $${String.format("%.2f", salario)}
            AFP: $${String.format("%.2f", afp)}
            ISSS: $${String.format("%.2f", isss)}
            Renta: $${String.format("%.2f", renta)}
            Salario Neto:
            $${String.format("%.2f", salarioNeto)}
            """.trimIndent()
    }

    private fun calcularRenta(salario: Double): Double {

        return when {
            salario <= 472.00 -> 0.0
            salario <= 895.24 -> (salario - 472.00) * 0.10 + 17.67
            salario <= 2038.10 -> (salario - 895.24) * 0.20 + 60.00
            else -> (salario - 2038.10) * 0.30 + 288.57
        }
    }

    private fun vibrar() {
        val vibrator =
            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate( VibrationEffect.createOneShot( 300, VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            vibrator.vibrate(300)
        }
    }
}
