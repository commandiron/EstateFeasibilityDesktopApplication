package com.myapp.ui.feature.second

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class TextFieldVisualTransformation(addedSymbol: String) : VisualTransformation {

    private val addedSymbol = addedSymbol

    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString(text.toString() + " $addedSymbol"),
            OffsetMapping.Identity
        )
    }
}
