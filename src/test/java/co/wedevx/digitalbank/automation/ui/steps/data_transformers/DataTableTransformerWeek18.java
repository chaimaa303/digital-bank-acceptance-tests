package co.wedevx.digitalbank.automation.ui.steps.data_transformers;

import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.models.accountCard;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTableTransformerWeek18 {
    @DataTableType
    public accountCard accountcardEntry(Map<String, String> entry){
        String accountName = entry.get("accountName");
        String accountType = entry.get("accountType");
        String ownership = entry.get("ownership");
        long accountNumber = Long.parseLong(entry.get("accountNumber"));
        String interestRate = entry.get("interestRate");
        double balance = Double.parseDouble(entry.get("balance"));
        return new accountCard(accountName,accountType,ownership,accountNumber,interestRate,balance);
    }

    @DataTableType
    public BankTransaction transactionEntry(Map<String, String> entry){
        String date = entry.get("date");
        String category = entry.get("category");
        String description = entry.get("description");
        double amount = Double.parseDouble(entry.get("amount"));
        double balance = Double.parseDouble((entry.get("balance")));
        return new BankTransaction(date,category,description,amount,balance);

    }
    @DataTableType
    public NewCheckingAccountInfo newCheckingAccountInfoEntry(Map<String, String> entry){
        String checkingAccountType = entry.get("checkingAccountType");
        String accountOwnership = entry.get("accountOwnership");
        String accountName = entry.get("accountName");
        double initialDepositAmount = Double.parseDouble(entry.get("initialDepositAmount"));

        return new NewCheckingAccountInfo(checkingAccountType,accountOwnership,accountName,initialDepositAmount);


    }
}
