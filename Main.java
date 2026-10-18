import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI version of the Bank Account Simulation.
 * Uses ArrayList for storage + Swing GUI (required).
 */
public class Main extends JFrame implements ActionListener {

    private Bank bank;
    private JTextArea displayArea;

    // Buttons
    private JButton btnCreateSavings, btnCreateChecking, btnDeposit, btnWithdraw;
    private JButton btnDisplay, btnInterest, btnExit;

    public Main() {
        bank = new Bank();

        // Window settings
        setTitle("Basic Bank Account Simulation");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title = new JLabel("Bank Account Simulation", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        displayArea.setText("Welcome!\nClick a button to start.");
        JScrollPane scroll = new JScrollPane(displayArea);
        add(scroll, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2, 8, 8));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

        btnCreateSavings = new JButton("1. Create Savings Account");
        btnCreateChecking = new JButton("2. Create Checking Account");
        btnDeposit = new JButton("3. Deposit Money");
        btnWithdraw = new JButton("4. Withdraw Money");
        btnDisplay = new JButton("5. Display All Accounts");
        btnInterest = new JButton("6. Calculate Interest");
        btnExit = new JButton("7. Exit");

        // Add listeners
        btnCreateSavings.addActionListener(this);
        btnCreateChecking.addActionListener(this);
        btnDeposit.addActionListener(this);
        btnWithdraw.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnInterest.addActionListener(this);
        btnExit.addActionListener(this);

        buttonPanel.add(btnCreateSavings);
        buttonPanel.add(btnCreateChecking);
        buttonPanel.add(btnDeposit);
        buttonPanel.add(btnWithdraw);
        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnInterest);
        buttonPanel.add(btnExit);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCreateSavings) {
            createSavings();
        } else if (e.getSource() == btnCreateChecking) {
            createChecking();
        } else if (e.getSource() == btnDeposit) {
            doDeposit();
        } else if (e.getSource() == btnWithdraw) {
            doWithdraw();
        } else if (e.getSource() == btnDisplay) {
            displayArea.setText(bank.displayAllAccounts());
        } else if (e.getSource() == btnInterest) {
            doInterest();
        } else if (e.getSource() == btnExit) {
            JOptionPane.showMessageDialog(this,
                "Thank you!\nTotal accounts created: " + bank.getAccountCount());
            System.exit(0);
        }
    }

    private void createSavings() {
        try {
            String accNum = JOptionPane.showInputDialog(this, "Enter Account Number:");
            if (accNum == null || accNum.trim().isEmpty()) return;

            String holder = JOptionPane.showInputDialog(this, "Enter Account Holder Name:");
            if (holder == null || holder.trim().isEmpty()) return;

            String balStr = JOptionPane.showInputDialog(this, "Enter Initial Balance:");
            double balance = Double.parseDouble(balStr);

            String rateStr = JOptionPane.showInputDialog(this, "Enter Interest Rate (e.g. 0.03 for 3%):");
            double rate = Double.parseDouble(rateStr);

            bank.createSavingsAccount(accNum, holder, balance, rate);
            displayArea.setText("Savings Account created successfully!\n\nAccount Number: " + accNum +
                                "\nHolder: " + holder + "\nBalance: $" + String.format("%.2f", balance));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
        }
    }

    private void createChecking() {
        try {
            String accNum = JOptionPane.showInputDialog(this, "Enter Account Number:");
            if (accNum == null || accNum.trim().isEmpty()) return;

            String holder = JOptionPane.showInputDialog(this, "Enter Account Holder Name:");
            if (holder == null || holder.trim().isEmpty()) return;

            String balStr = JOptionPane.showInputDialog(this, "Enter Initial Balance:");
            double balance = Double.parseDouble(balStr);

            String limitStr = JOptionPane.showInputDialog(this, "Enter Overdraft Limit:");
            double limit = Double.parseDouble(limitStr);

            bank.createCheckingAccount(accNum, holder, balance, limit);
            displayArea.setText("Checking Account created successfully!\n\nAccount Number: " + accNum +
                                "\nHolder: " + holder + "\nBalance: $" + String.format("%.2f", balance));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
        }
    }

    private void doDeposit() {
        try {
            String accNum = JOptionPane.showInputDialog(this, "Enter Account Number:");
            if (accNum == null) return;

            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            double amount = Double.parseDouble(amountStr);

            String result = bank.deposit(accNum, amount);
            displayArea.setText(result);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void doWithdraw() {
        try {
            String accNum = JOptionPane.showInputDialog(this, "Enter Account Number:");
            if (accNum == null) return;

            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            double amount = Double.parseDouble(amountStr);

            String result = bank.withdraw(accNum, amount);
            displayArea.setText(result);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void doInterest() {
        String accNum = JOptionPane.showInputDialog(this, "Enter Account Number:");
        if (accNum == null) return;

        String result = bank.calculateInterest(accNum);
        displayArea.setText(result);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        new Main();
    }
}