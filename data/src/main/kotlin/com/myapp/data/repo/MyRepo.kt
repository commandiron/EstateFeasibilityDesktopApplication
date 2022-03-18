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
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.devtools.idealized.Network.UserAgent
import org.openqa.selenium.support.ui.Select
import java.time.Duration
import javax.inject.Inject

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

                //Bot detection önlemek için.
                val userAgent = UserAgent(RandomUserAgent().getRandomUserAgent())
                options.addArguments("user-agent=${userAgent}")

                options.setCapability("useAutomationExtension", false)
                //options.setExperimentalOption("excludeSwitches","enable-automation")
                //options.addArguments("headless") //Without opening browser

                //Chrome Driver
                val driver: WebDriver = ChromeDriver(options)
                driver.manage().window().position = Point(0,0)
                driver.manage().window().size = Dimension(1024,768)

                var arsaAlani: Double = 0.0
                var mahalle: String = ""

                //ARSA ALANI VE MAHALLE

                fun getArsaAlaniVeMahalleAdi(){
                    driver.get(IMAR_URL) //Sadece istanbul/kadıöy ilçesi için

                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

                    val searchBox = driver.findElement(By.xpath("//*[@id=\"txtAdaParsel\"]"))
                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))
                    searchBox.clear()
                    searchBox.click()
                    searchBox.sendKeys("${fizibiliteModel.ada}/${fizibiliteModel.parsel}")

                    val searchButton = driver.findElement(By.xpath("//*[@id=\"btnSearchAdaParsel\"]"))
                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))
                    searchButton.click()

                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

                    val selectItem = driver.findElement(By.xpath("//*[@id=\"grid_adaParsel\"]/a"))
                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))
                    selectItem.click()

                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

                    val postItem = driver.findElement(By.xpath("//*[@id=\"btnAdaParsel\"]"))
                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))
                    postItem.click()

                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

                    val arsaAlaniSelector = driver.findElement(By.xpath("//*[@id=\"htmlOutput\"]/div[11]/div[2]/div/div/div/div[2]/b/span"))
                    arsaAlani = arsaAlaniSelector.text.toString().getAmount().replace("," ,".").toDouble()
                    //result

                    val mahalleSelector = driver.findElement(By.xpath("//*[@id=\"htmlOutput\"]/div[11]/div[1]/div/div/div/div[2]"))
                    mahalle = mahalleSelector.text.toString().lowercase().filter { !it.isWhitespace() }
                    //result

                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))
                }

                getArsaAlaniVeMahalleAdi()

                //BİRİM SATIŞ FİYATI;

                suspend fun getBirimSatısFiyati(){
                    val randomRange = (1000..2000) //-> bu kısım random beklemeler için bot detectiondan sıyrılmak için.

                    driver.get(BIRIM_SATIS_FIYATI_ENDEKS_URL) //Siteye girdik
                    delay(randomRange.random().toLong()) // Random bekledik

                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

                    val sehirSelector = Select(driver.findElement(By.xpath("//*[@id=\"ddlReiCity\"]"))) //Şehir selectledik
                    delay(randomRange.random().toLong()) // Random bekledik
                    sehirSelector.selectByVisibleText(fizibiliteModel.projeSehir) //Şehri seçtik
                    delay(randomRange.random().toLong()) // Random bekledik

                    val ilceSelector = Select(driver.findElement(By.xpath("//*[@id=\"ddlReiTown\"]"))) //İlçe selectledik
                    delay(randomRange.random().toLong()) // Random bekledik
                    ilceSelector.selectByVisibleText(fizibiliteModel.projeIlce) //İlçe seçtik
                    delay(randomRange.random().toLong()) // Random bekledik

                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

                    val mahalleSelectorInEndeksSelectDropDownMenu = driver.findElement(By.xpath("//*[@id=\"ddlReiQuarter\"]/option[2]")) //Mahalle dropdown menü seçtik.
                    delay(randomRange.random().toLong()) // Random bekledik
                    mahalleSelectorInEndeksSelectDropDownMenu.click() //Mahalle Dropdown clickledik.
                    delay(randomRange.random().toLong()) // Random bekledik

                    val mahalleSelectorInEndeks = Select(driver.findElement(By.xpath("//*[@id=\"ddlReiQuarter\"]"))) //Mahalle Dropdown seçtik.
                    delay(randomRange.random().toLong())// Random bekledik

                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))


                    //burada ilk 6 harfine göre mahalleyi eşleştiriyoruz çünkü, farklı formatlarda geliyor iki ayrı sayfadan.
                    //Birisinde mh. eklemiş diğerinde boşluk eklemiş vs.
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
                    delay(randomRange.random().toLong())// Random bekledik
                    val boslukSelector = driver.findElement(By.xpath("//*[@id=\"estate360Container\"]/div/div[2]/h2[1]"))
                    boslukSelector.click()
                    delay(randomRange.random().toLong())// Random bekledik

                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

                    //Burada problem var nedense endeks buttonuna tıklayamadım.
                    val getEndeksSelector = driver.findElement(By.xpath("//*[@id=\"reiForm\"]/div")) //Endeks button seç
                    delay(randomRange.random().toLong())// Random bekledik

                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))
                    getEndeksSelector.click() //Endeks button tıkla
                }

                //getBirimSatısFiyati()

                //Close browser
//                delay(2000)
//                driver.quit()

                //Output
                fizibiliteModelOutPut.arsaAlani = arsaAlani
                //fizibiliteModelOutPut.brutAlanBirimSatisFiyati =
                send(Response.Success(fizibiliteModelOutPut))
            }

        }catch (e: Exception){
            send(Response.Error(e.localizedMessage ?: "ERROR MESSAGE"))
        }
    }
}