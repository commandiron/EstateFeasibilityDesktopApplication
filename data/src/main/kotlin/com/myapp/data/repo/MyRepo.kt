package com.arsa_fizibilite_app_by_command.data.repo

import javax.inject.Inject

class MyRepo @Inject constructor() {
    fun getClickedWelcomeText() = "Hello Desktop!"
}