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
    var hedefKarOrani: Double? = 0.15,
    var bodrumKatAlani: Double? = 0.0,
    var bodrumKatBirimMaliyeti: Double? = 0.0,
    var bodrumKatAdedi: Int? = 0,
    var mevcutDaireSayisi: Int? = 0,

) {
}