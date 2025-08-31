
package vut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CapitecChatBot extends JFrame {
    private JPanel chatPanel;
    private JTextField inputField;

    // Capitec colors
    private final Color capitecGreen = new Color(0, 152, 70);
    private final Color capitecLightGreen = new Color(200, 240, 220);
    private final Color capitecDarkGreen = new Color(0, 100, 40);

    public CapitecChatBot() {
        setTitle("Capitec Virtual Assistant");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Chat area
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(chatPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Input area
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton sendButton = new JButton("Send");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Quick Actions
        JPanel quickActionsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        String[] actions = {"Check Balance", "Transfer Money", "Buy Airtime", "View History"};
        for (String action : actions) {
            JButton btn = new JButton(action);
            btn.setBackground(Color.WHITE);
            btn.setForeground(capitecGreen);
            btn.addActionListener(e -> addUserMessage(action));
            quickActionsPanel.add(btn);
        }
        add(quickActionsPanel, BorderLayout.NORTH);

        // Send button action
        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());

        setVisible(true);

        // Welcome message
        addBotMessage("Hello 👋, I'm your Capitec Assistant.\nHow can I help you today?");
    }

    private void sendMessage() {
        String text = inputField.getText().trim();
        if (!text.isEmpty()) {
            addUserMessage(text);
            inputField.setText("");
            respondToUser(text);
        }
    }

    private void addUserMessage(String text) {
        JLabel label = new JLabel("<html><p style='width:200px;'>" + text + "</p></html>");
        label.setOpaque(true);
        label.setBackground(capitecGreen);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        wrapper.setBackground(Color.WHITE);
        wrapper.add(label);

        chatPanel.add(wrapper);
        chatPanel.revalidate();
    }

    private void addBotMessage(String text) {
        JLabel label = new JLabel("<html><p style='width:200px;'>" + text + "</p></html>");
        label.setOpaque(true);
        label.setBackground(capitecLightGreen);
        label.setForeground(Color.BLACK);
        label.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapper.setBackground(Color.WHITE);
        wrapper.add(label);

        chatPanel.add(wrapper);
        chatPanel.revalidate();
    }

    private void respondToUser(String text) {
        // Very simple rules (you can expand later)
        if (text.equalsIgnoreCase("check balance")) {
            addBotMessage("To check your balance: Open the Capitec app → Tap 'Transact' → Select 'Balances'.");
        } else if (text.equalsIgnoreCase("transfer money")) {
            addBotMessage("To transfer money: Go to 'Transact' → 'Payments' → Enter details → Confirm.");
        } else if (text.equalsIgnoreCase("buy airtime")) {
            addBotMessage("To buy airtime: Open the app → 'Transact' → 'Buy' → 'Airtime' → Choose amount.");
        } else if (text.equalsIgnoreCase("view history")) {
            addBotMessage("To view transaction history: Tap 'Savings Account' → 'Transactions'.");
        } else {
            addBotMessage("Sorry, I didn’t understand. Try asking: 'Check Balance', 'Transfer Money', 'Buy Airtime', or 'View History'.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CapitecChatBot::new);
    }
}
