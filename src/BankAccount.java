import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final String accountNumber;
    private double balance;
    private final List<String> transactionHistory; // To track transactions
    private final ReentrantLock lock;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        balance -= amount;
        transactionHistory.add("Withdrew: " + amount);
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
