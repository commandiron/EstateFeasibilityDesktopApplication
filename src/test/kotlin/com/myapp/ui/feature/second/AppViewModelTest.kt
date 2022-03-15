package com.arsa_fizibilite_app_by_command.ui.feature.main

import com.github.theapache64.expekt.should
import com.arsa_fizibilite_app_by_command.data.repo.MyRepo
import com.arsa_fizibilite_app_by_command.test.MyDaggerMockRule
import com.myapp.ui.feature.AppViewModel
import it.cosenonjaviste.daggermock.InjectFromComponent
import org.junit.Rule
import org.junit.Test

class AppViewModelTest {

    @get:Rule
    val daggerMockRule = MyDaggerMockRule()

    @InjectFromComponent
    private lateinit var myRepo: MyRepo

    private val appViewModel by lazy {
        AppViewModel(myRepo)
    }

    @Test
    fun `Button click changes the welcome text`() {
        appViewModel.welcomeText.value.should.equal(AppViewModel.INIT_WELCOME_MSG)
        appViewModel.onClickMeClicked()
        appViewModel.welcomeText.value.should.equal(myRepo.getClickedWelcomeText())
    }
}