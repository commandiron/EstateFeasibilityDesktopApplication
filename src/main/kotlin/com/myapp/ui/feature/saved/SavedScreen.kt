package com.myapp.ui.feature.saved

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.sp
import com.arsa_fizibilite_app_by_command.ui.value.R
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.model.SavedCalculationDto
import com.myapp.ui.feature.components.CustomAlertDialog
import com.myapp.ui.feature.components.CustomCard
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@ExperimentalMaterialApi
@Composable
fun SavedScreen(
    savedViewModel: SavedViewModel,
    savedToFourthScreen:(selectedItemId: String)  -> Unit
) {

    val savedCalculations = savedViewModel.savedCalculations

    //Get SavedList
    LaunchedEffect(key1 = savedCalculations){
        savedViewModel.loadCalculations()
    }

    //For DeleteItem
    var clickedDeleteItemId by remember { mutableStateOf("") }

    //Alert Dialog For DeleteItem
    var showAlertDialog by remember { mutableStateOf(false) }
    if (showAlertDialog) {
        CustomAlertDialog(
            onDismiss = {showAlertDialog = !showAlertDialog},
            onConfirm = {
                if(clickedDeleteItemId.isNotEmpty()){
                    savedViewModel.deleteCalculation(clickedDeleteItemId)
                }
                showAlertDialog = !showAlertDialog
            })
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val scrollState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 5.dp,
            contentColor = Color.Black
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(4.dp)) {
                Text("FİZİBİLİTE RAPORLARIM")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth().draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { delta ->
                        coroutineScope.launch {
                            scrollState.scrollBy(-delta)
                        }
                    }
                ),
            state = scrollState,
            contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp, start = 60.dp, end = 60 .dp)
        ) {
            if(savedCalculations.value.isNotEmpty()){
                items(savedCalculations.value){ item ->
                    CustomCard(
                        item
                        ,{selectedItemId->
                            savedToFourthScreen(selectedItemId)
                        }
                        ,{deletedItemId ->
                            showAlertDialog = !showAlertDialog
                            clickedDeleteItemId = deletedItemId

                        }
                    )
                }
            }
        }
    }

}