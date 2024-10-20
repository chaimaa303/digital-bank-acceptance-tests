package SauceLab;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceLabDemo {
    public static void main(String[] args) throws MalformedURLException {



    String USERNAME = "oauth-denize.ele-473a4";
    String ACCESS_KEY = "0902a79e-a91b-43ca-8aef-56b986914795";
    String url = "https://oauth-denize.ele-473a4:0902a79e-a91b-43ca-8aef-56b986914795@ondemand.us-west-1.saucelabs.com:443/wd/hub";

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", "Windows 11 Home");
    capabilities.setCapability("browserName", BrowserType.FIREFOX);
    capabilities.setCapability("browserVersion", "128.0.3");
        WebDriver driver = new RemoteWebDriver( new URL(url), capabilities);

        driver.get("https://www.amazon.com/");

        WebElement searchBox = driver.findElement(By.cssSelector("#twotabsearchtextbox"));
        searchBox.sendKeys("Iphone" + Keys.ENTER);

        WebElement appleBrand = driver.findElement(By.cssSelector("li[id='p_89/Apple']"));
        System.out.println(appleBrand.getText());






}
}
