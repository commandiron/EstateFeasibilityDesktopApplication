package com.myapp.ui.feature.first

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.myapp.ui.feature.AppViewModel
import com.arsa_fizibilite_app_by_command.ui.value.R
import com.myapp.ui.feature.second.CustomBasicTextField

@Composable
fun FirstScreen(
    viewModel: AppViewModel,
    onButtonClicked: () -> Unit
) {

    var projeAdi by remember { mutableStateOf("") }
    var projeSehir by remember { mutableStateOf("İstanbul") }
    var projeIlce by remember { mutableStateOf("Kadıköy") }
    var ada by remember { mutableStateOf("") }
    var parsel by remember { mutableStateOf("") }

    BoxWithConstraints (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val constraints = this.constraints

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                constraints = constraints,
                entry = projeAdi,
                hint = "Proje Adını Giriniz"){
                projeAdi = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                readOnly = true,
                constraints = constraints,
                entry = projeSehir,
                hint = "Şehir Giriniz"){
                projeSehir = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                readOnly = true,
                constraints = constraints,
                entry = projeIlce,
                hint = "İlçe Giriniz"){
                projeIlce = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                constraints = constraints,
                entry = ada,
                hint = "Ada no Giriniz")
            {
                ada = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomBasicTextField(
                constraints = constraints,
                entry = parsel,
                hint = "Parsel no Giriniz")
            {
                parsel = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    onButtonClicked()
                }
            ) {
                Text(text = R.string.ACTION_FIRST_SONRAKI_ADIM)

            }
        }
    }
}