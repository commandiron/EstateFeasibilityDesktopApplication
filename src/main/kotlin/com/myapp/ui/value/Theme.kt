package com.arsa_fizibilite_app_by_command.ui.value

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
import org.jetbrains.skia.Surface

// Color set
val LightTheme = lightColors() // TODO :
val DarkTheme = darkColors(

    primary = Color(0xFF6E7987),


    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC6),
    secondaryVariant = Color.White,

    background = Color(0xFF1d4170),//

    error = Color(0xFFCF6679),
    surface = Color.Red,
    onPrimary= Color.Black,
    onSecondary= Color.Black,
    onBackground = Color.White,
    onSurface= Color.White,
    onError= Color.Black
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
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.primary
                ) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = "KAT KARŞILIĞI İNŞAAT FİZİBİLİTE UYGULAMASI",
                            style = MaterialTheme.typography.h5
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