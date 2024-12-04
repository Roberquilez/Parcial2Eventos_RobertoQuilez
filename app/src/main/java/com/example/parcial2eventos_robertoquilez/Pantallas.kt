package com.example.parcial2eventos_robertoquilez

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PantallaAgregarClase(onGuardarClase: (Clase) -> Unit) {
    var asignatura by remember { mutableStateOf("") }
    var dia by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    val diasSemana = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Añadir Clase", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Cuadro de texto para el nombre de la asignatura
        OutlinedTextField(
            value = asignatura,
            onValueChange = { asignatura = it },
            label = { Text("Asignatura") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Cuadro de selección para el día de la semana
        Box {
            OutlinedTextField(
                value = dia,
                onValueChange = { },
                label = { Text("Día de la semana") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                diasSemana.forEach { diaSemana ->
                    DropdownMenuItem(
                        onClick = {
                            dia = diaSemana
                            expanded = false
                        },
                        text = { Text(text = diaSemana) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Cuadro de texto para la hora
        OutlinedTextField(
            value = hora,
            onValueChange = { hora = it },
            label = { Text("Hora") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar la clase
        Button(onClick = {
            val nuevaClase = Clase(asignatura, dia, hora)
            onGuardarClase(nuevaClase)
        }) {
            Text("Guardar")
        }
    }
}

@Composable
fun PantallaVerHorario(
    horario: List<Clase>,
    diaSeleccionado: String,
    onDiaSeleccionado: (String) -> Unit,
    onVolver: () -> Unit
) {
    val diasSemana = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Ver Horario", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))

        diasSemana.forEach { dia ->
            Button(onClick = { onDiaSeleccionado(dia) }, modifier = Modifier.fillMaxWidth(0.8f)) {
                Text(dia)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))
        horario.filter { it.dia == diaSeleccionado }.forEach { clase ->
            Text("${clase.hora} - ${clase.asignatura}")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onVolver) {
            Text("Volver")
        }
    }
}

@Composable
fun PantallaQueTocaAhora(horario: List<Clase>, onVolver: () -> Unit) {
    val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    val currentDay = SimpleDateFormat("EEEE", Locale.getDefault()).format(Date())
    val clasesHoy = horario.filter { it.dia == currentDay }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "¿Qué toca ahora?", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))

        if (clasesHoy.isNotEmpty()) {
            clasesHoy.forEach { clase ->
                Text(
                    text = "Tienes ${clase.asignatura} a las ${clase.hora}",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        } else {
            Text(
                text = "No hay clases en este momento",
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onVolver) {
            Text("Volver")
        }
    }
}