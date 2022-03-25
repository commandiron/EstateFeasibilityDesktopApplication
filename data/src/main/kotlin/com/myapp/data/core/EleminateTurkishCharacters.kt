package com.myapp.data.core

fun String.eliminateTurkishCharacters(): String =
    this.replace("İ","i")
        .replace("Ş", "s")
        .replace("Ğ","g")
        .replace("Ç","c")
        .replace("Ö","o")
        .replace("Ü","u")
        .replace("ı","i")
        .replace("ş", "s")
        .replace("ğ","g")
        .replace("ç","c")
        .replace("ö","o")
        .replace("ü","u")