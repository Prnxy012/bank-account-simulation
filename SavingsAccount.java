/**
 * SavingsAccount inherits from BankAccount.
 * Demonstrates Inheritance and Polymorphism (method overriding).
 */
public class SavingsAccount extends BankAccount {
    private double interestRate; // e.g. 0.03 for 3%

    public SavingsAccount(String accountNumber, String accountHolder, double balance, double interestRate) {
        super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    // Polymorphism: override abstract methods
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && getBalance() >= amount) {
            setBalance(getBalance() - amount);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("=== Savings Account ===");
        System.out.println(toString());
        System.out.println("Interest Rate: " + (interestRate * 100) + "%");
    }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }
}