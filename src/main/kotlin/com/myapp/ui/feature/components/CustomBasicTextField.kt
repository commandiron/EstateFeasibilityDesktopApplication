package com.myapp.ui.feature.second

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.ui.feature.components.TextFieldVisualTransformation

@Composable
fun CustomBasicTextField(
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    textIsEmptyError: Boolean = false,
    constraints: Constraints,
    entry: String,
    hint: String,
    addedSymbol: String? = null,
    inputTypeIsNumber: Boolean = false,
    onFocusChange:(Boolean) -> Unit = {},
    onChange:(String) -> Unit = {}){

    val windowHeight = constraints.maxHeight
    val windowWidth = constraints.maxWidth
    val fontSize = Math.round(windowHeight/50.toDouble()).toInt()

    var text by remember{ mutableStateOf("") }
    text = entry

    var unfocusedIndicatorColor = MaterialTheme.colors.primary
    var focusedIndicatorColor  = MaterialTheme.colors.onSurface

    if(textIsEmptyError){
        unfocusedIndicatorColor = Color.Red
        focusedIndicatorColor = Color.Red
    }

    OutlinedTextField(
        modifier = modifier.width((windowWidth/2).dp).defaultMinSize(minHeight = fontSize.dp).onFocusChanged {
            if(text.toDoubleOrNull() == 0.0){
                text = ""
                onChange(text)
            }
        }.onFocusEvent {
            onFocusChange(it.hasFocus)
        },
        readOnly = readOnly,
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = MaterialTheme.colors.onSurface,
            unfocusedLabelColor = MaterialTheme.colors.onSurface,
            unfocusedIndicatorColor = unfocusedIndicatorColor,
            focusedIndicatorColor = focusedIndicatorColor),
        textStyle = TextStyle(fontSize = fontSize.sp),
        label = { Text(text = hint, modifier = Modifier.alpha(1f), fontSize = fontSize.sp) },
        value = text,
        onValueChange = { output ->

            if(inputTypeIsNumber){
                text = output.filter { it.isDigit() or it.equals(".".first(),false) }

                if(output.isEmpty()){
                    text = output
                }else{
                    when(output.toDoubleOrNull()){
                        null -> text = ""
                        else -> text = output
                    }
                }

                onChange(text)
            }else{
                text = output
                onChange(text)
            }
        },
        visualTransformation = if(addedSymbol == null) VisualTransformation.None else TextFieldVisualTransformation(addedSymbol),
    )
}