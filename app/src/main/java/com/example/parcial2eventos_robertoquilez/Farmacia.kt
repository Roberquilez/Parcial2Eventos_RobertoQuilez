package com.example.parcial2eventos_robertoquilez

import kotlinx.serialization.Serializable

@Serializable
data class Farmacia(
    val nombre: String,
    val direccion: String,
    val telefono: String,
    val latitud: Double,
    val longitud: Double
)