package com.myapp.ui.feature.fourth

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.data.model.CalculationResult
import com.myapp.data.model.FizibiliteModel
import com.myapp.util.FormatNumbers

@Composable
fun FourthScreen(
    fourthViewModel: FourthViewModel,
    calculationResultFromThirdScreen: CalculationResult,
    fizibiliteModelFromThirdScreen: FizibiliteModel
) {

    //Model from third screen
    var fizibiliteModel by remember { mutableStateOf(FizibiliteModel()) }
    fizibiliteModel = fizibiliteModelFromThirdScreen
    var calculationResult by remember { mutableStateOf(CalculationResult()) }
    calculationResult = calculationResultFromThirdScreen

    //Proje bilgileri
    val projeAdi by remember { mutableStateOf(fizibiliteModel.projeAdi) }
    val projeSehir by remember { mutableStateOf(fizibiliteModel.projeSehir) }
    val projeIlce by remember { mutableStateOf(fizibiliteModel.projeIlce) }
    val projeMahalle by remember { mutableStateOf(fizibiliteModel.projeMahalle) }
    val ada by remember { mutableStateOf(fizibiliteModel.ada) }
    val parsel by remember { mutableStateOf(fizibiliteModel.parsel) }

    //Hesaplama için (zorunlu)
    val arsaAlani by remember { mutableStateOf(fizibiliteModel.arsaAlani) }
    val insaatBirimMaliyeti by remember { mutableStateOf(fizibiliteModel.insaatBirimMaliyeti) }
    val brutAlanBirimSatisFiyati by remember { mutableStateOf(fizibiliteModel.brutAlanBirimSatisFiyati) }
    val hedefKarOrani by remember { mutableStateOf(fizibiliteModel.hedefKarOrani) }

    //Sonuc
    var toplamBrutAlan by remember { mutableStateOf(calculationResult.toplamBrutAlan) }
    var toplamMaliyet  by remember { mutableStateOf(calculationResult.toplamMaliyet) }
    var hedefGelir by remember { mutableStateOf(calculationResult.hedefGelir) }
    var muteahhitBrutAlan by remember { mutableStateOf(calculationResult.muteahhitBrutAlan) }
    var mutaahhitYuzde by remember { mutableStateOf(calculationResult.mutaahhitYuzde) }
    var katMalikleriBrutAlan by remember { mutableStateOf(calculationResult.katMalikleriBrutAlan) }
    var katMalikleriYuzde by remember { mutableStateOf(calculationResult.katMalikleriYuzde) }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 5.dp,
                    contentColor = Color.Black
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(4.dp)) {
                        Text("$projeAdi")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier.padding(4.dp),
                    elevation = 5.dp,
                    contentColor = Color.Black
                ) {
                    Column(modifier = Modifier.padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "PROJE BİLGİLERİ",
                            style = TextStyle(
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp))

                        Spacer(modifier = Modifier.height(4.dp))

                        Text("Sehir: $projeSehir")
                        Text("İlce: $projeIlce")
                        Text("Mahalle: $projeMahalle")
                        Text("Ada: $ada")
                        Text("Parsel: $parsel")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier.padding(4.dp),
                    elevation = 5.dp,
                    contentColor = Color.Black
                ) {
                    Column(modifier = Modifier.padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "KULLANILAN PARAMETRELER",
                            style = TextStyle(
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp))

                        Spacer(modifier = Modifier.height(4.dp))

                        Text("Arsa Alani: ${FormatNumbers.formatNumber(arsaAlani,"m²")}")
                        Text("İnsaat Birim Maliyeti: ${FormatNumbers.formatNumber(insaatBirimMaliyeti,"₺")}")
                        Text("Brüt Alan Birim Satış Fiyatı: ${FormatNumbers.formatNumber(brutAlanBirimSatisFiyati,"₺")}")
                        Text("Hedef Kar Oranı: ${FormatNumbers.formatNumber(hedefKarOrani,"%",1)}")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier.padding(4.dp),
                    elevation = 5.dp,
                    contentColor = Color.Black
                ) {
                    Column(modifier = Modifier.padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "HESAP SONUCU",
                            style = TextStyle(
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp))

                        Spacer(modifier = Modifier.height(4.dp))

                        Text("Toplam Brüt Alan: ${FormatNumbers.formatNumber(toplamBrutAlan, "m²")}")
                        Text("Toplam Maliyet: ${FormatNumbers.formatNumber(toplamMaliyet,"₺")}")
                        Text("Hedef Gelir: ${FormatNumbers.formatNumber(hedefGelir,"₺")}")
                        Text("Muteahhit Brüt Alan: ${FormatNumbers.formatNumber(muteahhitBrutAlan,"m²" )}")
                        Text("Mutaahhit Yüzde: ${FormatNumbers.formatNumber(mutaahhitYuzde,"%")}")
                        Text("Kat Malikleri Brüt Alan: ${FormatNumbers.formatNumber(katMalikleriBrutAlan,"m²")}")
                        Text("Kat Malikleri Yüzde: ${FormatNumbers.formatNumber(katMalikleriYuzde,"%")}")
                    }
                }
            }
        }
    }

}