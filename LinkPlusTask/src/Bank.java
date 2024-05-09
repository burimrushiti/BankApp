import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankName;
    static ArrayList<String> listOfAccounts = new ArrayList<>();
    static ArrayList<Account> accounts = new ArrayList<>();

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    static double totalTransferAmount;
    static double transactionFlatFeeAmount;
    static double transactionPercentFeeAmount;

    public void createAccount(String accountHolderName) {
        Account account = new Account(accountHolderName);
        listOfAccounts.add(accountHolderName);
        accounts.add(account);
    }

    public List<String> getListOfAccounts() {
        return listOfAccounts;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public double getTotalTransactionFeeAmount() {
        return transactionFlatFeeAmount + transactionPercentFeeAmount;
    }
}