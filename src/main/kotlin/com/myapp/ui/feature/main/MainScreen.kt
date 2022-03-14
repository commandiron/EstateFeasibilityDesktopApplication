package com.arsa_fizibilite_app_by_command.ui.feature.main

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arsa_fizibilite_app_by_command.ui.value.R
import com.myapp.ui.feature.main.CustomBasicTextField
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    val welcomeText by viewModel.welcomeText.collectAsState()

    var adaParselText by remember{ mutableStateOf("") }
    var arsaAlani by remember{ mutableStateOf("") }
    var insaatBirimMaliyeti by remember{ mutableStateOf("") }
    var bodrumKatBirimMaliyeti by remember{ mutableStateOf("") }
    var bodrumKatAdedi by remember{ mutableStateOf("") }
    var brutAlanBirimSatisFiyati by remember{ mutableStateOf("") }
    var hedefKarOrani by remember{ mutableStateOf("") }
    var mevcutDaireSayisi by remember{ mutableStateOf("") }

    BoxWithConstraints (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val windowHeight = this.constraints.maxHeight
        val windowWidth = this.constraints.maxWidth
        val fontSize = Math.round(windowHeight/50.toDouble()).toInt()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "KAT KARŞILIĞI İNŞAAT FİZİBİLİTE UYGULAMASI",
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                modifier = Modifier.width((windowWidth/2).dp).defaultMinSize(minHeight = fontSize.dp),
                entry = adaParselText,
                hint = "Ada/Parsel Giriniz",
                fontSize = fontSize){
                adaParselText = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                modifier = Modifier.width((windowWidth/2).dp).defaultMinSize(minHeight = fontSize.dp),
                entry = arsaAlani,
                hint = "Arsa Alanı Giriniz. (m²)",
                addedSymbol = "m²",
                fontSize = fontSize,
                inputTypeIsNumber = true)
                {
                arsaAlani = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                modifier = Modifier.width((windowWidth/2).dp).defaultMinSize(minHeight = fontSize.dp),
                entry = insaatBirimMaliyeti,
                hint = "İnşaat Birim Maliyeti Giriniz. (₺/m²)",
                addedSymbol = "₺/m²",
                fontSize = fontSize,
                inputTypeIsNumber = true)
            {
                insaatBirimMaliyeti = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                modifier = Modifier.width((windowWidth/2).dp).defaultMinSize(minHeight = fontSize.dp),
                entry = brutAlanBirimSatisFiyati,
                hint = "Brüt Alan Birim Satış Fiyatı. (₺/m²)",
                addedSymbol = "₺/m²",
                fontSize = fontSize,
                inputTypeIsNumber = true)
            {
                brutAlanBirimSatisFiyati = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                modifier = Modifier.width((windowWidth/2).dp).defaultMinSize(minHeight = fontSize.dp),
                entry = hedefKarOrani,
                hint = "Hedef Kar Oranı. (%)",
                addedSymbol = "%",
                fontSize = fontSize,
                inputTypeIsNumber = true)
            {
                hedefKarOrani = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                modifier = Modifier.width((windowWidth/2).dp).defaultMinSize(minHeight = fontSize.dp),
                entry = bodrumKatBirimMaliyeti,
                hint = "Bodrum Kat Alanı Giriniz. (₺/m²) - opsiyonel",
                addedSymbol = "₺/m²",
                fontSize = fontSize,
                inputTypeIsNumber = true)
            {
                bodrumKatBirimMaliyeti = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                modifier = Modifier.width((windowWidth/2).dp).defaultMinSize(minHeight = fontSize.dp),
                entry = bodrumKatBirimMaliyeti,
                hint = "Bodrum Kat Birim Maliyeti Giriniz. (₺/m²) - opsiyonel",
                addedSymbol = "₺/m²",
                fontSize = fontSize,
                inputTypeIsNumber = true)
            {
                bodrumKatBirimMaliyeti = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                modifier = Modifier.width((windowWidth/2).dp).defaultMinSize(minHeight = fontSize.dp),
                entry = bodrumKatAdedi,
                hint = "Bodrum Kat Adedini Giriniz. (ad) - opsiyonel",
                addedSymbol = "ad",
                fontSize = fontSize,
                inputTypeIsNumber = true)
            {
                bodrumKatAdedi = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                modifier = Modifier.width((windowWidth/2).dp).defaultMinSize(minHeight = fontSize.dp),
                entry = mevcutDaireSayisi,
                hint = "Mevcut Daire Sayısı. (ad) - opsiyonel",
                addedSymbol = "ad",
                fontSize = fontSize,
                inputTypeIsNumber = true)
            {
                mevcutDaireSayisi = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    viewModel.onClickMeClicked()
                }
            ) {
                Text(text = R.string.ACTION_MAIN_HESAPLA)
            }
        }
    }
}