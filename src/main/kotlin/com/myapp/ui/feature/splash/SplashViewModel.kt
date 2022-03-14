package com.arsa_fizibilite_app_by_command.ui.feature.splash

import com.arsa_fizibilite_app_by_command.data.repo.MyRepo
import com.arsa_fizibilite_app_by_command.util.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val myRepo: MyRepo,
    // Inject your repos here...
) : ViewModel() {

    companion object {
        const val SPLASH_DELAY = 2000L // 2 seconds of splash delay
    }

    private val _isSplashFinished = MutableStateFlow(false)
    val isSplashFinished: StateFlow<Boolean> = _isSplashFinished

    override fun init(viewModelScope: CoroutineScope) {
        super.init(viewModelScope)

        viewModelScope.launch {
            delay(SPLASH_DELAY)
            _isSplashFinished.value = true
        }
    }
}