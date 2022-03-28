package com.arsa_fizibilite_app_by_command.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.window.WindowState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.arkivanov.decompose.router.bringToFront
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.replaceCurrent
import com.arkivanov.decompose.router.router
import com.arkivanov.essenty.parcelable.Parcelable
import com.arsa_fizibilite_app_by_command.di.AppComponent
import com.arsa_fizibilite_app_by_command.di.DaggerAppComponent
import com.arsa_fizibilite_app_by_command.ui.feature.main.SecondScreenComponent
import com.arsa_fizibilite_app_by_command.ui.feature.splash.SplashScreenComponent
import com.arsa_fizibilite_app_by_command.ui.value.arsa_fizibilite_app_by_commandTheme
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.model.CalculationResult
import com.myapp.ui.feature.first.FirstScreenComponent
import com.myapp.ui.feature.fourth.FourthScreenComponent
import com.myapp.ui.feature.saved.SavedScreenComponent
import com.myapp.ui.feature.settings.SettingsScreenComponent
import com.myapp.ui.feature.third.ThirdScreenComponent

/**
 * All navigation decisions are made from here
 */
class NavHostComponent(
    private val componentContext: ComponentContext,
    private val windowState: WindowState,
) : Component, ComponentContext by componentContext {

    private val appComponent: AppComponent = DaggerAppComponent
        .create()

    /**
     * Router configuration
     */
    private val router = router<Config, Component>(
        initialConfiguration = Config.
            First
//        Third(
//            FizibiliteModel(
//            projeAdi = "Test1",
//            projeSehir = "İstanbul",
//            projeIlce = "Kadıköy",
//            projeMahalle = "Sahrayıcedit",
//            ada = "371",
//            parsel = "12",
//            arsaAlani = 1200.0,
//            brutAlanBirimSatisFiyati = 30000.0))
//        Fourth(
//            CalculationResult(
//                1500.0,
//                15000000.0,
//                1000000.0,
//                1000.0,
//                71.0,
//                500.0,
//                29.0
//
//            ),
//            FizibiliteModel(
//                projeAdi = "Test1",
//                projeSehir = "İstanbul",
//                projeIlce = "Kadıköy",
//                projeMahalle = "Sahrayıcedit",
//                ada = "371",
//                parsel = "12",
//                arsaAlani = 1200.0,
//                brutAlanBirimSatisFiyati = 30000.0
//            )
//        )
        ,
        handleBackButton = true,
        childFactory = ::createScreenComponent
    )

    /**
     * When a new navigation request made, the screen will be created by this method.
     */
    private fun createScreenComponent(config: Config, componentContext: ComponentContext): Component {

        return when (config) {
            is Config.Splash -> SplashScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext
            )
            is Config.Saved -> SavedScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext
            )
            is Config.First -> FirstScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                firstToSecondScreen = ::firstToSecondScreen
            )
            is Config.Second -> SecondScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                fizibiliteModel = config.fizibiliteModel,
                secondToThirdScreen = ::secondToThirdScreen,
                windowState = windowState
            )
            is Config.Third -> ThirdScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                fizibiliteModel = config.fizibiliteModel,
                thirdToFourthScreen = ::thirdToFourthScreen
            )
            is Config.Fourth -> FourthScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                calculationResult = config.calculationResult,
                fizibiliteModel = config.fizibiliteModel
            )
            is Config.Settings -> SettingsScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext
            )
            null -> SplashScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext
            )
        }
    }

    private fun firstToSecondScreen(fizibiliteModel: FizibiliteModel) {
        router.replaceCurrent(Config.Second(fizibiliteModel))
    }

    private fun secondToThirdScreen(fizibiliteModel: FizibiliteModel) {
        router.replaceCurrent(Config.Third(fizibiliteModel))
    }

    private fun thirdToFourthScreen(calculationResult: CalculationResult, fizibiliteModel: FizibiliteModel) {
        router.replaceCurrent(Config.Fourth(calculationResult, fizibiliteModel))
    }

    private fun bottomNavigate(toScreenTitle: String){
        when(toScreenTitle){
            Config.Saved.title -> router.replaceCurrent(Config.Saved)
            Config.First.title -> router.replaceCurrent(Config.First)
            Config.Settings.title -> router.replaceCurrent(Config.Settings)
        }
    }

    /**
     * Available screensSelectApp
     */
    sealed class Config(var title:String, var icon: ImageVector) : Parcelable {
        //Splash
        object Splash : Config("Hesaplama Yap", Icons.Filled.Home)
        //Navigation title: Kaydedilenler
        object Saved : Config("Kaydedilenler", Icons.Filled.List)
        //Navigation title: Hesaplama yap
        object First : Config("Hesaplama Yap", Icons.Filled.Home)
        data class Second(val fizibiliteModel: FizibiliteModel) : Config("Second", Icons.Filled.Home)
        data class Third(val fizibiliteModel: FizibiliteModel) : Config("Third", Icons.Filled.Home)
        data class Fourth(val calculationResult: CalculationResult, val fizibiliteModel: FizibiliteModel) : Config("Fourth", Icons.Filled.Home)
        //Navigation title: Ayarlar
        object Settings : Config("Ayarlar", Icons.Filled.Settings)
    }

    @Composable
    override fun render() {

        var currentRoute by remember { mutableStateOf("") }

        arsa_fizibilite_app_by_commandTheme(currentRoute = currentRoute, onBottomNavItemClick = {
            bottomNavigate(it)
        }) {
            Children(
                routerState = router.state,
                animation = crossfadeScale()
            ) { child ->
                currentRoute = child.configuration.title
                println(currentRoute)
                child.instance.render()
            }
        }
    }
}