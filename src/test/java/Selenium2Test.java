

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Selenium2Test {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();

    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void selTest() throws IOException {
        System.out.println("This is sel test");
        driver = new ChromeDriver();
        driver.get("https://www.fb.com");
        driver.findElement(By.xpath("//button[@data-testid=\"royal_login_button\"]")).click();
        String directory = "src/main/resources";
        File sourceFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String Filename="NMMMM" +".png";
        System.out.println(Filename);
        FileUtils.copyFile(sourceFile,new File(directory+Filename));
        driver.quit();
    }

    @Test
    public void selTestSelect() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println(System.getProperty("user.dir")+"\\src\\main\\resources");
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement select = driver.findElement(By.xpath("//select[@id='dropdown']"));
        select.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement option = driver.findElement(By.xpath("//option[contains(text(),'Option 1')]"));
        option.click();

        System.out.println(select.getText());
        Assert.assertTrue(option.isSelected());

    }

    @Test
    public void selTesthover() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/hovers");
        WebElement element = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/img[1]"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        WebElement text = driver.findElement(By.xpath("//h5[contains(text(),'name: user1')]"));
        Assert.assertEquals("name: user1", text.getText());


    }

    @Test
    public void selTestalert() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/context_menu");
        WebElement element = driver.findElement(By.xpath("//div[@id='hot-spot']"));
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
        driver.switchTo().alert().accept();

    }

    @Test
    public void selTestlasttest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://ultimateqa.com/simple-html-elements-for-automation-/");
        WebElement element = driver.findElement(By.linkText("Clickable Icon"));
        String href = element.getAttribute("href");
        String css = element.getCssValue("background-origin");
        Assert.assertEquals("https://ultimateqa.com/link-success/", href);
        Assert.assertEquals("padding-box", css);

    }

    @Test
    public void selTestkeypress() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/key_presses");

        WebElement element = driver.findElement(By.xpath("//input[@id='target']"));
        element.click();
        element.sendKeys(Keys.ARROW_RIGHT);
        WebElement result = driver.findElement(By.xpath("//p[@id='result']"));
        Assert.assertEquals("You entered: RIGHT", result.getText());


    }

    @Test
    public void selTestFocus() {
        driver = new ChromeDriver();



//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://example.cypress.io/commands/actions");

        WebElement element = driver.findElement(By.cssSelector("#password1"));
//        WebDriverWait wait = new WebDriverWait(driver,20);
//        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        WebElement element2 =driver.findElement(By.xpath("//label[contains(text(),'Password')]"));
    Assert.assertTrue(element2.getAttribute("style").contains("color: orange;"));
    }

    @Test
    public void selTestcookie() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://example.cypress.io/commands/cookies");

        WebElement element = driver.findElement(By.cssSelector(".set-a-cookie"));
        element.click();
        Cookie cookie = driver.manage().getCookieNamed("token");
        Assert.assertEquals("123ABC",cookie.getValue());
    }
}

