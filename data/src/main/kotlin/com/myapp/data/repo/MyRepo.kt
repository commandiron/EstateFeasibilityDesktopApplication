package com.myapp.data.repo

import com.myapp.data.RandomUserAgent
import com.myapp.data.model.FizibiliteModel
import com.myapp.data.util.Constants.BIRIM_SATIS_FIYATI_ENDEKS_URL
import com.myapp.data.util.Constants.IMAR_URL
import com.myapp.data.util.Response
import com.myapp.data.util.getAmount
import io.github.bonigarcia.wdm.WebDriverManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi.EC
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.devtools.idealized.Network.UserAgent
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.util.*
import javax.inject.Inject
import kotlin.math.log

class MyRepo @Inject constructor() {

    suspend fun getArsaVerileriWithSelenium(fizibiliteModel: FizibiliteModel): Flow<Response<FizibiliteModel>> = channelFlow {

        val fizibiliteModelOutPut = fizibiliteModel

        try {
            send(Response.Loading)

            withContext(Dispatchers.IO) {

                //Webdriver
                System.setProperty("webdriver.chrome.driver", "data/src/main/kotlin/com/myapp/data/drivers/chromedriver.exe");
                WebDriverManager.chromedriver().setup() //Bilgisayarda chrome yoksa çalışmıyor, çünkü onun sadece onun driver'ını gömdüm.

                //Chrome Options
                val options = ChromeOptions()

                // Fixing 255 Error crashes
                options.addArguments("--no-sandbox")
                options.addArguments("--disable-dev-shm-usage")

                // Options to trick bot detection
                // Removing webdriver property
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                options.setExperimentalOption("useAutomationExtension", null);

                //Headless browser
                //options.setHeadless(true)

                // Changing the user agent
                val userAgent = UserAgent(RandomUserAgent().getRandomUserAgent())
                options.addArguments("user-agent=${userAgent}")

                // Other
                options.addArguments("disable-infobars")

                //Chrome Driver
                val driver: WebDriver = ChromeDriver(options)
                driver.manage().window().position = Point(-2000,0) //Browserı gözükmeyecek bir yerde başlattık.
                driver.manage().window().size = Dimension(1200,768)

                //Wait For Element
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000))

                //Selenium Functions
                suspend fun sendHumanLikeKeys(xPathExpression: String, text: String, botProtection: Boolean = true){

                    if(botProtection){
                        val randomRangeForSelect = ((1000..2000))
                        val randomRangeForKeys = ((100..800))
                        delay(randomRangeForSelect.random().toLong())
                        val select = driver.findElement(By.xpath(xPathExpression))
                        delay(randomRangeForSelect.random().toLong())
                        for(i in text){
                            delay(randomRangeForKeys.random().toLong())
                            select.sendKeys(i.toString())
                        }
                    }else{
                        val select = driver.findElement(By.xpath(xPathExpression))
                        select.sendKeys(text)
                    }
                }

                suspend fun sendHumanLikeClick(xPathExpression: String, botProtection: Boolean = true){
                    if(botProtection){
                        val randomRangeForSelect = ((1000..2000))
                        delay(randomRangeForSelect.random().toLong())
                        val select = driver.findElement(By.xpath(xPathExpression))
                        delay(randomRangeForSelect.random().toLong())
                        if(!select.size.equals(0)){
                            select.click()
                        }
                    }else{
                        val select = driver.findElement(By.xpath(xPathExpression))
                        if(!select.size.equals(0)){
                            select.click()
                        }
                    }
                }

                suspend fun sendHumanLikeSelectAndClick(xPathExpression: String, visibleText: String, botProtection: Boolean = true){
                    if(botProtection){
                        val randomRangeForSelect = ((1000..2000))
                        delay(randomRangeForSelect.random().toLong())
                        val select = Select(driver.findElement(By.xpath(xPathExpression)))
                        delay(randomRangeForSelect.random().toLong())
                        select.selectByVisibleText(visibleText)
                    }
                }

                //Variables
                var arsaAlani: Double = 0.0
                var mahalle: String = ""
                var brutAlanBirimSatisFiyati: Double = 0.0

                //ARSA ALANI VE MAHALLE

                suspend fun getArsaAlaniVeMahalleAdi(){
                    driver.get(IMAR_URL) //Sadece istanbul/kadıöy ilçesi için

                    sendHumanLikeKeys("//*[@id=\"txtAdaParsel\"]","${fizibiliteModel.ada}/${fizibiliteModel.parsel}", false)
                    sendHumanLikeClick("//*[@id=\"btnSearchAdaParsel\"]",false)
                    sendHumanLikeClick("//*[@id=\"grid_adaParsel\"]/a",false)
                    sendHumanLikeClick("//*[@id=\"btnAdaParsel\"]",false)

                    val arsaAlaniSelector = driver.findElement(By.xpath("//*[@id=\"htmlOutput\"]/div[11]/div[2]/div/div/div/div[2]/b/span"))
                    arsaAlani = arsaAlaniSelector.text.toString().getAmount().replace("," ,".").toDouble()
                    //result

                    val mahalleSelector = driver.findElement(By.xpath("//*[@id=\"htmlOutput\"]/div[11]/div[1]/div/div/div/div[2]"))
                    mahalle = mahalleSelector.text.toString().lowercase().filter { !it.isWhitespace() }
                    //result
                }

                getArsaAlaniVeMahalleAdi()

                //BİRİM SATIŞ FİYATI;

                suspend fun getBirimSatısFiyati(){

                    val randomRangeForSelect = ((1000..2000))
                    delay(randomRangeForSelect.random().toLong())
                    driver.get(BIRIM_SATIS_FIYATI_ENDEKS_URL) //Siteye girdik
                    delay(randomRangeForSelect.random().toLong())

                    sendHumanLikeSelectAndClick("//*[@id=\"ddlReiCity\"]",fizibiliteModel.projeSehir) //Sehir dropdown
                    sendHumanLikeSelectAndClick("//*[@id=\"ddlReiTown\"]",fizibiliteModel.projeIlce) //Ilce dropdown
                    sendHumanLikeClick("//*[@id=\"ddlReiQuarter\"]/option[2]") //Mahalle dropdown click - focus için

                    //İlk sayfadan çektiğimiz mahalleyi, buradaki formda seçmeye çalışıyoruz, eşleştirmemiz lazım.
                    //Büyük harf, küçük harf, boşluk vs ayıklayarak. İlk 6 harfine bakmamız yeterli.
                    val mahalleSelectorInEndeks = Select(driver.findElement(By.xpath("//*[@id=\"ddlReiQuarter\"]"))) //Mahalle Dropdown seçtik.
                    mahalleSelectorInEndeks.options.forEach{
                        val lowercaseSelectedOptions = it.text.lowercase().filter { !it.isWhitespace() }
                        if(mahalle[0] == lowercaseSelectedOptions[0]){
                            if(mahalle[1] == lowercaseSelectedOptions[1]){
                                if(mahalle[2] == lowercaseSelectedOptions[2]){
                                    if(mahalle[3] == lowercaseSelectedOptions[3]){
                                        if(mahalle[4] == lowercaseSelectedOptions[4]){
                                            if(mahalle[5] == lowercaseSelectedOptions[5]){
                                                mahalle = it.text
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    mahalleSelectorInEndeks.selectByVisibleText(mahalle) //Mahalle Seçtik.

                    sendHumanLikeClick("//*[@id=\"estate360Container\"]/div/div[2]/h2[1]") //Boşluk - lose focus için
                    sendHumanLikeClick("//*[@id=\"reiForm\"]/div") //Endeks button.

                    val selectEmailBox = driver.findElement(By.xpath("//*[@id=\"username\"]"))

                    //Sanırım reCAPTCHA bu kısmı koruduğu için elle girmemiz gerekecek.
//                    sendHumanLikeKeys("//*[@id=\"username\"]","slnmprjct@gmail.com")
//                    delay(randomRangeForSelect.random().toLong())
//                    sendHumanLikeKeys("//*[@id=\"password\"]","3836emirbc")
//                    delay(randomRangeForSelect.random().toLong())
//                    sendHumanLikeClick("//*[@id=\"userLoginSubmitButton\"]")
//                    delay(randomRangeForSelect.random().toLong())

                    if(!selectEmailBox.size.equals(0)){
                        //Login ekranı geldi, burada kullanıcıya giriş yaptıracağız.
                        //ŞU AN ELLE BİLE GİRİŞ YAPTIRAMIYORUM NEDENSE
                        driver.manage().window().position = Point(0,0) //Browser'ı kullanıcının önüne getiriyoruz ve giriş istiyoruz.
                        WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"appRoot\"]/div[13]/div[2]/div[2]/div/div[3]/div/div[2]/div[1]/div[1]/span")))
                        val select = driver.findElement(By.xpath("//*[@id=\"appRoot\"]/div[13]/div[2]/div[2]/div/div[3]/div/div[2]/div[1]/div[1]/span"))
                        brutAlanBirimSatisFiyati = select.text.toString().getAmount().toDouble()
                        driver.quit()
                    }else{
                        //Login ekranı gelmedi endeksteki fiyatı çekebilirsin.
                        val select = driver.findElement(By.xpath("//*[@id=\"appRoot\"]/div[13]/div[2]/div[2]/div/div[3]/div/div[2]/div[1]/div[1]/span"))
                        brutAlanBirimSatisFiyati = select.text.toString().getAmount().toDouble()
                        driver.quit()
                    }

                }

                getBirimSatısFiyati()

                //Output
                fizibiliteModelOutPut.arsaAlani = arsaAlani
                fizibiliteModelOutPut.brutAlanBirimSatisFiyati = brutAlanBirimSatisFiyati
                send(Response.Success(fizibiliteModelOutPut))
            }

        }catch (e: Exception){
            send(Response.Error(e.localizedMessage ?: "ERROR MESSAGE"))
        }
    }
}