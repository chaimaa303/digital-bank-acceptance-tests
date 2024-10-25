package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    private static WebDriver driver;

    //private constructor
    private Driver() {

    }

    public static WebDriver getDriver() throws MalformedURLException {
        if (driver == null) {
            String browser = ConfigReader.getPropertiesValue("browser");

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;

                case "headless":
                    //WebDriverManager.firefoxdriver().setup();
                    WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                    FirefoxOptions options = new FirefoxOptions();

                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("disable-extensions");
                    options.addArguments("--proxy-server='direct://'");
                    options.addArguments("--proxy-bypass-list=*");
                    options.addArguments("--start-maximized");
                    options.addArguments("--headless");

                    driver = new FirefoxDriver(options);
                    break;
                case "saucelabs":
                    String platform = ConfigReader.getPropertiesValue("dbank.saucelabs.platform");
                    String browserType = ConfigReader.getPropertiesValue("dbank.saucelabs.browser");
                    String browserVersion = ConfigReader.getPropertiesValue("dbank.saucelabs.browser.version");

                    driver = loadSauceLabs(platform,browserType,browserVersion);
                    break;


                case "firefox":
                default:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

            }
        }
        return driver;
    }

    public static void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            //taking screenshot
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            //adding screenshots to report
            scenario.attach(screenshot, "image/png", "screenshot");
        }

    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static WebDriver loadSauceLabs(String platform, String browserType, String browserVersion) {
        String USERNAME = ConfigReader.getPropertiesValue("dbank.saucelabs.username");
        String ACCESS_KEY = "0902a79e-a91b-43ca-8aef-56b986914795";

        String url = "https://"+USERNAME +":" + ACCESS_KEY +"@ondemand.us-west-1.saucelabs.com:443/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("browserName", browserType);
        capabilities.setCapability("browserVersion", browserVersion);
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return driver;


    }
}

