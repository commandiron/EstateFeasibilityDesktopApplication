package com.arsa_fizibilite_app_by_command.util

import kotlinx.coroutines.CoroutineScope

open class ViewModel {

    lateinit var viewModelScope: CoroutineScope

    open fun init(viewModelScope: CoroutineScope) {
        this.viewModelScope = viewModelScope
    }
}