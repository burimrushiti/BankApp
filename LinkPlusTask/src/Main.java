import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the bank:");
        String bankName = scanner.nextLine();
        Bank bank = new Bank(bankName);

        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Create Account");
            System.out.println("2. List Accounts");
            System.out.println("3. Perform Transaction");
            System.out.println("4. Check Total Transaction Fee Amount");
            System.out.println("5. Check Total Transfer Amount");
            System.out.println("6. Withdraw Money");
            System.out.println("7. Deposit Money");
            System.out.println("8. See list of transactions for any account");
            System.out.println("9. Check account balance for any account");
            System.out.println("10. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter account holder name:");
                    String accountHolderName = scanner.nextLine();
                    bank.createAccount(accountHolderName);
                    break;
                case 2:
                    List<String> accountNames = bank.getListOfAccounts();
                    System.out.println("Accounts:");
                    for (String name : accountNames) {
                        System.out.println(name);
                    }
                    break;
                case 3:
                    System.out.println("Enter sender account holder name:");
                    String senderName = scanner.nextLine();
                    System.out.println("Enter receiver account holder name:");
                    String receiverName = scanner.nextLine();
                    System.out.println("Enter transaction amount:");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter transaction reason:");
                    String reason = scanner.nextLine();

                    Account sender = getAccountByName(bank, senderName);
                    Account receiver = getAccountByName(bank, receiverName);

                    if (sender != null && receiver != null) {
                        Transaction transaction = new Transaction(amount, sender, receiver, reason);
                        transaction.sendMoney(sender, receiver, amount, reason);
                    } else {
                        System.out.println("Invalid account holders.");
                    }
                    break;
                case 4:
                    System.out.println("Total transaction fee amount: $" + bank.getTotalTransactionFeeAmount());
                    break;
                case 5:
                    System.out.println("Total transfer amount: $" + Bank.totalTransferAmount);
                    break;
                case 6:
                    System.out.println("Enter account holder name:");
                    String accountName1 = scanner.nextLine();
                    Account account1 = getAccountByName(bank, accountName1);
                    if (account1 != null) {
                        System.out.println("Enter withdrawal amount:");
                        double withdrawalAmount = scanner.nextDouble();
                        scanner.nextLine();
                        if (account1.getAccountBalance() >= withdrawalAmount) {
                            account1.setAccountBalance(account1.getAccountBalance() - withdrawalAmount);
                            System.out.println("Withdrawal successful.");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 7:
                    System.out.println("Enter account holder name:");
                    String accountName2 = scanner.nextLine();
                    Account account2 = getAccountByName(bank, accountName2);
                    if (account2 != null) {
                        System.out.println("Enter deposit amount:");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine();
                        account2.setAccountBalance(account2.getAccountBalance() + depositAmount);
                        System.out.println("Deposit successful.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 8:
                    System.out.println("Enter account holder name:");
                    String accountName3 = scanner.nextLine();
                    Account account3 = getAccountByName(bank, accountName3);
                    if (account3 != null) {
                        account3.printTransactions();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 9:
                    System.out.println("Enter account holder name:");
                    String accountName4 = scanner.nextLine();
                    Account account4 = getAccountByName(bank, accountName4);
                    if (account4 != null) {
                        System.out.println("Account balance: $" + account4.getAccountBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 10:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 10.");
            }
        }
    }

    private static Account getAccountByName(Bank bank, String accountHolderName) {
        for (String name : bank.getListOfAccounts()) {
            if (name.equals(accountHolderName)) {
                for (Account account : bank.getAccounts()) {
                    if (account.getNameOfTheUser().equals(accountHolderName)) {
                        return account;
                    }
                }
            }
        }
        return null;
    }
}

