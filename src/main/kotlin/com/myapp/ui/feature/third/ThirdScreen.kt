package com.myapp.ui.feature.third


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arsa_fizibilite_app_by_command.ui.value.R
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.model.CalculationResult
import com.myapp.ui.feature.components.CustomLinearProgressIndicator
import com.myapp.ui.feature.components.HoverTextState
import com.myapp.ui.feature.second.CustomBasicTextField

@Composable
fun ThirdScreen(
    thirdViewModel: ThirdViewModel,
    fizibiliteModelFromSecondScreen: FizibiliteModel,
    thirdToFourthScreen:(CalculationResult, FizibiliteModel)  -> Unit,
) {

    //Model from second screen
    var fizibiliteModel by remember { mutableStateOf(FizibiliteModel()) }
    fizibiliteModel = fizibiliteModelFromSecondScreen

    //Proje bilgileri
    val projeAdi by remember { mutableStateOf(fizibiliteModel.projeAdi) }
    val projeSehir by remember { mutableStateOf(fizibiliteModel.projeSehir) }
    val projeIlce by remember { mutableStateOf(fizibiliteModel.projeIlce) }
    val projeMahalle by remember { mutableStateOf(fizibiliteModel.projeMahalle) }
    val ada by remember { mutableStateOf(fizibiliteModel.ada) }
    val parsel by remember { mutableStateOf(fizibiliteModel.parsel) }

    //Hesaplama için (zorunlu)
    var arsaAlani by remember { mutableStateOf(fizibiliteModel.arsaAlani.toString()) }
    var insaatBirimMaliyeti by remember { mutableStateOf(fizibiliteModel.insaatBirimMaliyeti.toString()) }
    var brutAlanBirimSatisFiyati by remember { mutableStateOf(fizibiliteModel.brutAlanBirimSatisFiyati.toString()) }
    var hedefKarOrani by remember { mutableStateOf(fizibiliteModel.hedefKarOrani.times(100).toString()) }

    //HoverText
    var hoverTextStateFirst: HoverTextState by remember{ mutableStateOf(HoverTextState()) }
    var hoverTextStateSecond: HoverTextState by remember{ mutableStateOf(HoverTextState()) }

    //Loading
    val isLoading by remember {thirdViewModel.isLoading }
    val duration by remember{thirdViewModel.progressIndicatorDuration}

    val fizibiliteModelFromCalculation by remember { thirdViewModel.fizibiliteModelFromCalculation }
    LaunchedEffect(fizibiliteModelFromCalculation){
        if(fizibiliteModelFromCalculation != null){
            thirdToFourthScreen(fizibiliteModelFromCalculation!!,fizibiliteModel)
        }
    }

    //Compose Components
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val constraints = this.constraints

        if(isLoading){
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(20.dp))
                Text("Hesaplama Yapılıyor. Lütfen bekleyiniz.")
                Spacer(modifier = Modifier.height(20.dp))
                CustomLinearProgressIndicator(duration)
            }
        }else{
            if(hoverTextStateFirst.isCurserOverInfo){
                Card(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(
                            start = Dp(hoverTextStateFirst.positionX + 40),
                            top = Dp(hoverTextStateFirst.positionY - 56))) {
                    Text(text = "Bu değer internetten çekilmiştir.",fontSize = 10.sp, modifier = Modifier.padding(4.dp), color = Color.Black)
                }
            }
            if(hoverTextStateSecond.isCurserOverInfo){
                Card(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(
                            start = Dp(hoverTextStateSecond.positionX + 40),
                            top = Dp(hoverTextStateSecond.positionY - 56))) {
                    Text(text = "Bu değer internetten çekilmiştir.",fontSize = 10.sp, modifier = Modifier.padding(4.dp), color = Color.Black)
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
                        Column(modifier = Modifier.padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Row{
                                Text("Şehir: $projeSehir - ")
                                Text("İlçe: $projeIlce - ")
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
                    println("1" + it)
                    hedefKarOrani = it
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

                            fizibiliteModel.arsaAlani = arsaAlani.toDouble()
                            fizibiliteModel.insaatBirimMaliyeti = insaatBirimMaliyeti.toDouble()
                            fizibiliteModel.brutAlanBirimSatisFiyati = brutAlanBirimSatisFiyati.toDouble()
                            fizibiliteModel.hedefKarOrani = hedefKarOrani.toDouble()/100

                            thirdViewModel.calculation(fizibiliteModel)
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
}