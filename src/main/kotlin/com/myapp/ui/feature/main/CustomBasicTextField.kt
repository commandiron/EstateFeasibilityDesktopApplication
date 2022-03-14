package com.myapp.ui.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomBasicTextField(
    modifier: Modifier,
    entry: String,
    hint: String,
    addedSymbol: String? = null,
    fontSize : Int,
    inputTypeIsNumber: Boolean = false,
    onChange:(String) -> Unit = {}){

    var text by remember{ mutableStateOf("") }
    text = entry

    OutlinedTextField(

        textStyle = TextStyle(fontSize = fontSize.sp),
        label = { Text(text = hint, modifier = Modifier.alpha(0.3f)) },
        modifier = modifier,
        value = text,
        onValueChange = { output ->

            if(inputTypeIsNumber){
                if(addedSymbol != null){
                    if(output == " $addedSymbol"){
                        text = ""
                        onChange(text)
                    }else{

                        //Sembolü ve harfleri filtrele
                        val filteredReOutPut = output.filter { it.isDigit() }
                        text = filteredReOutPut

                        //Para sembolü var ise ondalık ekle, cursor bozuluyor. Daha sonra geri dön
//                        if(addedSymbol.contains("₺")){
//                            if(filteredReOutPut.length >= 7){
//                                val str = StringBuilder(filteredReOutPut)
//                                val insertedString = str
//                                    .insert(filteredReOutPut.length - 6,".")
//                                    .insert(filteredReOutPut.length - 2,".")
//                                text = insertedString.toString()
//
//                            }else if(filteredReOutPut.length >= 4){
//                                val str = StringBuilder(filteredReOutPut)
//                                val insertedString = str.insert(filteredReOutPut.length - 3,".")
//                                text = insertedString.toString()
//                            }else{
//                                text = filteredReOutPut
//                            }
//                        }else{
//                            text = filteredReOutPut
//                        }

                        //OnChange
                        if(text == ""){
                            onChange(text)
                            println("Lütfen Sayı Giriniz.")
                        }else{
                            onChange(text + " $addedSymbol")
                        }
                    }
                }else{
                    text = output.filter { it.isDigit() }
                    onChange(text)
                }
            }else {
                text = output
                onChange(output)
            }
        })
}