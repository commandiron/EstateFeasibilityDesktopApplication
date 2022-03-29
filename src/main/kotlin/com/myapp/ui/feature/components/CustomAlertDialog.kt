package com.myapp.ui.feature.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterialApi
@Composable
fun CustomAlertDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit = {}
) {

    AlertDialog(
        contentColor = Color.Black,
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = "Hayır", color = Color.Black)
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = "Evet", color = Color.Black)
            }
        },
        title = {
            Text(text = "Hesaplama kaydınız silinecektir, emin misiniz?", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    )
}