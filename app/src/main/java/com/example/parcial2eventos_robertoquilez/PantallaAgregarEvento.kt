package com.example.parcial2eventos_robertoquilez

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun PantallaAgregarEvento(onEventoAgregado: (Evento) -> Unit, onVolver: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.agregar_evento), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))

        TextField(value = nombre, onValueChange = { nombre = it }, label = { Text(stringResource(id = R.string.nombre_evento)) })
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = fecha, onValueChange = { fecha = it }, label = { Text(stringResource(id = R.string.fecha_evento)) })
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            onEventoAgregado(Evento(nombre, fecha))
            onVolver()
        }) {
            Text(stringResource(id = R.string.guardar_evento))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = onVolver) {
            Text(stringResource(id = R.string.volver))
        }
    }
}