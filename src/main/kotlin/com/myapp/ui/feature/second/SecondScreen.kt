package com.arsa_fizibilite_app_by_command.ui.feature.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arsa_fizibilite_app_by_command.ui.value.R
import com.myapp.ui.feature.AppViewModel
import com.myapp.ui.feature.second.CustomBasicTextField

@Composable
fun SecondScreen(
    appViewModel: AppViewModel,
) {

    var projeAdi by remember { mutableStateOf("") }
    var projeSehir by remember { mutableStateOf("") }
    var projeIlce by remember { mutableStateOf("") }
    var ada by remember { mutableStateOf("") }
    var parsel by remember { mutableStateOf("") }

    var arsaAlani by remember{ mutableStateOf("") }
    arsaAlani = appViewModel.arsaAlani.value

    var insaatBirimMaliyeti by remember{ mutableStateOf("") }
    insaatBirimMaliyeti = appViewModel.insaatBirimMaliyeti.value

    var brutAlanBirimSatisFiyati by remember{ mutableStateOf("") }
    brutAlanBirimSatisFiyati = appViewModel.brutAlanBirimSatisFiyati.value

    var hedefKarOrani by remember{ mutableStateOf("") }
    hedefKarOrani = appViewModel.hedefKarOrani.value

    var bodrumKatAlani by remember{ mutableStateOf("") }
    bodrumKatAlani = appViewModel.bodrumKatAlani.value

    var bodrumKatBirimMaliyeti by remember{ mutableStateOf("") }
    bodrumKatBirimMaliyeti = appViewModel.bodrumKatBirimMaliyeti.value

    var bodrumKatAdedi by remember{ mutableStateOf("") }
    bodrumKatAdedi = appViewModel.bodrumKatAdedi.value

    var mevcutDaireSayisi by remember{ mutableStateOf("") }
    mevcutDaireSayisi = appViewModel.mevcutDaireSayisi.value

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
//                    FizibiliteModel(
//                        projeAdi= ,
//                        projeSehir= ,
//                        projeIlce= ,
//                        ada= ,
//                        parsel= ,
//                        arsaAlani= ,
//                        insaatBirimMaliyeti= ,
//                        brutAlanBirimSatisFiyati= ,
//                        hedefKarOrani= ,
//                        bodrumKatAlani= ,
//                        bodrumKatBirimMaliyeti= ,
//                        bodrumKatAdedi= ,
//                        mevcutDaireSayisi= ,
//                    )

                    appViewModel.onButtonClicked()
                }
            ) {
                Text(text = R.string.ACTION_MAIN_HESAPLA)
            }
        }
    }
}