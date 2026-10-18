/**
 * CheckingAccount inherits from BankAccount.
 * Demonstrates Inheritance and Polymorphism.
 * Includes overdraft fee concept.
 */
public class CheckingAccount extends BankAccount {
    private double overdraftLimit;
    private static final double OVERDRAFT_FEE = 25.00;

    public CheckingAccount(String accountNumber, String accountHolder, double balance, double overdraftLimit) {
        super(accountNumber, accountHolder, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    // Polymorphism: different withdraw behavior
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }

        if (getBalance() >= amount) {
            setBalance(getBalance() - amount);
            return true;
        } else if (getBalance() + overdraftLimit >= amount) {
            // Allow overdraft and charge fee
            setBalance(getBalance() - amount - OVERDRAFT_FEE);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("=== Checking Account ===");
        System.out.println(toString());
        System.out.println("Overdraft Limit: $" + String.format("%.2f", overdraftLimit));
    }

    @Override
    public double calculateInterest() {
        // Checking accounts typically earn little or no interest
        return 0.0;
    }
}