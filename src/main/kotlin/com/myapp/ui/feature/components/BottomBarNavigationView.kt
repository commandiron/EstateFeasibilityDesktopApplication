package com.myapp.ui.feature.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arsa_fizibilite_app_by_command.ui.navigation.NavHostComponent
import com.arsa_fizibilite_app_by_command.ui.value.R.color.navigationIconColor

@Composable
fun BottomNavigationView(bottomBarState: Boolean, currentRoute: String, onBottomNavItemClick: (String) -> Unit) {
    val items = listOf(
        //NavHostComponent.Config.Splash,
        NavHostComponent.Config.Saved,
        NavHostComponent.Config.First,
        //NavHostComponent.Config.Second,
        //NavHostComponent.Config.Third,
        //NavHostComponent.Config.Fourth,
        NavHostComponent.Config.Settings
    )

    AnimatedVisibility(
        visible = bottomBarState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
            ) {

                items.forEach { item ->

                    BottomNavigationItem(
                        modifier = Modifier.background(MaterialTheme.colors.surface),
                        icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                        label = { Text(text = item.title,
                            fontSize = 9.sp) },
                        alwaysShowLabel = false,
                        selected = currentRoute == item.title,
                        unselectedContentColor = navigationIconColor,
                        selectedContentColor = MaterialTheme.colors.background,
                        onClick = {
                            onBottomNavItemClick(item.title)
                        }
                    )
                }
            }
        }
    )
}