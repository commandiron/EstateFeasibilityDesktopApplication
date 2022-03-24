package com.myapp.ui.feature.third

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arsa_fizibilite_app_by_command.ui.value.R
import com.myapp.data.model.FizibiliteModel
import com.myapp.ui.feature.second.CustomBasicTextField

@Composable
fun ThirdScreen(
    thirdViewModel: ThirdViewModel,
    fizibiliteModelFromFirstCal: FizibiliteModel
) {

    var fizibiliteModel by remember { mutableStateOf(FizibiliteModel()) }
    fizibiliteModel = fizibiliteModelFromFirstCal

    var projeAdi by remember { mutableStateOf("") }
    projeAdi = fizibiliteModel.projeAdi

    var projeSehir by remember { mutableStateOf("") }
    projeSehir = fizibiliteModel.projeSehir

    var projeIlce by remember { mutableStateOf("") }
    projeIlce = fizibiliteModel.projeIlce

    var ada by remember { mutableStateOf("") }
    ada = fizibiliteModel.ada

    var parsel by remember { mutableStateOf("") }
    parsel = fizibiliteModel.parsel

    var arsaAlani by remember { mutableStateOf("") }
    arsaAlani = fizibiliteModel.arsaAlani.toString()

    var insaatBirimMaliyeti by remember { mutableStateOf("") }
    insaatBirimMaliyeti = fizibiliteModel.insaatBirimMaliyeti.toString()

    var brutAlanBirimSatisFiyati by remember { mutableStateOf("") }
    brutAlanBirimSatisFiyati = fizibiliteModel.brutAlanBirimSatisFiyati.toString()

    var hedefKarOrani by remember { mutableStateOf("") }
    hedefKarOrani = fizibiliteModel.hedefKarOrani.toString()

    var bodrumKatAlani by remember { mutableStateOf("") }
    bodrumKatAlani = fizibiliteModel.bodrumKatAlani.toString()

    var bodrumKatBirimMaliyeti by remember { mutableStateOf("") }
    bodrumKatBirimMaliyeti = fizibiliteModel.bodrumKatBirimMaliyeti.toString()

    var bodrumKatAdedi by remember { mutableStateOf("") }
    bodrumKatAdedi = fizibiliteModel.bodrumKatAdedi.toString()

    var mevcutDaireSayisi by remember { mutableStateOf("") }
    mevcutDaireSayisi = fizibiliteModel.mevcutDaireSayisi.toString()


    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val constraints = this.constraints

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                textIsEmptyError = arsaAlani.isEmpty(),
                constraints = constraints,
                entry = arsaAlani,
                hint = "Arsa Alanı Giriniz. (m²)",
                addedSymbol = "m²",
                inputTypeIsNumber = true
            )
            {
                arsaAlani = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                textIsEmptyError = insaatBirimMaliyeti.isEmpty(),
                constraints = constraints,
                entry = insaatBirimMaliyeti,
                hint = "İnşaat Birim Maliyeti Giriniz. (₺/m²)",
                addedSymbol = "₺/m²",
                inputTypeIsNumber = true
            )
            {
                insaatBirimMaliyeti = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                textIsEmptyError = brutAlanBirimSatisFiyati.isEmpty(),
                constraints = constraints,
                entry = brutAlanBirimSatisFiyati,
                hint = "Brüt Alan Birim Satış Fiyatı. (₺/m²)",
                addedSymbol = "₺/m²",
                inputTypeIsNumber = true
            )
            {
                brutAlanBirimSatisFiyati = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                textIsEmptyError = hedefKarOrani.isEmpty(),
                constraints = constraints,
                entry = hedefKarOrani,
                hint = "Hedef Kar Oranı. (%)",
                addedSymbol = "%",
                inputTypeIsNumber = true
            )
            {
                hedefKarOrani = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                constraints = constraints,
                entry = bodrumKatAlani,
                hint = "Bodrum Kat Alanı Giriniz. (m²) - opsiyonel",
                addedSymbol = "m²",
                inputTypeIsNumber = true
            )
            {
                bodrumKatAlani = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                constraints = constraints,
                entry = bodrumKatBirimMaliyeti,
                hint = "Bodrum Kat Birim Maliyeti Giriniz. (₺/m²) - opsiyonel",
                addedSymbol = "₺/m²",
                inputTypeIsNumber = true
            )
            {
                bodrumKatBirimMaliyeti = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                constraints = constraints,
                entry = bodrumKatAdedi,
                hint = "Bodrum Kat Adedini Giriniz. (ad) - opsiyonel",
                addedSymbol = "ad",
                inputTypeIsNumber = true
            )
            {
                bodrumKatAdedi = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                constraints = constraints,
                entry = mevcutDaireSayisi,
                hint = "Mevcut Daire Sayısı. (ad) - opsiyonel",
                addedSymbol = "ad",
                inputTypeIsNumber = true
            )
            {
                mevcutDaireSayisi = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = {
                    if (
                        arsaAlani.isNotEmpty() &&
                        insaatBirimMaliyeti.isNotEmpty() &&
                        brutAlanBirimSatisFiyati.isNotEmpty() &&
                        hedefKarOrani.isNotEmpty()
                    ) {

                        val fizibiliteModel =
                            FizibiliteModel(
                                projeAdi = projeAdi,
                                projeSehir = projeSehir,
                                projeIlce = projeIlce,
                                ada = ada,
                                parsel = parsel,
                                //Bunlar boş gelemez hesaplama için gerekli;
                                arsaAlani = arsaAlani.toDouble(),
                                insaatBirimMaliyeti = insaatBirimMaliyeti.toDouble(),
                                brutAlanBirimSatisFiyati = brutAlanBirimSatisFiyati.toDouble(),
                                hedefKarOrani = hedefKarOrani.toDouble(),
                                //Bunlar null gelebilir default değer atadık;
                                bodrumKatAlani = bodrumKatAlani.toDoubleOrNull(),
                                bodrumKatBirimMaliyeti = bodrumKatBirimMaliyeti.toDoubleOrNull(),
                                bodrumKatAdedi = bodrumKatAdedi.toDoubleOrNull(),
                                mevcutDaireSayisi = mevcutDaireSayisi.toDoubleOrNull(),
                            )
                    } else {
                        println("Boş olan kutucukları doldurunuz.")
                    }
                }
            ) {
                Text(text = R.string.ACTION_THIRD_HESAPLA)
            }
        }
    }
}