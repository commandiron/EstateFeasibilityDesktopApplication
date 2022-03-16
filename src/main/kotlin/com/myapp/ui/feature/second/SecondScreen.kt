package com.arsa_fizibilite_app_by_command.ui.feature.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arsa_fizibilite_app_by_command.ui.value.R
import com.myapp.data.model.FizibiliteModel
import com.myapp.ui.feature.second.CustomBasicTextField
import com.myapp.ui.feature.second.SecondViewModel

@Composable
fun SecondScreen(
    secondViewModel: SecondViewModel,
) {

    val projeAdi by remember { mutableStateOf("") }
    val projeSehir by remember { mutableStateOf("") }
    val projeIlce by remember { mutableStateOf("") }
    val ada by remember { mutableStateOf("") }
    val parsel by remember { mutableStateOf("") }

    var arsaAlani by remember{ mutableStateOf("") }
    arsaAlani = secondViewModel.arsaAlani.value

    var insaatBirimMaliyeti by remember{ mutableStateOf("") }
    insaatBirimMaliyeti = secondViewModel.insaatBirimMaliyeti.value

    var brutAlanBirimSatisFiyati by remember{ mutableStateOf("") }
    brutAlanBirimSatisFiyati = secondViewModel.brutAlanBirimSatisFiyati.value

    var hedefKarOrani by remember{ mutableStateOf("") }
    hedefKarOrani = secondViewModel.hedefKarOrani.value

    var bodrumKatAlani by remember{ mutableStateOf("") }
    bodrumKatAlani = secondViewModel.bodrumKatAlani.value

    var bodrumKatBirimMaliyeti by remember{ mutableStateOf("") }
    bodrumKatBirimMaliyeti = secondViewModel.bodrumKatBirimMaliyeti.value

    var bodrumKatAdedi by remember{ mutableStateOf("") }
    bodrumKatAdedi = secondViewModel.bodrumKatAdedi.value

    var mevcutDaireSayisi by remember{ mutableStateOf("") }
    mevcutDaireSayisi = secondViewModel.mevcutDaireSayisi.value

    BoxWithConstraints (
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
                inputTypeIsNumber = true)
                {
                arsaAlani = it
                println(arsaAlani)
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                textIsEmptyError = insaatBirimMaliyeti.isEmpty(),
                constraints = constraints,
                entry = insaatBirimMaliyeti,
                hint = "İnşaat Birim Maliyeti Giriniz. (₺/m²)",
                addedSymbol = "₺/m²",
                inputTypeIsNumber = true)
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
                inputTypeIsNumber = true)
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
                inputTypeIsNumber = true)
            {
                hedefKarOrani = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                constraints = constraints,
                entry = bodrumKatAlani,
                hint = "Bodrum Kat Alanı Giriniz. (m²) - opsiyonel",
                addedSymbol = "m²",
                inputTypeIsNumber = true)
            {
                bodrumKatAlani = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                constraints = constraints,
                entry = bodrumKatBirimMaliyeti,
                hint = "Bodrum Kat Birim Maliyeti Giriniz. (₺/m²) - opsiyonel",
                addedSymbol = "₺/m²",
                inputTypeIsNumber = true)
            {
                bodrumKatBirimMaliyeti = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                constraints = constraints,
                entry = bodrumKatAdedi,
                hint = "Bodrum Kat Adedini Giriniz. (ad) - opsiyonel",
                addedSymbol = "ad",
                inputTypeIsNumber = true)
            {
                bodrumKatAdedi = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomBasicTextField(
                constraints = constraints,
                entry = mevcutDaireSayisi,
                hint = "Mevcut Daire Sayısı. (ad) - opsiyonel",
                addedSymbol = "ad",
                inputTypeIsNumber = true)
            {
                mevcutDaireSayisi = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = {
                    if(
                        arsaAlani.isNotEmpty() &&
                        insaatBirimMaliyeti.isNotEmpty() &&
                        brutAlanBirimSatisFiyati.isNotEmpty() &&
                        hedefKarOrani.isNotEmpty()){

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
                    }else{
                        println("Boş olan kutucukları doldurunuz.")
                    }
                }
            ) {
                Text(text = R.string.ACTION_MAIN_HESAPLA)
            }
        }
    }
}