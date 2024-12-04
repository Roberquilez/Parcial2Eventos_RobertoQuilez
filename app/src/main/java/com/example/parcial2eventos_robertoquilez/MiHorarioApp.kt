package com.example.parcial2eventos_robertoquilez

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MiHorarioApp() {
    var selectedOption by remember { mutableStateOf(-1) } // -1: Welcome, 0: Añadir, 1: Ver, 2: ¿Qué toca ahora?, 3: Eventos, 4: Listas de Farmacias
    var horario by remember { mutableStateOf(mutableListOf<Clase>()) }
    var eventos by remember { mutableStateOf(mutableListOf<Evento>()) }
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
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { selectedOption = 3 }, modifier = Modifier.fillMaxWidth(0.8f)) {
                    Text("Eventos")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { selectedOption = 4 }, modifier = Modifier.fillMaxWidth(0.8f)) {
                    Text("Listas de Farmacias")
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
            3 -> PantallaEventos(eventos, onAgregarEvento = { selectedOption = 4 }) {
                selectedOption = -1
            } // Eventos
            4 -> PantallaListasFarmacias {
                selectedOption = -1
            } // Listas de Farmacias
        }
    }
}