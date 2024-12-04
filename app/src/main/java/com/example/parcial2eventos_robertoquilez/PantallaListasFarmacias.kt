package com.example.parcial2eventos_robertoquilez

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun PantallaListasFarmacias(onVolver: () -> Unit) {
    val context = LocalContext.current
    val farmacias = remember { mutableStateOf(listOf<Farmacia>()) }

    LaunchedEffect(Unit) {
        farmacias.value = cargarFarmacias(context)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Listas de Farmacias", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        farmacias.value.forEach { farmacia ->
            Text("Nombre: ${farmacia.nombre}")
            Text("Dirección: ${farmacia.direccion}")
            Text("Teléfono: ${farmacia.telefono}")
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onVolver) {
            Text("Volver")
        }
    }
}