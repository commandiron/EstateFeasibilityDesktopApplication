package com.myapp.data.repo

import com.myapp.data.core.ChromeDriverSeleniumHandle
import com.myapp.data.core.eliminateTurkishCharacters
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.core.Constants.BIRIM_SATIS_FIYATI_ENDEKS_URL_1
import com.myapp.data.core.Constants.BIRIM_SATIS_FIYATI_ENDEKS_URL_2
import com.myapp.data.core.Constants.IMAR_URL
import com.myapp.data.core.Response
import com.myapp.data.core.inAppCalculationForFeasibility
import com.myapp.data.model.CalculationResult
import com.myapp.data.model.SavedCalculationDto
import com.myapp.data.util.getAmount
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import org.apache.commons.lang3.StringUtils
import org.kodein.db.DB
import org.kodein.db.OpenPolicy
import org.kodein.db.impl.open
import org.kodein.db.orm.kotlinx.KotlinxSerializer
import org.openqa.selenium.By
import org.openqa.selenium.Point
import org.openqa.selenium.support.ui.Select
import javax.inject.Inject

class MyRepo @Inject constructor() {

    init {
        try {



//            fun load(db: DB, id: String): SavedCalculationDto = db.get(db.keyById(id))!!
//
//            fun test(db: DB) {
//                val id = UUID.randomUUID().toString() // Kodein-DB provides the UUID util
//
//                val savedCalculationDto1 = SavedCalculationDto(id = id, FizibiliteModel(), CalculationResult())
//                //Store
//                store(db, savedCalculationDto1)
//                //Load
//                val savedCalculationDto2 = load(db, id)
//
//                println(savedCalculationDto2)
//            }


        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    suspend fun getArsaAlaniVeMahalleAdiWithSelenium(fizibiliteModel: FizibiliteModel): Flow<Response<FizibiliteModel>> = callbackFlow {

        this@callbackFlow.trySendBlocking(Response.Loading)

        val fizibiliteModelOutPut = fizibiliteModel

        launch(Dispatchers.IO) {

            try {
                val driver = ChromeDriverSeleniumHandle.setSelenium(isHeadless = true)

                //Variables
                var arsaAlani: Double = 0.0
                var mahalle: String = ""

                driver.get(IMAR_URL) //Sadece istanbul/kadıöy ilçesi için

                ChromeDriverSeleniumHandle.sendHumanLikeKeys("//*[@id=\"txtAdaParsel\"]","${fizibiliteModel.ada}/${fizibiliteModel.parsel}", false)
                ChromeDriverSeleniumHandle.sendHumanLikeClick("//*[@id=\"btnSearchAdaParsel\"]",false)
                ChromeDriverSeleniumHandle.sendHumanLikeClick("//*[@id=\"grid_adaParsel\"]/a",false)
                ChromeDriverSeleniumHandle.sendHumanLikeClick("//*[@id=\"btnAdaParsel\"]",false)

                val arsaAlaniSelector = driver.findElement(By.xpath("//*[@id=\"htmlOutput\"]/div[11]/div[2]/div/div/div/div[2]/b/span"))
                arsaAlani = arsaAlaniSelector.text.toString().getAmount().replace("," ,".").toDouble()
                //result

                val mahalleSelector = driver.findElement(By.xpath("//*[@id=\"htmlOutput\"]/div[11]/div[1]/div/div/div/div[2]"))
                mahalle = mahalleSelector.text.toString().eliminateTurkishCharacters().lowercase().filter { !it.isWhitespace() }
                //result

                //Result
                fizibiliteModelOutPut.projeMahalle = mahalle
                fizibiliteModelOutPut.arsaAlani = arsaAlani

                driver.quit()

                this@callbackFlow.trySendBlocking(Response.Success(fizibiliteModelOutPut))


            }catch (e:Exception){
                this@callbackFlow.trySendBlocking(Response.Error(e.localizedMessage ?: "ERROR MESSAGE"))
            }
        }

        awaitClose {
            channel.close()
            cancel()
        }
    }

    suspend fun loginWebScrapingSite(windowPositionX: Float, windowPositionY: Float): Flow<Response<Boolean>> = callbackFlow{

        this@callbackFlow.trySendBlocking(Response.Loading)

        launch(Dispatchers.IO) {

            try {
                val driver = ChromeDriverSeleniumHandle.setSelenium(windowPosition = Point(windowPositionX.toInt(),windowPositionY.toInt()))
                driver.get(BIRIM_SATIS_FIYATI_ENDEKS_URL_1)

                this@callbackFlow.trySendBlocking(Response.Success(true))

            }catch (e:Exception){
                this@callbackFlow.trySendBlocking(Response.Error(e.localizedMessage ?: "ERROR MESSAGE"))
            }
        }

        awaitClose {
            channel.close()
            cancel()
        }
    }

    suspend fun getBirimSatisFiyatiWithSelenium(fizibiliteModel: FizibiliteModel): Flow<Response<FizibiliteModel>> = callbackFlow{


        this@callbackFlow.trySendBlocking(Response.Loading)
        val fizibiliteModelOutPut = fizibiliteModel
        var mahalle = fizibiliteModel.projeMahalle

        launch(Dispatchers.IO) {

            try {
                val driver = ChromeDriverSeleniumHandle.setSelenium(windowPosition = Point(-2000,0))

                driver.get(BIRIM_SATIS_FIYATI_ENDEKS_URL_2)

                ChromeDriverSeleniumHandle.waitUntil("/html/body/div[3]/div/ul/li[4]/a",5)

                ChromeDriverSeleniumHandle.sendHumanLikeSelectAndClick(
                    "//*[@id=\"ddlReiCity\"]",
                    fizibiliteModel.projeSehir
                ) //Sehir dropdown
                ChromeDriverSeleniumHandle.sendHumanLikeSelectAndClick(
                    "//*[@id=\"ddlReiTown\"]",
                    fizibiliteModel.projeIlce
                ) //Ilce dropdown
                ChromeDriverSeleniumHandle.sendHumanLikeClick("//*[@id=\"ddlReiQuarter\"]/option[2]") //Mahalle dropdown click - focus için

                //İlk sayfadan çektiğimiz mahalleyi, buradaki formda seçmeye çalışıyoruz, eşleştirmemiz lazım.
                //Büyük harf, küçük harf, boşluk vs ayıklayarak. İlk 6 harfine bakmamız yeterli.

                val mahalleSelectorInEndeks =
                    Select(driver.findElement(By.xpath("//*[@id=\"ddlReiQuarter\"]"))) //Mahalle Dropdown seçtik.
                mahalleSelectorInEndeks.options.forEach {

                    val lowercaseSelectedOptions = it.text.eliminateTurkishCharacters().lowercase().filter { !it.isWhitespace() }
                    if (mahalle[0] == lowercaseSelectedOptions[0]) {
                        if (mahalle[1] == lowercaseSelectedOptions[1]) {
                            if (mahalle[2] == lowercaseSelectedOptions[2]) {
                                if (mahalle[3] == lowercaseSelectedOptions[3]) {
                                    if (mahalle[4] == lowercaseSelectedOptions[4]) {
                                        if (mahalle[5] == lowercaseSelectedOptions[5]) {
                                            mahalle = it.text
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                mahalleSelectorInEndeks.selectByVisibleText(mahalle) //Mahalle Seçtik.

                ChromeDriverSeleniumHandle.sendHumanLikeClick("//*[@id=\"estate360Container\"]/div/div[2]/h2[1]") //Boşluk - lose focus için
                ChromeDriverSeleniumHandle.sendHumanLikeClick("//*[@id=\"reiForm\"]/div") //Endeks button.

                ChromeDriverSeleniumHandle.waitUntil("//*[@id=\"appRoot\"]/div[13]/div[2]/div[2]/div/div[3]/div/div[2]/div[1]/div[1]/span",5)
                val selectResult =
                    driver.findElement(By.xpath("//*[@id=\"appRoot\"]/div[13]/div[2]/div[2]/div/div[3]/div/div[2]/div[1]/div[1]/span"))
                val brutAlanBirimSatisFiyati = selectResult.text.toString().getAmount().toDouble()

                //Result
                fizibiliteModelOutPut.brutAlanBirimSatisFiyati = brutAlanBirimSatisFiyati
                fizibiliteModelOutPut.projeMahalle = StringUtils.capitalize(mahalle).toString()

                this@callbackFlow.trySendBlocking(Response.Success(fizibiliteModelOutPut))

                driver.quit()
            }catch (e: Exception){
                ChromeDriverSeleniumHandle.quitDriver()
                this@callbackFlow.trySendBlocking(Response.Error(e.localizedMessage ?: "ERROR MESSAGE"))
            }
        }

        awaitClose {
            channel.close()
            cancel()
        }
    }

    suspend fun getInAppCalculationForFeasibility(fizibiliteModel: FizibiliteModel): Flow<Response<CalculationResult>> = flow {
        try {
            emit(Response.Loading)
            delay(1000)
            emit(Response.Success(inAppCalculationForFeasibility.calculate(fizibiliteModel)))
        }catch (e:Exception){
            emit(Response.Error(e.localizedMessage ?: "ERROR MESSAGE"))
        }
    }

    fun saveCalculationResult(){

        //Buradan devem dbye kayıt işlemlerini yapıyorum.

//        val dbDirectory = "C:/Users/Emir/Desktop/dbtry2"
//        val db = DB.open(dbDirectory, KotlinxSerializer(), OpenPolicy.OpenOrCreate)
//        db.put(SavedCalculationDto())
//        db.close()

        fun store(db: DB, savedCalculationDto: SavedCalculationDto) { db.put(savedCalculationDto) }
    }
}