package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCheckingPage {
    private WebDriver driver;
    private String expectedUrl = "https://dbank-qa.wedevx.co/bank/account/checking-add";

    public CreateCheckingPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "checking-menu")
    private WebElement checkingMenu;

    @FindBy(id = "new-checking-menu-item")
    private WebElement newCheckingButton;
    @FindBy(id = "Standard Checking")
    private WebElement standardCheckingAccountTypeRadioButton;

    @FindBy(id = "Interest Checking")
    private WebElement interestCheckingAccountTypeRadioButton;

    @FindBy(id = "Individual")
    private WebElement individualOwnershipTypeRadioButton;

    @FindBy(id = "Joint")
    private WebElement jointOwnershipTypeRadioButton;

    @FindBy(id = "name")
    private WebElement accountNameTxt;

    @FindBy(id = "openingBalance")
    private WebElement openingBalanceTxtBox;

    @FindBy(id = "newCheckingSubmit")
    private WebElement submitBtn;





    public void createNewChecking(List<NewCheckingAccountInfo> checkingAccountInfoList){
        NewCheckingAccountInfo testDataForCheckingAccount = checkingAccountInfoList.get(0);

        checkingMenu.click();

        newCheckingButton.click();


        assertEquals(ConfigReader.getPropertiesValue("digitalbank.createnewCheckingurl"), driver.getCurrentUrl(), "new checking button didnt get to the " + expectedUrl);

        // user selects account type
        if(testDataForCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Standard Checking")){
            standardCheckingAccountTypeRadioButton.click();

        }else if(testDataForCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Interest Checking")){
           interestCheckingAccountTypeRadioButton.click();
        } else {
            throw new NoSuchElementException("Invalid checking account type");
        }


        if(testDataForCheckingAccount.getAccountOwnership().equalsIgnoreCase("Individual")){
            individualOwnershipTypeRadioButton.click();

        }else if(testDataForCheckingAccount.getAccountOwnership().equalsIgnoreCase("Joint")){
            jointOwnershipTypeRadioButton.click();
        } else {
            throw new NoSuchElementException("Invalid account ownership type");
        }



        accountNameTxt.sendKeys(testDataForCheckingAccount.getAccountName());


        openingBalanceTxtBox.sendKeys(String.valueOf(testDataForCheckingAccount.getInitialDepositAmount()));

        submitBtn.click();
    }









}
