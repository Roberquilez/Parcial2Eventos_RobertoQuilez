package com.example.parcial2eventos_robertoquilez

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MiHorarioApp() {
    var selectedOption by remember { mutableStateOf(-1) } // -1: Welcome, 0: Añadir, 1: Ver, 2: ¿Qué toca ahora?
    var horario by remember { mutableStateOf(mutableListOf<Clase>()) }
    var diaSeleccionado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (selectedOption) {
            -1 -> {
                Text(text = "Bienvenido usuario ¿Qué quieres hacer?", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(20.dp))

                // Botones de las opciones
                Button(onClick = { selectedOption = 0 }, modifier = Modifier.fillMaxWidth(0.8f)) {
                    Text("Añadir Clase")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { selectedOption = 1 }, modifier = Modifier.fillMaxWidth(0.8f)) {
                    Text("Ver Horario")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { selectedOption = 2 }, modifier = Modifier.fillMaxWidth(0.8f)) {
                    Text("¿Qué toca ahora?")
                }
            }
            0 -> PantallaAgregarClase { nuevaClase ->
                horario.add(nuevaClase)
                selectedOption = -1
            } // Añadir clase
            1 -> PantallaVerHorario(horario, diaSeleccionado, { diaSeleccionado = it }) {
                selectedOption = -1
            } // Ver horario
            2 -> PantallaQueTocaAhora(horario) {
                selectedOption = -1
            } // ¿Qué toca ahora?
        }
    }
}