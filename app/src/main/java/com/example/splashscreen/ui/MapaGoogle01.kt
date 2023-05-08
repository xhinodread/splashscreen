package com.example.splashscreen.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap

@Composable
fun MapaBase(){

    Text("mapa")
    GoogleMap(modifier = Modifier.fillMaxSize())
}