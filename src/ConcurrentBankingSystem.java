import java.util.Scanner;

public class ConcurrentBankingSystem {
    public static void main(String[] args) {
        // Initialize accounts
        BankAccount account1 = new BankAccount(1, 500);
        BankAccount account2 = new BankAccount(2, 1000);
        BankAccount account3 = new BankAccount(3, 1500);

        // Menu-driven system
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n========= Banking System Menu =========");
            System.out.println("1. View Account Balances");
            System.out.println("2. Transfer Money");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // View Account Balances
                    System.out.println("\nAccount Balances:");
                    System.out.println("Account 1: $" + account1.getBalance());
                    System.out.println("Account 2: $" + account2.getBalance());
                    System.out.println("Account 3: $" + account3.getBalance());
                    break;

                case 2: // Transfer Money
                    System.out.println("\nEnter source account (1, 2, or 3): ");
                    int fromAccountId = getValidAccountId(scanner);
                    System.out.println("Enter destination account (1, 2, or 3): ");
                    int toAccountId = getValidAccountId(scanner);

                    if (fromAccountId == toAccountId) {
                        System.out.println("Source and destination accounts cannot be the same. Please try again.");
                        break;
                    }

                    System.out.println("Enter amount to transfer: ");
                    double amount = scanner.nextDouble();

                    BankAccount fromAccount = getAccountById(fromAccountId, account1, account2, account3);
                    BankAccount toAccount = getAccountById(toAccountId, account1, account2, account3);

                    Thread transferThread = new Thread(() -> TransactionManager.transfer(fromAccount, toAccount, amount));
                    transferThread.start();

                    // Wait for the transaction to complete before returning to the menu
                    try {
                        transferThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3: // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Helper method to validate account input
    private static int getValidAccountId(Scanner scanner) {
        while (true) {
            int accountId = scanner.nextInt();
            if (accountId >= 1 && accountId <= 3) {
                return accountId;
            }
            System.out.println("Invalid account ID. Please enter a valid account (1, 2, or 3): ");
        }
    }

    // Helper method to get the account by ID
    private static BankAccount getAccountById(int accountId, BankAccount... accounts) {
        for (BankAccount account : accounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        return null;
    }
}
