package com.myapp.ui.feature.fourth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arsa_fizibilite_app_by_command.ui.value.R
import com.myapp.data.model.CalculationResult
import com.myapp.data.model.FizibiliteModel
import com.myapp.util.FormatNumbers
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.io.File
import javax.imageio.ImageIO

@Composable
fun FourthScreen(
    fourthViewModel: FourthViewModel,
    calculationResultFromThirdScreen: CalculationResult,
    fizibiliteModelFromThirdScreen: FizibiliteModel,
    clickedItemIdFromSavedScreen: String,
    fourthToFirstScreen:()  -> Unit,
) {
    //Model for view
    var fizibiliteModel by remember { mutableStateOf(FizibiliteModel()) }
    var calculationResult by remember { mutableStateOf(CalculationResult()) }

    LaunchedEffect(Unit){
        if(clickedItemIdFromSavedScreen.isNotEmpty()){
            fourthViewModel.loadSingleCalculation(clickedItemIdFromSavedScreen)
        }else{
            fizibiliteModel = fizibiliteModelFromThirdScreen
            calculationResult = calculationResultFromThirdScreen
            println("From Third Screen: " + fizibiliteModel)
        }
    }

    val loadCalculation = fourthViewModel.savedCalculationFromSavedScreen.value
    LaunchedEffect(loadCalculation){
        if(loadCalculation != null){
            fizibiliteModel = loadCalculation.fizibiliteModel
            calculationResult = loadCalculation.calculationResult
            println("FromSavedScreen: " + fizibiliteModel)
        }
    }

    //Proje bilgileri
    var projeAdi by remember { mutableStateOf(fizibiliteModel.projeAdi) }
    var projeSehir by remember { mutableStateOf(fizibiliteModel.projeSehir) }
    var projeIlce by remember { mutableStateOf(fizibiliteModel.projeIlce) }
    var projeMahalle by remember { mutableStateOf(fizibiliteModel.projeMahalle) }
    var ada by remember { mutableStateOf(fizibiliteModel.ada) }
    var parsel by remember { mutableStateOf(fizibiliteModel.parsel) }
    var imagePath by remember { mutableStateOf(fizibiliteModel.imagePath) }

    //Hesaplama için (zorunlu)
    var arsaAlani by remember { mutableStateOf(fizibiliteModel.arsaAlani) }
    var insaatBirimMaliyeti by remember { mutableStateOf(fizibiliteModel.insaatBirimMaliyeti) }
    var brutAlanBirimSatisFiyati by remember { mutableStateOf(fizibiliteModel.brutAlanBirimSatisFiyati) }
    var hedefKarOrani by remember { mutableStateOf(fizibiliteModel.hedefKarOrani) }

    //Sonuc
    var toplamBrutAlan by remember { mutableStateOf(calculationResult.toplamBrutAlan) }
    var toplamMaliyet  by remember { mutableStateOf(calculationResult.toplamMaliyet) }
    var hedefGelir by remember { mutableStateOf(calculationResult.hedefGelir) }
    var muteahhitBrutAlan by remember { mutableStateOf(calculationResult.muteahhitBrutAlan) }
    var mutaahhitYuzde by remember { mutableStateOf(calculationResult.mutaahhitYuzde) }
    var katMalikleriBrutAlan by remember { mutableStateOf(calculationResult.katMalikleriBrutAlan) }
    var katMalikleriYuzde by remember { mutableStateOf(calculationResult.katMalikleriYuzde) }

    //Daha iyi bir yöntem bulabilirim buna bakacağım.
    LaunchedEffect(fizibiliteModel){
        projeAdi = fizibiliteModel.projeAdi
        projeSehir = fizibiliteModel.projeSehir
        projeIlce = fizibiliteModel.projeIlce
        projeMahalle = fizibiliteModel.projeMahalle
        ada = fizibiliteModel.ada
        parsel = fizibiliteModel.parsel
        imagePath = fizibiliteModel.imagePath
        arsaAlani = fizibiliteModel.arsaAlani
        insaatBirimMaliyeti = fizibiliteModel.insaatBirimMaliyeti
        brutAlanBirimSatisFiyati= fizibiliteModel.brutAlanBirimSatisFiyati
        hedefKarOrani = fizibiliteModel.hedefKarOrani
        toplamBrutAlan = calculationResult.toplamBrutAlan
        toplamMaliyet  = calculationResult.toplamMaliyet
        hedefGelir = calculationResult.hedefGelir
        muteahhitBrutAlan = calculationResult.muteahhitBrutAlan
        mutaahhitYuzde = calculationResult.mutaahhitYuzde
        katMalikleriBrutAlan = calculationResult.katMalikleriBrutAlan
        katMalikleriYuzde = calculationResult.katMalikleriYuzde
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 5.dp,
                contentColor = Color.Black
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(4.dp)) {
                    Text("$projeAdi")
                }
            }

            Row(modifier = Modifier.weight(5f).padding(10.dp)) {
                Card(
                    modifier = Modifier.weight(1f),
                    elevation = 5.dp,
                    contentColor = Color.Black
                ) {
                    if(imagePath.isNotEmpty()){
                        val file = File(imagePath)
                        val imageBitmap: ImageBitmap = remember(file) {loadImageBitmap(file.inputStream())}

                        Image(
                            painter = BitmapPainter(image = imageBitmap),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(1f).padding(start = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        elevation = 5.dp,
                        contentColor = Color.Black
                    ) {
                        Column(
                            modifier = Modifier.padding(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "PROJE BİLGİLERİ",
                                style = TextStyle(
                                    textDecoration = TextDecoration.Underline,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp))

                            Spacer(modifier = Modifier.height(4.dp))

                            Text("Sehir: $projeSehir ")
                            Text("İlce: $projeIlce ")
                            Text("Mahalle: $projeMahalle ")
                            Text("Ada: $ada ")
                            Text("Parsel: $parsel")
                        }
                    }

                    Spacer(Modifier.height(10.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        elevation = 5.dp,
                        contentColor = Color.Black
                    ) {
                        Column(
                            modifier = Modifier.padding(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {
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
                }
            }

            Card(
                modifier = Modifier.padding(4.dp).weight(5f),
                elevation = 5.dp,
                contentColor = Color.Black
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "FİZİBİLİTE SONUCU",
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp))

                    Spacer(modifier = Modifier.height(10.dp))

                    Text("Toplam Brüt Alan: ${FormatNumbers.formatNumber(toplamBrutAlan, "m²")}")
                    Text("Toplam Maliyet: ${FormatNumbers.formatNumber(toplamMaliyet,"₺")}")
                    Text("Hedef Gelir: ${FormatNumbers.formatNumber(hedefGelir,"₺")}")
                    Text("Muteahhit Brüt Alan: ${FormatNumbers.formatNumber(muteahhitBrutAlan,"m²" )}")
                    Text("Mutaahhit Yüzde: ${FormatNumbers.formatNumber(mutaahhitYuzde,"%")}")
                    Text("Kat Malikleri Brüt Alan: ${FormatNumbers.formatNumber(katMalikleriBrutAlan,"m²")}")
                    Text("Kat Malikleri Yüzde: ${FormatNumbers.formatNumber(katMalikleriYuzde,"%")}")
                }
            }
            Row() {
                Box(
                    modifier = Modifier.weight(1f).padding(start = 10.dp),
                    contentAlignment = Alignment.CenterStart){
                    Button(
                        modifier = Modifier.width(100.dp),
                        onClick = {
                            fourthToFirstScreen()
                        }
                    ) {
                        Text(text = R.string.ACTION_FOURTH_GERI_DON)
                    }
                }

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center){
                    Button(
                        modifier = Modifier.width(200.dp),
                        onClick = {
                            fourthViewModel.saveCalculation(fizibiliteModel,calculationResult)
                        }
                    ) {
                        Text(text = R.string.ACTION_FOURTH_KAYDET)
                    }
                }

                Box(
                    modifier = Modifier.weight(1f).padding(end = 10.dp),
                    contentAlignment = Alignment.CenterEnd){
                    Button(
                        modifier = Modifier.width(100.dp),
                        onClick = {

                            val screenRect = Rectangle(Toolkit.getDefaultToolkit().screenSize)
                            val capture = Robot().createScreenCapture(screenRect)

                            val path = "C:/Users/Emir/Desktop/FizibiliteUygulamasi/test123.png"
                            val destFile = File(path)
                            ImageIO.write(capture,"png",destFile)

                            //Burda windowun ölçülerini ve positionunu alarak ss alacağız ve kaydedeceğiz, burada kaldım,
                            //Bunu viewmodelde yapacağız.

                        }
                    ) {
                        Text(text = R.string.ACTION_FOURTH_CIKTI_AL)
                    }
                }
            }
        }
    }
}