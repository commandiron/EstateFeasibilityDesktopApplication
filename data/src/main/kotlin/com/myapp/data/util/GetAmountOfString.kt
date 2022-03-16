package com.myapp.data.util


fun String.getAmount(): String {
    return substring(indexOfFirst { it.isDigit() }, indexOfLast { it.isDigit() } + 1)
        .filter { it.isDigit() || it == ',' }
}
