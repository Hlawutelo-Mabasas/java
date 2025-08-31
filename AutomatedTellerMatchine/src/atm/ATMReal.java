package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class ATMReal extends JFrame {

    private final CardLayout cards = new CardLayout();
    private final JPanel      base = new JPanel(cards);

    /* ---------- simple model ---------- */
    private static final class Account {
        private double balance = 1500.00;
        boolean checkPin(String p) { return "1234".equals(p); }
        void deposit(double d)   { balance += d; }
        boolean withdraw(double w) {
            if (w <= 0 || w > balance) return false;
            balance -= w; return true;
        }
        double getBal() { return balance; }
    }
    private final Account acc = new Account();

    /* ---------- screens ---------- */
    private JPasswordField pinField;
    private JLabel         balLabel;
    private JTextField     amtField;
    private JLabel         status;

    public ATMReal() {
        super("Skye Bank ATM");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        base.add(pinScreen(),  "PIN");
        base.add(menuScreen(),  "MENU");
        base.add(balScreen(),  "BAL");
        base.add(depositScreen(),"DEP");
        base.add(withdrawScreen(),"WIT");
        add(base);
        cards.show(base, "PIN");
    }

    /* ---------- PIN screen ---------- */
    private JPanel pinScreen() {
        JPanel p = new JPanel(new BorderLayout());
        pinField = new JPasswordField(4);
        pinField.setHorizontalAlignment(SwingConstants.CENTER);
        pinField.setFont(new Font("Monospaced", Font.BOLD, 28));

        JButton ok = new JButton("✓ Enter");
        ok.addActionListener(e -> {
            if (acc.checkPin(new String(pinField.getPassword()))) {
                cards.show(base, "MENU");
                pinField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Wrong PIN");
            }
        });

        p.add(new JLabel("Enter 4-digit PIN", SwingConstants.CENTER), BorderLayout.NORTH);
        p.add(pinField, BorderLayout.CENTER);
        p.add(ok, BorderLayout.SOUTH);
        return p;
    }

    /* ---------- menu ---------- */
    private JPanel menuScreen() {
        JPanel p = new JPanel(new GridLayout(4,1,5,5));
        JButton b1 = new JButton("Balance Inquiry");
        JButton b2 = new JButton("Deposit");
        JButton b3 = new JButton("Withdraw");
        JButton b4 = new JButton("Exit");

        b1.addActionListener(e -> showBalance());
        b2.addActionListener(e -> { amtField.setText(""); cards.show(base, "DEP"); });
        b3.addActionListener(e -> { amtField.setText(""); cards.show(base, "WIT"); });
        b4.addActionListener(e -> System.exit(0));

        p.add(b1); p.add(b2); p.add(b3); p.add(b4);
        return p;
    }

    /* ---------- balance ---------- */
    private void showBalance() {
        balLabel.setText(String.format("Current balance: R%.2f", acc.getBal()));
        cards.show(base, "BAL");
    }
    private JPanel balScreen() {
        JPanel p = new JPanel(new BorderLayout());
        balLabel = new JLabel("", SwingConstants.CENTER);
        p.add(balLabel, BorderLayout.CENTER);
        JButton back = new JButton("Back");
        back.addActionListener(e -> cards.show(base, "MENU"));
        p.add(back, BorderLayout.SOUTH);
        return p;
    }

    /* ---------- deposit ---------- */
    private JPanel depositScreen() {
        return txnPanel("Deposit", amt -> {
            acc.deposit(amt);
            JOptionPane.showMessageDialog(this, "Deposit successful");
            cards.show(base, "MENU");
        });
    }

    /* ---------- withdraw ---------- */
    private JPanel withdrawScreen() {
        return txnPanel("Withdraw", amt -> {
            if (acc.withdraw(amt)) {
                JOptionPane.showMessageDialog(this, "Please take your cash");
            } else {
                JOptionPane.showMessageDialog(this, "Withdrawal failed");
            }
            cards.show(base, "MENU");
        });
    }

    /* ---------- generic txn panel with numpad ---------- */
    private JPanel txnPanel(String title, TxnAction act) {
        JPanel p = new JPanel(new BorderLayout(5,5));

        amtField = new JTextField(12);
        amtField.setHorizontalAlignment(SwingConstants.RIGHT);
        amtField.setFont(new Font("Monospaced", Font.BOLD, 24));

        status = new JLabel("Enter amount", SwingConstants.CENTER);

        JPanel num = new JPanel(new GridLayout(4,3,5,5));
        String[] keys = {"7","8","9","4","5","6","1","2","3","0",".","⌫"};
        for (String k : keys) {
            JButton b = new JButton(k);
            b.setFont(new Font("Monospaced", Font.BOLD, 20));
            b.addActionListener(e -> keyPressed(k));
            num.add(b);
        }

        JButton ok = new JButton(title);
        ok.addActionListener(e -> {
            try {
                double val = parseMoney(amtField.getText());
                if (val <= 0) throw new NumberFormatException();
                act.run(val);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid positive number");
            }
        });
        JButton back = new JButton("Back");
        back.addActionListener(e -> cards.show(base, "MENU"));

        JPanel south = new JPanel();
        south.add(ok); south.add(back);

        p.add(status, BorderLayout.NORTH);
        p.add(amtField, BorderLayout.CENTER);
        p.add(num, BorderLayout.EAST);
        p.add(south, BorderLayout.SOUTH);
        return p;
    }

    /* ---------- numpad helper ---------- */
    private void keyPressed(String k) {
        String txt = amtField.getText();
        if ("⌫".equals(k)) {
            if (!txt.isEmpty()) amtField.setText(txt.substring(0, txt.length()-1));
        } else {
            amtField.setText(txt + k);
        }
    }

    /* ---------- safe money parser ---------- */
    private double parseMoney(String s) {
        s = s.replaceAll("\\s+","").replace(',', '.');
        NumberFormat nf = NumberFormat.getInstance();
        ParsePosition pp = new ParsePosition(0);
        Number n = nf.parse(s, pp);
        if (n == null || pp.getIndex() < s.length()) throw new NumberFormatException();
        return n.doubleValue();
    }

    @FunctionalInterface interface TxnAction { void run(double amt); }

    /* ---------- launch ---------- */
    public static void main(String[] a) {
        SwingUtilities.invokeLater(() -> new ATMReal().setVisible(true));
    }
}