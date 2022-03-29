package com.myapp.data.core

import com.myapp.data.core.KillRunningWinProcesses.isProcessRunning
import com.myapp.data.core.KillRunningWinProcesses.killProcess
import io.github.bonigarcia.wdm.WebDriverManager
import kotlinx.coroutines.delay
import org.apache.commons.io.FileUtils
import org.kodein.memory.util.UUID
import org.openqa.selenium.*
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.devtools.idealized.Network
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration
import java.util.*

object ChromeDriverSeleniumHandle {

    lateinit var driver: WebDriver

    fun setSelenium(
        isHeadless: Boolean = false,
        windowPosition: Point = Point(0,0),
        dimension: Dimension = Dimension(800,520)):WebDriver{


        quitDriver()

        //Webdriver
        System.setProperty(
            "webdriver.chrome.driver",
            "data/src/main/kotlin/com/myapp/data/drivers/chromedriver.exe"
        );
        WebDriverManager.chromedriver().setup() //Bilgisayarda chrome yoksa çalışmıyor, çünkü onun sadece onun driver'ını gömdüm.

        //Chrome Options
        val options = ChromeOptions()

        // Fixing 255 Error crashes
        options.addArguments("--no-sandbox")
        options.addArguments("--disable-dev-shm-usage")

        // Options to trick bot detection
        options.addArguments("--disable-blink-features=AutomationControlled")
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"))
        options.setExperimentalOption("useAutomationExtension", false)
//        options.addArguments("window-size=1920,1080")
        options.addArguments("disable-infobars")
        options.addArguments("user-data-dir=/tmp/afabc")//User data, login olunan siteye bir daha girilebilmesi için.

        //Headless browser
        options.setHeadless(isHeadless)

        // Changing the user agent
        val userAgent = Network.UserAgent(RandomUserAgent().getRandomUserAgent())
        options.addArguments("user-agent=${userAgent}")

        //Chrome Driver
        driver = ChromeDriver(options)
        driver.manage().window().position = windowPosition
        driver.manage().window().size = dimension

        //Wait For Element
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000))

        return driver
    }

    fun quitDriver(){
        try {
            if(isProcessRunning("chrome.exe")){
                killProcess("chrome.exe")
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    suspend fun sendHumanLikeKeys(xPathExpression: String, text: String, botProtection: Boolean = true){
        if(botProtection){
            val randomRangeForSelect = ((1000..2000))
            val randomRangeForKeys = ((100..800))
            delay(randomRangeForSelect.random().toLong())
            val select = driver.findElement(By.xpath(xPathExpression))
            delay(randomRangeForSelect.random().toLong())
            select.click()
            delay(randomRangeForSelect.random().toLong())
            for(i in text){
                delay(randomRangeForKeys.random().toLong())
                select.sendKeys(i.toString())
            }
        }else{
            val select = driver.findElement(By.xpath(xPathExpression))
            select.click()
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

    fun waitUntilVisibilityOfElement(elementXpath: String, timeOutInSeconds: Long){
        WebDriverWait(driver,timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)))
    }

    fun getScreenShot(elementXpath: String): String{
        try {
            val file = driver.findElement(By.xpath(elementXpath)).getScreenshotAs(OutputType.FILE)
            val uuid = UUID.randomUUID().toString()
            val path = "C:/Users/Emir/Desktop/FizibiliteUygulamasi/${uuid}.png"
            val destFile = File(path)
            FileUtils.copyFile(file,destFile)
            return path
        }catch (e:Exception){
            e.printStackTrace()
            return ""
        }
    }
}