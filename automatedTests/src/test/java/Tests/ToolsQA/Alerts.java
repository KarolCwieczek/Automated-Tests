package Tests.ToolsQA;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Alerts {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver","src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/alerts");
    }

    @After
    public void afterAll() {
        //driver.quit();
    }

    @Test
    public void alertOne() throws InterruptedException {
        WebElement firstBtn = driver.findElement(By.id("alertButton"));
        firstBtn.click();
        Alert alert = driver.switchTo().alert();
        Thread.sleep(500);
        alert.accept();
    }

    @Test
    public void alertTwo() {
        WebElement secondBtn = driver.findElement(By.id("timerAlertButton"));
        secondBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 5500);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void alertThree() {
        WebElement thirdBtn = driver.findElement(By.id("confirmButton"));
        thirdBtn.click();
        Alert alert = driver.switchTo().alert();
        Random number = new Random();
        int tellMeWhatToChoose = number.nextInt(2);
        switch (tellMeWhatToChoose) {
            case 0:
                alert.accept();
                WebElement confirmation = driver.findElement(By.id("confirmResult"));
                Assert.assertEquals("You selected Ok",confirmation.getText());
                break;
            case 1:
                alert.dismiss();
                WebElement confirmationToo = driver.findElement(By.id("confirmResult"));
                Assert.assertEquals("You selected Cancel",confirmationToo.getText());
                break;
        }
    }

    @Test
    public void alertFour() {
        WebElement fourthBtn = driver.findElement(By.id("promtButton"));
        fourthBtn.click();
        Alert alert = driver.switchTo().alert();
        String[] names = {"Na-Mi-Ka","Roger","Uvo","Dr. Marcus"};
        Random number = new Random();
        int tellMeWhatToChoose = number.nextInt(names.length);
        alert.sendKeys(names[tellMeWhatToChoose]);
        alert.accept();
        WebElement confirmation = driver.findElement(By.id("promptResult"));
        Assert.assertEquals("You entered " + names[tellMeWhatToChoose], confirmation.getText());
    }
}
