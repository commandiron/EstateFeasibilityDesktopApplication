package com.arsa_fizibilite_app_by_command.ui.value

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.skia.Surface

// Color set
val LightTheme = lightColors() // TODO :
val DarkTheme = darkColors(

    primary = Color.White,
    primaryVariant = Color.Red,
    secondary = Color.Green,

    background = Color(0xFF255560),

    surface = Color(0xFFE1E8EB),
)

@Composable
fun arsa_fizibilite_app_by_commandTheme(
    isDark: Boolean = true, // TODO: If you want to support both light theme and dark theme, you'll need to implement it manually.
    content: @Composable ColumnScope.() -> Unit,
) {
    MaterialTheme(
        colors = if (isDark) DarkTheme else LightTheme,
        typography = arsa_fizibilite_app_by_commandTypography
    ) {
        Scaffold(
            backgroundColor = MaterialTheme.colors.background,
            topBar = {
                TopAppBar(
                    modifier = Modifier.border(2.dp,Color.Gray),
                    backgroundColor = MaterialTheme.colors.surface,
                ) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            text = "FİZİBİLİTE UYGULAMASI",
                            color = Color.Black
                        )
                    }
                }
            }
        ) {
            Surface(
                color = MaterialTheme.colors.background
            ) {
                Column {
                    content()
                }
            }
        }
    }
}