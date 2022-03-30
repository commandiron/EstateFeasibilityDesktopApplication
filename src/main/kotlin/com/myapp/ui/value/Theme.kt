package com.arsa_fizibilite_app_by_command.ui.value

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import com.arsa_fizibilite_app_by_command.ui.navigation.NavHostComponent
import com.myapp.data.model.CalculationResult
import com.myapp.data.model.FizibiliteModel
import com.myapp.ui.feature.components.BottomNavigationView

// Color set
val LightTheme = lightColors() // TODO :
val DarkTheme = darkColors(

    primary = Color(0xFFE1E8EB),

    secondary = Color.Black,

    background = Color(0xFF255560),


    surface = Color(0xFFE1E8EB),
    onSurface = Color(0xFF92ABB6)
)

//RippleTheme
private object JetNewsRippleTheme : RippleTheme {
    // Here you should return the ripple color you want
    // and not use the defaultRippleColor extension on RippleTheme.
    // Using that will override the ripple color set in DarkMode
    // or when you set light parameter to false
    @Composable
    override fun defaultColor(): Color = MaterialTheme.colors.secondary

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Color.Black,
        lightTheme = !isSystemInDarkTheme()
    )
}

//Theme
@Composable
fun arsa_fizibilite_app_by_commandTheme(
    currentRoute: String,
    isDark: Boolean = true, // TODO: If you want to support both light theme and dark theme, you'll need to implement it manually.
    onBottomNavItemClick: (String) -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {


    val bottomBarState = rememberSaveable {(mutableStateOf(true))}

    MaterialTheme(
        colors = if (isDark) DarkTheme else LightTheme,
        typography = arsa_fizibilite_app_by_commandTypography
    ) {
        CompositionLocalProvider(
            LocalRippleTheme provides JetNewsRippleTheme,
            content = {
                Scaffold(
                    //Şu anlık daha iyi görünüm için topBar'ı kaldırdım.
//            topBar = {
//                TopAppBar(
//                    modifier = Modifier.border(2.dp,Color.Gray),
//                    backgroundColor = MaterialTheme.colors.surface,
//                ) {
//
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            textAlign = TextAlign.Center,
//                            fontSize = 20.sp,
//                            text = "FİZİBİLİTE UYGULAMASI",
//                            color = Color.Black
//                        )
//                    }
//                }
//            },
                    bottomBar = {
                        //Burada aslında 3 adet children'ı dallara ayırmamız gerekiyor. Daha sonra yapmak üzere bırakıyorum.
                        //Çünkü şu anda fourth screende eğer bottomnavigation bar'ı aktif hale getirirsem kullanıcı her hangi
                        //birisine bastığı anda en başa dönüyor.
                        bottomBarState.value =
                            currentRoute != NavHostComponent.Config.Second(FizibiliteModel()).title &&
                                    currentRoute != NavHostComponent.Config.Third(FizibiliteModel()).title &&
                                    currentRoute != NavHostComponent.Config.Fourth(CalculationResult(), FizibiliteModel()).title

                        BottomNavigationView(bottomBarState.value,currentRoute){
                            onBottomNavItemClick(it)
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
        )
    }
}

