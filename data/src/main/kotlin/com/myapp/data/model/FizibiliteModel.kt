package com.myapp.data.model

data class FizibiliteModel(

    //First Screen
    var projeAdi: String = "",
    var projeSehir: String = "",
    var projeIlce: String = "",
    var projeMahalle: String = "",
    var ada: String = "",
    var parsel: String = "",

    //Second Screen
    var arsaAlani: Double? = null,
    var insaatBirimMaliyeti: Double? = 0.0,
    var brutAlanBirimSatisFiyati: Double? = null,
    var hedefKarOrani: Double? = 15.0,
    var bodrumKatAlani: Double? = 0.0,
    var bodrumKatBirimMaliyeti: Double? = 0.0,
    var bodrumKatAdedi: Double? = 0.0,
    var mevcutDaireSayisi: Double? = 0.0,

) {
}