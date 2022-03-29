package com.myapp.ui.feature.first

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arsa_fizibilite_app_by_command.ui.value.R
import com.myapp.data.model.FizibiliteModel
import com.myapp.ui.feature.components.CustomLinearProgressIndicator
import com.myapp.ui.feature.second.CustomBasicTextField

@Composable
fun FirstScreen(
    firstViewModel: FirstViewModel,
    firstToSecondScreen:(FizibiliteModel)  -> Unit
) {

    var projeAdi by remember { mutableStateOf("Proje Fizibilite - 1") }
    var projeSehir by remember { mutableStateOf("İstanbul") } //Uygulama şu anlık şehir değiştirmeyi desteklemiyor.
    var projeIlce by remember { mutableStateOf("Kadıköy") } //Uygulama şu anlık ilçe değiştirmeyi desteklemiyor.
    var ada by remember { mutableStateOf("347") }
    var parsel by remember { mutableStateOf("1")}

    val fizibiliteModelFromWebScraping by remember { firstViewModel.fizibiliteModelFromInternet}

    val isLoading by remember {firstViewModel.isLoading}

    LaunchedEffect(fizibiliteModelFromWebScraping){
        if(fizibiliteModelFromWebScraping != null){
            firstToSecondScreen(fizibiliteModelFromWebScraping!!)
        }
    }

    val isErrorHappen by remember {firstViewModel.isErrorHappen }

    val duration by remember{firstViewModel.progressIndicatorDuration}

    BoxWithConstraints (
        modifier = Modifier.fillMaxSize().focusable(enabled = true),
        contentAlignment = Alignment.Center
    ) {

        if(isLoading){
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(20.dp))
                Text("Veriler çekiliyor. Lütfen bekleyiniz.")
                Spacer(modifier = Modifier.height(20.dp))
                CustomLinearProgressIndicator(duration)
            }
        }else{
            val constraints = this.constraints

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(14.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 5.dp,
                    contentColor = Color.Black
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(4.dp)) {
                        Text("HESAPLAMAYA BAŞLA")
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                CustomBasicTextField(
                    textIsEmptyError = projeAdi.isEmpty(),
                    constraints = constraints,
                    entry = projeAdi,
                    hint = "Proje Adını Giriniz"){
                    projeAdi = it
                }

                Spacer(modifier = Modifier.height(10.dp))

                CustomBasicTextField(
                    textIsEmptyError = projeSehir.isEmpty(),
                    readOnly = true,
                    constraints = constraints,
                    entry = projeSehir,
                    hint = "Şehir Giriniz"){
                    projeSehir = it
                }

                Spacer(modifier = Modifier.height(10.dp))

                CustomBasicTextField(
                    textIsEmptyError = projeIlce.isEmpty(),
                    readOnly = true,
                    constraints = constraints,
                    entry = projeIlce,
                    hint = "İlçe Giriniz"){
                    projeIlce = it
                }

                Spacer(modifier = Modifier.height(10.dp))

                CustomBasicTextField(
                    textIsEmptyError = ada.isEmpty(),
                    constraints = constraints,
                    entry = ada,
                    hint = "Ada no Giriniz")
                {
                    ada = it
                }

                Spacer(modifier = Modifier.height(10.dp))

                CustomBasicTextField(
                    textIsEmptyError = parsel.isEmpty(),
                    constraints = constraints,
                    entry = parsel,
                    hint = "Parsel no Giriniz")
                {
                    parsel = it
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = {
                        if(
                            projeAdi.isNotEmpty() &&
                            projeSehir.isNotEmpty() &&
                            projeIlce.isNotEmpty() &&
                            ada.isNotEmpty() &&
                            parsel.isNotEmpty()){

                            firstViewModel.getArsaAlaniVeMahalleAdiWithSelenium(
                                FizibiliteModel(
                                    projeAdi = projeAdi,
                                    projeSehir = projeSehir,
                                    projeIlce = projeIlce,
                                    ada = ada,
                                    parsel = parsel)
                            )

                        }else{
                            println("Boş olan kutucukları doldurunuz.")
                        }
                    }
                ) {
                    Text(text = R.string.ACTION_FIRST_SONRAKI_ADIM)
                }

                if(isErrorHappen){
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(text = R.string.TEXT_FIRST_HATA, color = Color.Red)
                }
            }
        }
    }
}