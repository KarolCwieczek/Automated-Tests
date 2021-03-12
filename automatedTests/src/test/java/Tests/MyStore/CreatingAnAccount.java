package Tests.MyStore;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class CreatingAnAccount {

    private WebDriver driver;
    private String email;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver","src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://temp-mail.org/pl/10minutemail");
        email = driver.findElement(By.id("mail")).getAttribute("value");
    }

    @After
    public void afterAll() {
        // driver.quit();
    }

    @Test
    public void creatingAnAccount() {
        //Going to shop website and beginning of customer creation
        driver.get("http://automationpractice.com/index.php");
        WebElement signInBtn = driver.findElement(By.className("login"));
        signInBtn.click();
        WebElement emailInput = driver.findElement(By.id("email_create"));
        emailInput.click();
        emailInput.sendKeys(email);
        WebElement createAccBtn = driver.findElement(By.id("SubmitCreate"));
        createAccBtn.click();

        //Filling customer creation form
        WebElement title = driver.findElement(By.id("id_gender1"));
        title.click();
        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        firstName.click();
        firstName.sendKeys("Derek");
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        lastName.click();
        lastName.sendKeys("Trucks");
        WebElement password = driver.findElement(By.id("passwd"));
        password.click();
        password.sendKeys("Jeden8L");
        WebElement days = driver.findElement(By.id("days"));
        Select dateDays = new Select(days);
        dateDays.selectByIndex(11);
        WebElement month = driver.findElement(By.id("months"));
        Select dateMonth = new Select(month);
        dateMonth.selectByIndex(5);
        WebElement year = driver.findElement(By.id("years"));
        Select dateYear = new Select(year);
        dateYear.selectByIndex(33);
        WebElement address = driver.findElement(By.id("address1"));
        address.click();
        address.sendKeys("Sesame Street");
        WebElement city = driver.findElement(By.id("city"));
        city.click();
        city.sendKeys("Sesame City");
        WebElement state = driver.findElement(By.id("id_state"));
        Select stateToChoose = new Select(state);
        stateToChoose.selectByVisibleText("Hawaii");
        WebElement postal = driver.findElement(By.id("postcode"));
        postal.click();
        postal.sendKeys("96420");
        WebElement phone = driver.findElement(By.id("phone_mobile"));
        phone.click();
        phone.sendKeys("777666555");

        //Going to addresses page
        WebElement registerBtn = driver.findElement(By.id("submitAccount"));
        registerBtn.click();
        WebElement addressesBtn = driver.findElement(By.xpath("//a[@title='Addresses']"));
        addressesBtn.click();

        //Checking if submitted data is the same as the one previously written
        String nameCheck = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/div/div/ul/li[2]/span[1]")).getText();
        String lastNameCheck = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/div/div/ul/li[2]/span[2]")).getText();
        String streetCheck = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[01]/div/div/ul/li[4]/span[1]")).getText();
        String cityCheck = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/div/div/ul/li[5]/span[1]")).getText();
        String stateCheck = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/div/div/ul/li[5]/span[2]")).getText();
        String postalCheck = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/div/div/ul/li[5]/span[3]")).getText();
        String phoneCheck = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]/div/div/ul/li[8]/span")).getText();
        Assert.assertTrue(nameCheck.contains("Derek"));
        Assert.assertTrue(lastNameCheck.contains("Trucks"));
        Assert.assertTrue(streetCheck.contains("Sesame Street"));
        Assert.assertTrue(cityCheck.contains("Sesame City"));
        Assert.assertTrue(stateCheck.contains("Hawaii"));
        Assert.assertTrue(postalCheck.contains("96420"));
        Assert.assertTrue(phoneCheck.contains("777666555"));
    }

}
