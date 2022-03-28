package com.myapp.util

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

object FormatNumbers {
    fun formatNumber(input: Double, addSymbol: String = "", decimal: Int = 2): String{
        if(addSymbol == "%"){
            return String.format("%.${decimal}f %%",input*100)
        }else{
            if(decimal == 0){
                return String.format("%.${decimal}f ${addSymbol}",input)
            }else{
                return String.format("%#,.${decimal}f ${addSymbol}",input)
            }
        }
    }
}