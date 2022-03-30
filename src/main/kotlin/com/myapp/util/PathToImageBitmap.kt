package com.myapp.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import java.io.File

object PathToImageBitmap {
    fun invoke(imagePath: String): ImageBitmap?{
        try {
            val file = File(imagePath)
            return  loadImageBitmap(file.inputStream())
        }catch (e:Exception){
            return null
        }

    }
}