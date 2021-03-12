package Tests.MyStore;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class LogOffAndForgotPassBtn {
    //I'm using here an account created with CreatingAnAccount Test
    private WebDriver driver;
    private String email = "gikora8265@mailnest.net";
    private String passwr = "Jeden8L";

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver","src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }

    @After
    public void afterAll() {
        driver.quit();
    }

    @Test
    public void logOffTest() throws InterruptedException {
        // Going to login page
        WebElement signInBtn = driver.findElement(By.className("login"));
        signInBtn.click();
        // Typing valid email & password combination to log in
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(this.email);
        WebElement passwordInput = driver.findElement(By.id("passwd"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(this.passwr);
        //Logging out
        WebElement logInBtn = driver.findElement(By.id("SubmitLogin"));
        logInBtn.click();
        WebElement logOutBtn = driver.findElement(By.className("logout"));
        logOutBtn.click();
        //Checking successful logout
        WebElement signInBtnLogOut = driver.findElement(By.className("login"));
        Assert.assertEquals("Sign in", signInBtnLogOut.getText());
        //Checking forgot password function
        WebElement forgotPassBtn = driver.findElement(By.xpath("//a[@title='Recover your forgotten password']"));
        forgotPassBtn.click();
        WebElement forgottenEmail = driver.findElement(By.xpath("//input[@name='email']"));
        forgottenEmail.click();
        forgottenEmail.sendKeys(this.email);
        WebElement retrievePasswd = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/form/fieldset/p/button"));
        retrievePasswd.click();
        WebElement confirmationAlert = driver.findElement(By.xpath("//p[@class='alert alert-success']"));
        Assert.assertEquals("A confirmation email has been sent to your address: gikora8265@mailnest.net",confirmationAlert.getText());
    }
}
