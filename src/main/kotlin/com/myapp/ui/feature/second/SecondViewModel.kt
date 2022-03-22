package com.myapp.ui.feature.second

import com.arsa_fizibilite_app_by_command.util.ViewModel
import com.myapp.data.usecase.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class SecondViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    var isLoading = MutableStateFlow(false)
        private set
}