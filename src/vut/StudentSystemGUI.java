package vut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class StudentSystemGUI extends JFrame {
    private JTextField nameField, emailField, pinField, loginIdField, loginPinField;
    private JTextArea displayArea;
    private HashMap<String, Student> students = new HashMap<>();
    private final String fileName = "students.txt";

    public StudentSystemGUI() {
        setTitle("VUT Registration System");
        setSize(550, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        loadStudentsFromFile();

        // Top Input Panel (Registration)
        JPanel regPanel = new JPanel(new GridLayout(4, 2));
        regPanel.setBorder(BorderFactory.createTitledBorder("Register New Student"));

        regPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        regPanel.add(nameField);

        regPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        regPanel.add(emailField);

        regPanel.add(new JLabel("PIN (4 digits):"));
        pinField = new JTextField();
        regPanel.add(pinField);

        JButton registerButton = new JButton("Register");
        regPanel.add(registerButton);

        JButton showButton = new JButton("Show All");
        regPanel.add(showButton);

        add(regPanel, BorderLayout.NORTH);

        // Center Display
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Bottom Login Panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));

        loginPanel.add(new JLabel("Student Number or Email:"));
        loginIdField = new JTextField();
        loginPanel.add(loginIdField);

        loginPanel.add(new JLabel("PIN:"));
        loginPinField = new JTextField();
        loginPanel.add(loginPinField);

        JButton loginButton = new JButton("Login");
        loginPanel.add(loginButton);

        add(loginPanel, BorderLayout.SOUTH);

        // Button actions
        registerButton.addActionListener(e -> registerStudent());
        showButton.addActionListener(e -> showAllStudents());
        loginButton.addActionListener(e -> loginStudent());

        setVisible(true);
    }

    private void registerStudent() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim().toLowerCase();
        String pin = pinField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            JOptionPane.showMessageDialog(this, "Invalid email format.");
            return;
        }

        if (!pin.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(this, "PIN must be 4 digits.");
            return;
        }

        if (students.containsKey(email) || students.values().stream().anyMatch(s -> s.getPin().equals(pin))) {
            JOptionPane.showMessageDialog(this, "Email or PIN already registered.");
            return;
        }

        String studentNumber = generateStudentNumber();
        Student student = new Student(studentNumber, name, email, pin);
        students.put(email, student);
        students.put(studentNumber, student);

        saveStudentsToFile();
        JOptionPane.showMessageDialog(this, "✅ Registered!\nStudent Number: " + studentNumber);
        nameField.setText(""); emailField.setText(""); pinField.setText("");
    }

    private void loginStudent() {
        String id = loginIdField.getText().trim();
        String pin = loginPinField.getText().trim();

        if (!students.containsKey(id)) {
            JOptionPane.showMessageDialog(this, "Student not found.");
            return;
        }

        Student s = students.get(id);
        if (s.getPin().equals(pin)) {
            displayArea.setText("🔓 Login Successful!\n\n" + s.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect PIN.");
        }

        loginIdField.setText(""); loginPinField.setText("");
    }

    private void showAllStudents() {
        displayArea.setText("");
        Set<String> seen = new HashSet<>();
        for (Student s : students.values()) {
            if (!seen.contains(s.getStudentNumber())) {
                displayArea.append(s.toString() + "\n\n");
                seen.add(s.getStudentNumber());
            }
        }
    }

    private String generateStudentNumber() {
        Random rand = new Random();
        String num;
        do {
            num = String.valueOf(100000000 + rand.nextInt(900000000)); // 9-digit
        } while (students.containsKey(num));
        return num;
    }

    private void saveStudentsToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            Set<String> saved = new HashSet<>();
            for (Student s : students.values()) {
                if (!saved.contains(s.getStudentNumber())) {
                    out.println(s.toFileString());
                    saved.add(s.getStudentNumber());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "❌ Failed to save.");
        }
    }

    private void loadStudentsFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student s = Student.fromFileString(line);
                students.put(s.getEmail().toLowerCase(), s);
                students.put(s.getStudentNumber(), s);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "⚠️ Failed to load existing data.");
        }
    }

    public static void main(String[] args) {
        new StudentSystemGUI();
    }
}

