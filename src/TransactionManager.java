import java.util.concurrent.locks.Lock;

public class TransactionManager {

    public boolean transferMoney(BankAccount from, BankAccount to, double amount) {
        Lock firstLock = from.getLock();
        Lock secondLock = to.getLock();

        // Lock both accounts in a safe order
        if (from.getAccountNumber().compareTo(to.getAccountNumber()) < 0) {
            firstLock.lock();
            secondLock.lock();
        } else {
            secondLock.lock();
            firstLock.lock();
        }

        try {
            // Check for sufficient balance
            if (from.getBalance() < amount) {
                System.out.println("Insufficient balance for transfer.");
                return false;
            }

            // Perform the transfer
            from.withdraw(amount);
            to.deposit(amount);
            System.out.println("Transferred " + amount + " from " + from.getAccountNumber() + " to " + to.getAccountNumber());
            return true;
        } catch (Exception e) {
            System.out.println("Transaction failed, rolling back...");
            // Rollback in case of failure
            rollbackTransaction(from, to, amount);
            return false;
        } finally {
            firstLock.unlock();
            secondLock.unlock();
        }
    }

    private void rollbackTransaction(BankAccount from, BankAccount to, double amount) {
        from.deposit(amount);  // Reversing the withdrawal
        to.withdraw(amount);   // Reversing the deposit
    }
}
