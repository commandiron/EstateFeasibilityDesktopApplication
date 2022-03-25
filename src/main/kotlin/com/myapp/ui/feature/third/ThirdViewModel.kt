package com.myapp.ui.feature.third

import kotlinx.coroutines.flow.collect
import com.arsa_fizibilite_app_by_command.util.ViewModel
import com.myapp.data.usecase.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class ThirdViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    var isLoading = MutableStateFlow(false)
        private set
}