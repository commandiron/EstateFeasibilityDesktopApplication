package com.myapp.ui.feature.third


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arsa_fizibilite_app_by_command.ui.value.R
import com.myapp.data.model.FizibiliteModel
import com.myapp.ui.feature.components.HoverTextState
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

    var projeMahalle by remember { mutableStateOf("") }
    projeMahalle = fizibiliteModel.projeMahalle

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
    hedefKarOrani = (fizibiliteModel.hedefKarOrani!!.times(100)).toString()

    var bodrumKatAlani by remember { mutableStateOf("") }
    bodrumKatAlani = fizibiliteModel.bodrumKatAlani.toString()

    var bodrumKatBirimMaliyeti by remember { mutableStateOf("") }
    bodrumKatBirimMaliyeti = fizibiliteModel.bodrumKatBirimMaliyeti.toString()

    var bodrumKatAdedi by remember { mutableStateOf("") }
    bodrumKatAdedi = fizibiliteModel.bodrumKatAdedi.toString()

    var mevcutDaireSayisi by remember { mutableStateOf("") }
    mevcutDaireSayisi = fizibiliteModel.mevcutDaireSayisi.toString()

    var hoverTextStateFirst: HoverTextState by remember{ mutableStateOf(HoverTextState()) }
    var hoverTextStateSecond: HoverTextState by remember{ mutableStateOf(HoverTextState()) }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val constraints = this.constraints

        //BURADA BİR SIKINTI VAR BİR TÜRLÜÜ İSTEDEĞİM YERE DENK GETİREMİYORUM.

        if(hoverTextStateFirst.isCurserOverInfo){
            Card(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        start = Dp(hoverTextStateFirst.positionX + 20),
                        top = Dp(hoverTextStateFirst.positionY/2 + 20))) {
                Text("Bu değer internetten çekilmiştir.", modifier = Modifier.padding(4.dp), color = Color.Black)
            }
        }
        if(hoverTextStateSecond.isCurserOverInfo){
            Card(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        start = Dp(hoverTextStateSecond.positionX),
                        top = Dp(hoverTextStateSecond.positionY/2 ))) {
                Text("Bu değer internetten çekilmiştir.", modifier = Modifier.padding(4.dp), color = Color.Black)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally) {
                Card(
                    elevation = 5.dp,
                    contentColor = Color.Black) {
                    Text("Proje Adı: $projeAdi ",modifier = Modifier.padding(4.dp))
                }
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier.padding(4.dp),
                    elevation = 5.dp,
                    contentColor = Color.Black) {
                    Column(modifier = Modifier.padding(4.dp)) {
                        Row{
                            Text("Sehir: $projeSehir - ")
                            Text("İlce: $projeIlce - ")
                            Text("Mahalle: $projeMahalle")
                        }
                        Row {
                            Text("Ada: $ada ")
                            Text("Parsel: $parsel")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            CustomBasicTextField(
                textIsEmptyError = arsaAlani.isEmpty(),
                constraints = constraints,
                entry = arsaAlani,
                hint = "Arsa Alanı Giriniz. (m²)",
                addedSymbol = "m²",
                inputTypeIsNumber = true,
                showTrailingIcon = true,
                onHoverTrailingIcon = {
                    hoverTextStateFirst = it.copy()
                }
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
                inputTypeIsNumber = true,
                showTrailingIcon = true,
                onHoverTrailingIcon = {
                    hoverTextStateSecond = it.copy()
                }
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

            //Bu kısmı daha sonra özellik olarak ekleyebilirim, o yüzden yorum haline getiriyorum.
//            CustomBasicTextField(
//                constraints = constraints,
//                entry = bodrumKatAlani,
//                hint = "Bodrum Kat Alanı Giriniz. (m²) - opsiyonel",
//                addedSymbol = "m²",
//                inputTypeIsNumber = true
//            )
//            {
//                bodrumKatAlani = it
//            }
//
//            Spacer(modifier = Modifier.height(5.dp))
//
//            CustomBasicTextField(
//                constraints = constraints,
//                entry = bodrumKatBirimMaliyeti,
//                hint = "Bodrum Kat Birim Maliyeti Giriniz. (₺/m²) - opsiyonel",
//                addedSymbol = "₺/m²",
//                inputTypeIsNumber = true
//            )
//            {
//                bodrumKatBirimMaliyeti = it
//            }
//
//            Spacer(modifier = Modifier.height(5.dp))
//
//            CustomBasicTextField(
//                constraints = constraints,
//                entry = bodrumKatAdedi,
//                hint = "Bodrum Kat Adedini Giriniz. (ad) - opsiyonel",
//                addedSymbol = "ad",
//                inputTypeIsNumber = true
//            )
//            {
//                bodrumKatAdedi = it
//            }

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

            Spacer(modifier = Modifier.height(20.dp))

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
                                bodrumKatAdedi = bodrumKatAdedi.toIntOrNull(),
                                mevcutDaireSayisi = mevcutDaireSayisi.toIntOrNull(),
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