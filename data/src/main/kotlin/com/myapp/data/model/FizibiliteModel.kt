package com.myapp.data.model

@kotlinx.serialization.Serializable
data class FizibiliteModel(

    //First Screen
    var projeAdi: String = "",
    var projeSehir: String = "",
    var projeIlce: String = "",
    var projeMahalle: String = "",
    var ada: String = "",
    var parsel: String = "",
    var imagePath: String = "",

    //Second Screen
    var arsaAlani: Double = 0.0,
    var insaatBirimMaliyeti: Double = 0.0,
    var brutAlanBirimSatisFiyati: Double = 0.0,
    var hedefKarOrani: Double = 0.0,
)