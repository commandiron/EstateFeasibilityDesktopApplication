package com.myapp.model

data class FizibiliteModel(

    var projeAdi: String,
    var projeSehir: String,
    var projeIlce: String,
    var ada: String,
    var parsel: String,
    var arsaAlani: Int,
    var insaatBirimMaliyeti: Int,
    var brutAlanBirimSatisFiyati: Int,
    var hedefKarOrani: Int,
    var bodrumKatAlani: Int,
    var bodrumKatBirimMaliyeti: Int,
    var bodrumKatAdedi: Int,
    var mevcutDaireSayisi: Int,

) {
}