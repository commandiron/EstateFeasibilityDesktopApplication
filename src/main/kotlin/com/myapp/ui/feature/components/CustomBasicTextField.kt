    package com.myapp.ui.feature.second

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.ui.feature.components.HoverTextState
import com.myapp.ui.feature.components.TextFieldVisualTransformation
import java.awt.Cursor

@OptIn(ExperimentalComposeUiApi::class)
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
    showTrailingIcon: Boolean = false,
    onHoverTrailingIcon:(HoverTextState) -> Unit = {},
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

    val hoverTextState by remember{ mutableStateOf(HoverTextState()) }

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
        trailingIcon = {
            if(showTrailingIcon){
                Icon(
                    modifier = Modifier
                        .pointerHoverIcon(PointerIcon(Cursor.getDefaultCursor()))
                        .pointerMoveFilter(onMove = {
                            false },
                            onEnter = {
                                hoverTextState.isCurserOverInfo = true
                                onHoverTrailingIcon(hoverTextState)
                                true
                            },
                            onExit = {
                                hoverTextState.isCurserOverInfo = false
                                onHoverTrailingIcon(hoverTextState)
                                true
                            })
                        .onGloballyPositioned {
                            hoverTextState.positionX = it.positionInWindow().x
                            hoverTextState.positionY = it.positionInWindow().y
                        },
                    imageVector = Icons.Default.Info,
                    contentDescription = null
                )
            }
        }
    )
}