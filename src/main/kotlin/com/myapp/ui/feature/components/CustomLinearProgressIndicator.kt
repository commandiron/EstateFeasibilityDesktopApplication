package com.myapp.ui.feature.components

import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*

@Composable
fun CustomLinearProgressIndicator(duration: Float){

    var durationProcess by remember { mutableStateOf(0f) }
    durationProcess = duration

    LinearProgressIndicator(
        progress = durationProcess
    )
}