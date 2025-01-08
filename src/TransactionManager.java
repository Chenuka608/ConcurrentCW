public class TransactionManager {
    public static void transfer(BankAccount from, BankAccount to, double amount) {
        // Ensure consistent lock order to prevent deadlocks
        BankAccount first = from.getId() < to.getId() ? from : to;
        BankAccount second = from.getId() < to.getId() ? to : from;

        first.lock();
        second.lock();
        try {
            // Check for sufficient funds before proceeding
            if (from.getBalance() < amount) {
                System.out.println("Transaction failed: Insufficient funds in Account " + from.getId());
                return; // Exit without making any changes
            }

            // Proceed with the transaction
            from.withdraw(amount);
            to.deposit(amount);
            System.out.println("Transferred $" + amount + " from Account " + from.getId() + " to Account " + to.getId());
        } catch (Exception e) {
            System.out.println("Transaction failed: " + e.getMessage());
            rollback(from, to, amount); // Perform rollback only if necessary
        } finally {
            second.unlock();
            first.unlock();
        }
    }

    private static void rollback(BankAccount from, BankAccount to, double amount) {
        // Reverse transaction only if the amount has been transferred to the destination account
        if (to.getBalance() >= amount) {
            to.withdraw(amount);
            from.deposit(amount);
            System.out.println("Transaction rolled back: $" + amount + " returned to Account " + from.getId());
        }
    }
}
