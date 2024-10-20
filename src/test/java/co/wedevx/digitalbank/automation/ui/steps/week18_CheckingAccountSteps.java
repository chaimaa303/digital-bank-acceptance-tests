package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.models.accountCard;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingPage;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class week18_CheckingAccountSteps {
    WebDriver driver = Driver.getDriver() ;
    private LoginPage loginpage = new LoginPage(driver);
    private CreateCheckingPage createCheckingPage = new CreateCheckingPage(driver);
    private ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);

    public week18_CheckingAccountSteps() throws MalformedURLException {
    }


    @Given("the user is logged in as {string} {string}")
    public void the_user_is_logged_in_as(String username, String password) {
        loginpage.login(username, password);

    }


    @When("the user creates a new checking account with the following data")
    public void the_user_creates_a_new_checking_account_with_the_following_data(List<NewCheckingAccountInfo> checkingAccountInfoList) {
     createCheckingPage.createNewChecking(checkingAccountInfoList);
    }

    @Then("the user should see the green {string} message")
    public void the_user_should_see_the_green_message(String expectedConfirmationMsg) {
        expectedConfirmationMsg =  "Confirmation "+ expectedConfirmationMsg  ;
        assertEquals(expectedConfirmationMsg, "Confirmation "+viewCheckingAccountPage.getActualConfirmationMessage());
    }

    @Then("the user should see newly added account card")
    public void the_user_should_see_newly_added_account_card(List<accountCard> accountCardList)  {
        Map<String, String> actualresultMap = viewCheckingAccountPage.getNewleyAddedCheckingAccountInfoMap();

        accountCard expectedResult = accountCardList.get(0);

        assertEquals(expectedResult.getAccountName(), actualresultMap.get("actualAccountName"));
        assertEquals("Account: "+ expectedResult.getAccountType(), actualresultMap.get("actualAccType"));
        assertEquals("Ownership: "+ expectedResult.getOwnership(), actualresultMap.get("actualOwnership"));
        assertEquals("Interest Rate: "+ expectedResult.getInterestRate(), actualresultMap.get("actualInterestRate"));
        String expectedBalance = String.format("%.2f", expectedResult.getBalance());
        assertEquals("Balance: $"+expectedBalance, actualresultMap.get("actualBalance"));

    }

    @Then("the user should see the following transaction")
    public void the_user_should_see_the_following_transaction(List<BankTransaction> expectedTransactions) {
        Map<String, String> actualresultMap = viewCheckingAccountPage.getNewleyAddedCheckingAccountTransactionInfoMap();
        BankTransaction expectedTransaction = expectedTransactions.get(0);

        assertEquals(expectedTransaction.getCategory(), actualresultMap.get("actualCategory") , "Category Mismatch");
        assertEquals(expectedTransaction.getAmount(), Double.parseDouble(actualresultMap.get("actualAmount")), "Amount Mismatch");
        assertEquals(expectedTransaction.getBalance(), Double.parseDouble(actualresultMap.get("actualBalance")), "Balance Mismatch");


    }
}
