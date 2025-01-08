import java.util.concurrent.locks.*;
import java.util.*;

public class BankAccount {
    private final int id;
    private double balance;
    private final Lock lock;
    private final List<String> transactionHistory;

    public BankAccount(int id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
        this.lock = new ReentrantLock(true); // Fair lock for access order
        this.transactionHistory = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: $" + amount);
    }

    public void withdraw(double amount) {
        balance -= amount;
        transactionHistory.add("Withdrew: $" + amount);
    }

    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
}