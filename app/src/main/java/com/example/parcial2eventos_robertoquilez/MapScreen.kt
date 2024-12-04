package com.example.parcial2eventos_robertoquilez

import androidx.compose.runtime.Composable
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(latitude: Double, longitude: Double) {
    val position = LatLng(latitude, longitude)
    val cameraPositionState = rememberCameraPositionState {
        move(CameraUpdateFactory.newLatLngZoom(position, 15f))
    }

    GoogleMap(
        cameraPositionState = cameraPositionState
    ) {
        Marker(state = rememberMarkerState(position = position))
    }
}