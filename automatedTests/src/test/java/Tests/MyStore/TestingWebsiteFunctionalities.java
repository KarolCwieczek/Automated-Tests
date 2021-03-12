package Tests.MyStore;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestingWebsiteFunctionalities {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver","src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void afterAll() {
        driver.quit();
    }

    @Test
    public void priceFilterTest() {
        driver.get("http://automationpractice.com/index.php");
        WebElement dressesBtn = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a"));
        dressesBtn.click();
        WebElement filter = driver.findElement(By.id("selectProductSort"));
        Select sortBy = new Select(filter);
        sortBy.selectByIndex(2);
        // After choosing any type of sorting website become unresponsive and it's impossible
        // to continue testing this functionality
    }

    @Test
    public void newsletterTest() {
        //Getting an email
        driver.get("https://temp-mail.org/pl/10minutemail");
        String email = driver.findElement(By.id("mail")).getAttribute("value");
        //Going on shop website
        driver.get("http://automationpractice.com/index.php");
        //Submitting previously taken email to newsletter
        WebElement newsletterInput = driver.findElement(By.id("newsletter-input"));
        newsletterInput.click();
        newsletterInput.sendKeys(email);
        WebElement submitBtn = driver.findElement(By.xpath("//button[@name='submitNewsletter']"));
        submitBtn.click();
        //Checking if it was submitted successfully
        WebElement alert = driver.findElement(By.xpath("//p[@class='alert alert-success']"));
        Assert.assertEquals("Newsletter : You have successfully subscribed to this newsletter.",alert.getText());
    }

    @Test
    public void compareTest() throws InterruptedException {
        //Inicialization of wait and action objects
        WebDriverWait wait = new WebDriverWait(driver,3);
        Actions action = new Actions(driver);
        //Going on shop website and searching for certain item
        driver.get("http://automationpractice.com/index.php");
        WebElement searchBar = driver.findElement(By.id("search_query_top"));
        searchBar.click();
        searchBar.sendKeys("Printed Dress");
        searchBar.submit();
        //Waiting for results of the search
        Thread.sleep(4000);
        //Preparing comapare button and lists of items searched before
        List<WebElement> listOfProducts = driver.findElements(By.xpath("//child::div[@id='center_column']//h5[@itemprop='name']"));
        List<WebElement> listOfCompare = driver.findElements(By.xpath("//a[@class='add_to_compare']"));
        WebElement compareBtn = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/div[1]/div[2]/form/button"));
        //Getting names of items we will choose to compare
        String assertOne = listOfProducts.get(0).getText();
        String assertTwo = listOfProducts.get(1).getText();
        //Adding first item to compare
        action.moveToElement(listOfProducts.get(0)).perform();
        Thread.sleep(750);
        wait.until(ExpectedConditions.elementToBeClickable(listOfCompare.get(0)));
        listOfCompare.get(0).click();
        listOfCompare.get(0).click();
        //Adding second item to compare
        action.moveToElement(listOfProducts.get(1)).perform();
        Thread.sleep(750);
        wait.until(ExpectedConditions.elementToBeClickable(listOfCompare.get(1)));
        listOfCompare.get(1).click();
        listOfCompare.get(1).click();
        //Going to compare page
        compareBtn.click();
        //Checking if the items are the same as on previous page
        List<WebElement> assertList = driver.findElements(By.xpath("//a[@class='product-name']"));
        Assert.assertEquals(assertOne,assertList.get(1).getText());
        Assert.assertEquals(assertTwo,assertList.get(0).getText());
    }
}
