import java.util.ArrayList;

/**
 * Bank class manages a collection of accounts using ArrayList (data storage).
 * Demonstrates polymorphism by treating all accounts as BankAccount references.
 */
public class Bank {
    private ArrayList<BankAccount> accounts; // Data storage as required

    public Bank() {
        accounts = new ArrayList<>();
    }

    // Create a new Savings Account
    public void createSavingsAccount(String accNum, String holder, double balance, double rate) {
        SavingsAccount account = new SavingsAccount(accNum, holder, balance, rate);
        accounts.add(account);
    }

    // Create a new Checking Account
    public void createCheckingAccount(String accNum, String holder, double balance, double limit) {
        CheckingAccount account = new CheckingAccount(accNum, holder, balance, limit);
        accounts.add(account);
    }

    // Find account by number (polymorphism: returns BankAccount reference)
    public BankAccount findAccount(String accountNumber) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    // Deposit money
    public String deposit(String accNum, double amount) {
        BankAccount account = findAccount(accNum);
        if (account != null) {
            account.deposit(amount);
            return "Deposit successful!\nNew balance: $" + String.format("%.2f", account.getBalance());
        } else {
            return "Account not found.";
        }
    }

    // Withdraw money (polymorphism in action)
    public String withdraw(String accNum, double amount) {
        BankAccount account = findAccount(accNum);
        if (account != null) {
            boolean success = account.withdraw(amount);
            if (success) {
                return "Withdrawal successful!\nNew balance: $" + String.format("%.2f", account.getBalance());
            } else {
                return "Withdrawal failed.";
            }
        } else {
            return "Account not found.";
        }
    }

    // Display all accounts (polymorphism)
    public String displayAllAccounts() {
        if (accounts.isEmpty()) {
            return "No accounts found.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("===== All Accounts =====\n\n");
        for (BankAccount acc : accounts) {
            sb.append(acc.toString()).append("\n");
            if (acc instanceof SavingsAccount) {
                SavingsAccount sa = (SavingsAccount) acc;
                sb.append("Type: Savings | Interest Rate: ").append(sa.getInterestRate() * 100).append("%\n");
            } else if (acc instanceof CheckingAccount) {
                CheckingAccount ca = (CheckingAccount) acc;
                sb.append("Type: Checking | Overdraft Limit: $").append(String.format("%.2f", ca.getOverdraftLimit())).append("\n");
            }
            sb.append("------------------------\n");
        }
        return sb.toString();
    }

    // Calculate and add interest
    public String calculateInterest(String accNum) {
        BankAccount account = findAccount(accNum);
        if (account != null) {
            double interest = account.calculateInterest();
            if (interest > 0) {
                account.deposit(interest);
                return "Interest calculated: $" + String.format("%.2f", interest) +
                       "\nInterest added!\nNew balance: $" + String.format("%.2f", account.getBalance());
            } else {
                return "This account has no interest (Checking Account).";
            }
        } else {
            return "Account not found.";
        }
    }

    public int getAccountCount() {
        return accounts.size();
    }
}