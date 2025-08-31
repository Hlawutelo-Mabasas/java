package vut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibrarySystem extends JFrame {
    private ArrayList<Book> books = new ArrayList<>();
    private JTextArea displayArea;
    private JTextField idField, titleField, authorField;
    
    public LibrarySystem() {
        setTitle("Library Book Lending System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("ISBN:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        inputPanel.add(authorField);

        JButton addButton = new JButton("Add Book");
        inputPanel.add(addButton);

        JButton showButton = new JButton("Show Books");
        inputPanel.add(showButton);

        add(inputPanel, BorderLayout.NORTH);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Action Panel
        JPanel actionPanel = new JPanel();
        JButton borrowButton = new JButton("Borrow Book");
        JButton returnButton = new JButton("Return Book");
        actionPanel.add(borrowButton);
        actionPanel.add(returnButton);
        add(actionPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> addBook());
        showButton.addActionListener(e -> showBooks());
        borrowButton.addActionListener(e -> borrowBook());
        returnButton.addActionListener(e -> returnBook());

        setVisible(true);
    }

    private void addBook() {
        String id = idField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        if (id.isEmpty() || title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }
        books.add(new Book(id, title, author));
        JOptionPane.showMessageDialog(this, "Book added!");
        idField.setText(""); titleField.setText(""); authorField.setText("");
    }

    private void showBooks() {
        displayArea.setText("");
        for (Book b : books) {
            displayArea.append(b.toString() + "\n");
        }
    }

    private void borrowBook() {
        String id = JOptionPane.showInputDialog("Enter Book ISBN to borrow:");
        for (Book b : books) {
            if (b.getId().equals(id)) {
                if (!b.isBorrowed()) {
                    b.borrow();
                    JOptionPane.showMessageDialog(this, "Book borrowed.");
                } else {
                    JOptionPane.showMessageDialog(this, "Book is already borrowed.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Book not found.");
    }

    private void returnBook() {
        String id = JOptionPane.showInputDialog("Enter Book ISBN to return:");
        for (Book b : books) {
            if (b.getId().equals(id)) {
                if (b.isBorrowed()) {
                    b.returnBook();
                    JOptionPane.showMessageDialog(this, "Book returned.");
                } else {
                    JOptionPane.showMessageDialog(this, "Book wasn't borrowed.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Book not found.");
    }

    public static void main(String[] args) {
        new LibrarySystem();
    }
}

