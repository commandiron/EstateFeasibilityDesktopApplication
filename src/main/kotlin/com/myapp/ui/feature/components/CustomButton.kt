package com.myapp.ui.feature.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CustomButton(
    onClick: () -> Unit = {},
    content: @Composable() (RowScope.() -> Unit)
){

    Button(
        onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary,disabledBackgroundColor =  MaterialTheme.colors.primary)
    ){
        content
    }
}