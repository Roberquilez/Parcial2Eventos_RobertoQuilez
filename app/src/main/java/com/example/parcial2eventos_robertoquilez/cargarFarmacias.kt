package com.example.parcial2eventos_robertoquilez


import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun cargarFarmacias(context: Context): List<Farmacia> {
    val jsonString = context.assets.open("farmacias.json").bufferedReader().use { it.readText() }
    return Json.decodeFromString(jsonString)
}