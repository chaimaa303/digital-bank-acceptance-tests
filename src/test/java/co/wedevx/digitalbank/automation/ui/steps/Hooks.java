package co.wedevx.digitalbank.automation.ui.steps;


import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class Hooks {

    @Before("@Registration")
    public  void establishConnectionToDB() {
        DBUtils.establishConnection();
    }



    @Before("not @Registration")
    public void the_user_on_dbank_homepage() {
        try {
            getDriver().get("http://chaimaak2540.mydevx.com/bank/login");
            //getDriver().get("https://dbank-qa.wedevx.co/bank/login");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }


    @After("not @NegativeRegistrationCases")
    public void afterEachScenario(Scenario scenario){
        Driver.takeScreenshot(scenario);
        Driver.closeDriver();
    }

    @After()
    public static void closeConnectionToDB(){
        DBUtils.closeConnection();

    }

}
