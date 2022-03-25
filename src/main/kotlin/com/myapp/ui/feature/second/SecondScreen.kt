package com.arsa_fizibilite_app_by_command.ui.feature.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import com.arsa_fizibilite_app_by_command.ui.value.R
import com.arsa_fizibilite_app_by_command.ui.value.R.string.TEXT_SECOND_LOGIN_OLAMADINIZ
import com.arsa_fizibilite_app_by_command.ui.value.R.string.TEXT_SECOND_LUTFEN_LOGIN_OLUNUZ
import com.myapp.data.model.FizibiliteModel
import com.myapp.ui.feature.components.CustomLinearProgressIndicator
import com.myapp.ui.feature.second.SecondViewModel
import java.awt.Point

@Composable
fun SecondScreen(
    secondViewModel: SecondViewModel,
    fizibiliteModelFromFirstCal: FizibiliteModel,
    secondToThirdScreen:(FizibiliteModel)  -> Unit,
    windowState: WindowState
) {
    fizibiliteModelFromFirstCal //-> Burda Arsa Alanını ve mahalleyi çektik.
    //şimdi siteye kullanıcıyı login ettiriceğiz.
    //Ordan brüt fiyatı çekeceğiz.
    //Ordan üçüncü sayfaya geçeçeğiz.

    var fizibiliteModelFromWebCraping by remember { mutableStateOf(FizibiliteModel())}
    fizibiliteModelFromWebCraping = secondViewModel.fizibiliteModelFromInternet.value

    val isLoading by remember {secondViewModel.isLoading }

    val dataIsLoaded by remember {secondViewModel.dataIsLoaded}
    LaunchedEffect(dataIsLoaded){
        if(dataIsLoaded){
            secondToThirdScreen(fizibiliteModelFromWebCraping)
        }
    }

    val isErrorHappen by remember {secondViewModel.isErrorHappen }
    LaunchedEffect(isErrorHappen){
        if(isErrorHappen){
            secondViewModel.loginWebScrapingSite(windowState.position, windowState.size)
        }
    }

    LaunchedEffect(Unit){
        secondViewModel.loginWebScrapingSite(windowState.position, windowState.size)
    }

    val duration by remember{secondViewModel.progressIndicatorDuration}

    BoxWithConstraints (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {

        if(isLoading){
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(20.dp))
                Text("Veriler çekiliyor. Lütfen bekleyiniz.")
                Spacer(modifier = Modifier.height(20.dp))
                CustomLinearProgressIndicator(duration)
            }
        }else{
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(modifier = Modifier.height(40.dp))

                CircularProgressIndicator()

                Spacer(modifier = Modifier.height(40.dp))

                if(isErrorHappen){
                    Text(text = TEXT_SECOND_LOGIN_OLAMADINIZ, color = Color.Red)
                    Spacer(modifier = Modifier.height(40.dp))
                }

                Text(TEXT_SECOND_LUTFEN_LOGIN_OLUNUZ)

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        secondViewModel.getBirimSatisFiyatiWithSelenium(fizibiliteModelFromFirstCal)
                    }
                ) {
                    Text(text = R.string.ACTION_SECOND_ILERI)
                }
            }
        }
    }
}