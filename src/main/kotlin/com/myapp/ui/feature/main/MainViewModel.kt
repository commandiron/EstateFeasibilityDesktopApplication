package com.arsa_fizibilite_app_by_command.ui.feature.main

import com.arsa_fizibilite_app_by_command.data.repo.MyRepo
import com.arsa_fizibilite_app_by_command.util.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val myRepo: MyRepo,
    // Inject your repos here...
) : ViewModel() {
    companion object {
        const val INIT_WELCOME_MSG = "Hello World!"
    }

    private val _welcomeText = MutableStateFlow(INIT_WELCOME_MSG)
    val welcomeText: StateFlow<String> = _welcomeText

    fun onClickMeClicked() {
        _welcomeText.value = myRepo.getClickedWelcomeText()
    }
}