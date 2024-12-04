package com.example.parcial2eventos_robertoquilez

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.LocaleList
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun PantallaEventos(eventos: List<Evento>, onAgregarEvento: () -> Unit, onVolver: () -> Unit) {
    val context = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.eventos), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))

        eventos.forEach { evento ->
            Text(text = "${evento.nombre} - ${evento.fecha}")
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onAgregarEvento) {
            Text(stringResource(id = R.string.agregar_evento))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = onVolver) {
            Text(stringResource(id = R.string.volver))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { cambiarIdioma(context, "en") }) {
            Text("Change to English")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { cambiarIdioma(context, "es") }) {
            Text("Cambiar a Español")
        }
    }
}

fun cambiarIdioma(context: Context, idioma: String) {
    val locale = Locale(idioma)
    Locale.setDefault(locale)
    val config = Configuration(context.resources.configuration)
    config.setLocales(LocaleList(locale))
    context.resources.updateConfiguration(config, context.resources.displayMetrics)


    val intent = Intent(context, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}