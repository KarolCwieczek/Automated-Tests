package Tests.MyStore;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchForPrintedDressTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        //Test setup
        System.setProperty("webdriver.gecko.driver","src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test
    public void searchTest() throws InterruptedException {
        //Getting on http://automationpractice.com site and searching for Printed Dress
        driver.get("http://automationpractice.com/index.php");
        WebElement searchBar = driver.findElement(By.id("search_query_top"));
        searchBar.click();
        searchBar.sendKeys("Printed Dress");
        searchBar.submit();
        //Waiting for results of the search
        Thread.sleep(5000);
        //Checking if the right elements were found and the right number of products is displayed
        List<WebElement> listOfProducts = driver.findElements(By.xpath("//child::div[@id='center_column']//a[@class='product-name']"));
        String productShownSentence = driver.findElement(By.className("heading-counter")).getText();
        String numbersOfProductShown = productShownSentence.substring(0,2).trim();
        int usedForComparison = Integer.parseInt(numbersOfProductShown);
        Assert.assertEquals(listOfProducts.size(),usedForComparison);
        for(WebElement product : listOfProducts) {
           Assert.assertTrue(product.getText().contains("Printed"));
           Assert.assertTrue(product.getText().contains("Dress"));
        }
    }

    @After
    public void afterAll() {
        //Closing the browser
       driver.quit();
    }
}
