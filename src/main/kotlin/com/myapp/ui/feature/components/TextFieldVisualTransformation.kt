package com.myapp.ui.feature.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class TextFieldVisualTransformation(addedSymbol: String) : VisualTransformation {

    private val addedSymbol = addedSymbol

    override fun filter(text: AnnotatedString): TransformedText {
        if(text.isNotEmpty()){
            return TransformedText(
                AnnotatedString(text.toString() + " $addedSymbol"),
                OffsetMapping.Identity
            )
        }else{
            return TransformedText(
                AnnotatedString(text.toString()),
                OffsetMapping.Identity
            )
        }
    }
}

//Ondalık için çalıştıramadım daha sonra döneceğim;

//interface OffsetMappingCom : OffsetMapping {
//
//    override fun originalToTransformed(offset: Int): Int{
//        if (offset <= 3) return offset
//        if (offset <= 7) return offset + 1
//        if (offset <= 11) return offset + 2
//        if (offset <= 16) return offset + 3
//        return 19
//    }
//
//    override fun transformedToOriginal(offset: Int): Int{
//        if (offset <= 4) return offset
//        if (offset <= 9) return offset - 1
//        if (offset <= 14) return offset - 2
//        if (offset <= 19) return offset - 3
//        return 16
//    }
//
//    companion object {
//
//        val Identity = object : OffsetMappingCom {
//            override fun originalToTransformed(offset: Int): Int = offset
//            override fun transformedToOriginal(offset: Int): Int = offset
//        }
//    }
//}
