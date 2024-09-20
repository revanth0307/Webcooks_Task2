import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMInterface{
    private Map<String, BankAccount> accounts;
    private Scanner scanner;

    public ATMInterface() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
        initializeAccounts();
    }

    private void initializeAccounts() {
        accounts.put("12345", new BankAccount("12345",500.00));
        accounts.put("67890", new BankAccount("67890",1000.00));
    }

    private BankAccount authenticate() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            return account;
        } else {
            System.out.println("Invalid account number");
            return null;
        }
    }

    private void showMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Funds");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    public void start() {
        BankAccount account = authenticate();
        if (account == null) {
            return;
        }

        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdraw amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient account number: ");
                    String recipientAccountNumber = scanner.nextLine();
                    BankAccount recipientAccount = accounts.get(recipientAccountNumber);
                    if (recipientAccount != null) {
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        account.transfer(recipientAccount, transferAmount);
                    } else {
                        System.out.println("Invalid recipient account number");
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        System.out.println("Thank you for using the ATM. Goodbye!");
    }

    public static void main(String[] args) {
        ATMInterface atm = new ATMInterface();
        atm.start();
    }
}
