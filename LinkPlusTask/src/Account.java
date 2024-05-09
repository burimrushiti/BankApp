import java.util.ArrayList;
import java.util.Scanner;

public class Account {
    private static int counter = 0;
    private int accountId;
    private String nameOfTheUser;
    private double accountBalance;
    ArrayList<Transaction> listOfTransactions = new ArrayList<>();

    public Account(String nameOfTheUser) {
        this.accountId = ++counter;
        this.nameOfTheUser = nameOfTheUser;
        this.accountBalance = 0;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getNameOfTheUser() {
        return nameOfTheUser;
    }

    public void printDetails() {
        System.out.println("Details for this account: ");
        System.out.println("Account ID: " + accountId);
        System.out.println("Account holder: " + nameOfTheUser);
        System.out.println("Balance: " + accountBalance);
    }

    public void printTransactions() {
        System.out.println("Transaction history for account: " + nameOfTheUser);
        for (Transaction transaction : listOfTransactions) {
            System.out.println(transaction.toString());
        }
    }
}

class Transaction {
    private double amount;
    private Account sender;
    private Account receiver;
    private String transactionReason;

    public Transaction(double amount, Account sender, Account receiver, String transactionReason) {
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        this.transactionReason = transactionReason;
    }

    public String toString() {
        return "Transaction: Amount=" + amount + ", Sender=" + sender.getNameOfTheUser() +
                ", Receiver=" + receiver.getNameOfTheUser() + ", Reason=" + transactionReason;
    }

    void sendMoney(Account sender, Account receiver, double amount, String reason) {
        System.out.println("Choose transaction fee type:");
        System.out.println("1. Flat fee of $5");
        System.out.println("2. 3% fee");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                if (sender.getAccountBalance() >= amount + 5) {
                    sender.setAccountBalance(sender.getAccountBalance() - (amount + 5));
                    receiver.setAccountBalance(receiver.getAccountBalance() + amount);
                    Bank.totalTransferAmount += amount;
                    Bank.transactionFlatFeeAmount += 5;
                    sender.listOfTransactions.add(new Transaction(-(amount + 5), sender, receiver, reason));
                    receiver.listOfTransactions.add(new Transaction(amount, sender, receiver, reason));
                    System.out.println("Transaction successful.");
                } else {
                    System.out.println("Not enough money to send.");
                }
                break;
            case 2:
                double fee = amount * 0.03;
                if (sender.getAccountBalance() >= amount + fee) {
                    sender.setAccountBalance(sender.getAccountBalance() - (amount + fee));
                    receiver.setAccountBalance(receiver.getAccountBalance() + amount);
                    Bank.totalTransferAmount += amount;
                    Bank.transactionPercentFeeAmount += fee;
                    sender.listOfTransactions.add(new Transaction(-(amount + fee), sender, receiver, reason));
                    receiver.listOfTransactions.add(new Transaction(amount, sender, receiver, reason));
                    System.out.println("Transaction successful.");
                } else {
                    System.out.println("Not enough money to send.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}

