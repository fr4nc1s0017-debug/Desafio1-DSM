package com.example.desafio1_menu

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.desafio1_menu.ui.theme.DEsafio1MenuTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DEsafio1MenuTheme {
                MenuPrincipal(
                    onEjercicio1 = {
                        val intent = Intent(this, PromedioActivity::class.java)
                        startActivity(intent)
                    },
                    onEjercicio2 = {
                        val intent = Intent(this, SalarioActivity::class.java)
                        startActivity(intent)
                    },
                    onEjercicio3 = {
                        val intent = Intent(this, CalculadoraActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun MenuPrincipal(
    onEjercicio1: () -> Unit,
    onEjercicio2: () -> Unit,
    onEjercicio3: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Menú de Ejercicios",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onEjercicio1,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ejercicio 1 - Promedio")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onEjercicio2,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ejercicio 2 - Salario")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onEjercicio3,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ejercicio 3 - Calculadora")
        }
    }
}