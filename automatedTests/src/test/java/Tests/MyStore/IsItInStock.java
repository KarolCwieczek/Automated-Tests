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

public class IsItInStock {
    private WebDriver driver;
    private String item = "Printed Chiffon Dress";

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver","src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }

    @After
    public void afterAll() {
        driver.quit();
    }

    @Test
    public void isItInStock() throws InterruptedException {
        //Searching for item described above
        WebElement searchBar = driver.findElement(By.id("search_query_top"));
        searchBar.click();
        searchBar.sendKeys(item);
        searchBar.submit();
        Thread.sleep(5000);
        List<WebElement> listOfProducts = driver.findElements(By.xpath("//child::div[@id='center_column']//a[@class='product-name']"));
        List<WebElement> inStockList = driver.findElements(By.xpath("//span[@class='available-now']"));
        int index = 0;
        for (WebElement theOne : listOfProducts) {
            if (theOne.getText().equals(item)) {
                index = listOfProducts.indexOf(theOne);
            }
        }
        Assert.assertEquals("In stock", inStockList.get(index).getText());
    }
}
