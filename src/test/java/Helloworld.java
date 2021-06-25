import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Helloworld {
    @Test
    public void firstTest(){
        System.out.println("this is first test");
        System.setProperty("webdriver.chrome.driver","Resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.youtube.com");
        driver.quit();
    }
}

