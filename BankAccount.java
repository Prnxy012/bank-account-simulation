/**
 * Abstract base class representing a bank account.
 * Demonstrates Abstraction and Encapsulation.
 */
public abstract class BankAccount {
    // Encapsulation: private fields
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Getters (Encapsulation)
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    // Protected setter for balance so subclasses can modify it safely
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    // Common operations
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public abstract boolean withdraw(double amount); // Abstraction

    public abstract void displayAccountInfo(); // Abstraction

    public abstract double calculateInterest(); // Abstraction

    @Override
    public String toString() {
        return "Account Number: " + accountNumber +
               " | Holder: " + accountHolder +
               " | Balance: $" + String.format("%.2f", balance);
    }
}