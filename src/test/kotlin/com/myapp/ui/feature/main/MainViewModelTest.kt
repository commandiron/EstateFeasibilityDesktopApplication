package com.arsa_fizibilite_app_by_command_.ui.feature.main

import com.github.theapache64.expekt.should
import com.arsa_fizibilite_app_by_command_.data.repo.MyRepo
import com.arsa_fizibilite_app_by_command_.test.MyDaggerMockRule
import it.cosenonjaviste.daggermock.InjectFromComponent
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val daggerMockRule = MyDaggerMockRule()

    @InjectFromComponent
    private lateinit var myRepo: MyRepo

    private val mainViewModel by lazy {
        MainViewModel(myRepo)
    }

    @Test
    fun `Button click changes the welcome text`() {
        mainViewModel.welcomeText.value.should.equal(MainViewModel.INIT_WELCOME_MSG)
        mainViewModel.onClickMeClicked()
        mainViewModel.welcomeText.value.should.equal(myRepo.getClickedWelcomeText())
    }
}