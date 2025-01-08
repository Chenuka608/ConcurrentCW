public class ConcurrentBankingSystem {
    public static void main(String[] args) {
        // Create bank accounts
        BankAccount account1 = new BankAccount("A1", 10000);
        BankAccount account2 = new BankAccount("A2", 10000);
        BankAccount account3 = new BankAccount("A3", 10000);

        // Create a transaction manager
        TransactionManager transactionManager = new TransactionManager();

        // Simulate concurrent transactions
        Thread thread1 = new Thread(() -> transactionManager.transferMoney(account1, account2, 100));
        Thread thread2 = new Thread(() -> transactionManager.transferMoney(account2, account3, 200));
        Thread thread3 = new Thread(() -> transactionManager.transferMoney(account3, account1, 50));
        Thread thread4 = new Thread(() -> System.out.println("Account 1 Balance: " + account1.getBalance()));
        Thread thread5 = new Thread(() -> System.out.println("Account 3 Balance: " + account3.getBalance()));

        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        // Wait for threads to complete
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final balances
        System.out.println("Final Account Balances:");
        System.out.println("Account 1: " + account1.getBalance());
        System.out.println("Account 2: " + account2.getBalance());
        System.out.println("Account 3: " + account3.getBalance());

        // Print transaction history for each account
        System.out.println("\nTransaction History for Account 1:");
        account1.getTransactionHistory().forEach(System.out::println);

        System.out.println("\nTransaction History for Account 2:");
        account2.getTransactionHistory().forEach(System.out::println);

        System.out.println("\nTransaction History for Account 3:");
        account3.getTransactionHistory().forEach(System.out::println);
    }
}
