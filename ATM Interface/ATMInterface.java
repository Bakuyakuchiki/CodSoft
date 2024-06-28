import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface extends JFrame {
    private ATM atm;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton balanceButton;
    private JTextArea messageArea;

    public ATMInterface(ATM atm) {
        this.atm = atm;
        createUI();
    }

    private void createUI() {
        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        balanceLabel = new JLabel("Current Balance: $" + atm.checkBalance());
        amountField = new JTextField();

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        balanceButton = new JButton("Check Balance");

        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(balanceButton);
        panel.add(balanceLabel);

        messageArea = new JTextArea();
        messageArea.setEditable(false);

        add(panel, BorderLayout.CENTER);
        add(new JScrollPane(messageArea), BorderLayout.SOUTH);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    atm.deposit(amount);
                    balanceLabel.setText("Current Balance: $" + atm.checkBalance());
                    messageArea.append("Deposited: $" + amount + "\n");
                } catch (NumberFormatException ex) {
                    messageArea.append("Invalid amount\n");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (atm.withdraw(amount)) {
                        balanceLabel.setText("Current Balance: $" + atm.checkBalance());
                        messageArea.append("Withdrew: $" + amount + "\n");
                    } else {
                        messageArea.append("Insufficient funds or invalid amount\n");
                    }
                } catch (NumberFormatException ex) {
                    messageArea.append("Invalid amount\n");
                }
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                balanceLabel.setText("Current Balance: $" + atm.checkBalance());
                messageArea.append("Checked balance\n");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BankAccount userAccount = new BankAccount(1000.00);
                ATM atm = new ATM(userAccount);
                ATMInterface atmInterface = new ATMInterface(atm);
                atmInterface.setVisible(true);
            }
        });
    }
}