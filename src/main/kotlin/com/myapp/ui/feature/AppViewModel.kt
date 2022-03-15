package com.myapp.ui.feature

import androidx.compose.runtime.mutableStateOf
import com.arsa_fizibilite_app_by_command.data.repo.MyRepo
import com.arsa_fizibilite_app_by_command.util.ViewModel
import com.myapp.model.FizibiliteModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val myRepo: MyRepo,
    // Inject your repos here...
) : ViewModel() {

    var arsaAlani = mutableStateOf("")
        private set

    var insaatBirimMaliyeti = mutableStateOf("")
        private set

    var brutAlanBirimSatisFiyati = mutableStateOf("")
        private set

    var hedefKarOrani = mutableStateOf("15")
        private set

    var bodrumKatAlani = mutableStateOf("0")
        private set

    var bodrumKatBirimMaliyeti = mutableStateOf("0")
        private set

    var bodrumKatAdedi = mutableStateOf("0")
        private set

    var mevcutDaireSayisi = mutableStateOf("0")
        private set

    fun onButtonClicked() {
    }
}