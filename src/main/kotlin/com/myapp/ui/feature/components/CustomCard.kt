package com.myapp.ui.feature.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.data.model.SavedCalculationDto

@Composable
fun CustomCard(
    content: SavedCalculationDto,
    onSelectedItemClick:(selectedItemId: String)  -> Unit,
    onDeleteButtonClick:(deletedItemId:String)  -> Unit,
){
    Card(
        modifier = Modifier.fillMaxWidth().clickable {
            onSelectedItemClick(content.id)
        },
        elevation = 5.dp,
        contentColor = Color.Black
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            ) {
                Text(
                    text = "Proje Adı: " + content.fizibiliteModel.projeAdi, fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(
                        text = "Ada: " + content.fizibiliteModel.ada, fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Parsel: " + content.fizibiliteModel.parsel, fontSize = 12.sp
                    )
                }
            }
            IconButton(
                onClick = { onDeleteButtonClick(content.id) },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete note",
                    tint = Color.Red
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}