package com.myapp.data.usecase

import javax.inject.Inject

data class UseCases @Inject constructor(
    val getArsaVerileriWithSelenium: GetArsaVerileriWithSelenium,
) {
}