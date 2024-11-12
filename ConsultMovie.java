package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultMovie extends JFrame {
    private JTextField reviewerIdField;
    private JTextField movieIdField;
    private JTextField starsField;
    private JButton submitButton;

    public ConsultMovie() {
        setTitle("Consult Movie");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel to hold the input fields with padding
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Reviewer ID
        fieldsPanel.add(new JLabel("Reviewer ID:"));
        reviewerIdField = new JTextField(15);
        fieldsPanel.add(reviewerIdField);

        // Movie ID
        fieldsPanel.add(new JLabel("Movie ID:"));
        movieIdField = new JTextField(15);
        fieldsPanel.add(movieIdField);

        // Stars (Rating)
        fieldsPanel.add(new JLabel("Stars (Rating):"));
        starsField = new JTextField(15);
        fieldsPanel.add(starsField);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve input values
                String reviewerId = reviewerIdField.getText();
                String movieId = movieIdField.getText();
                String stars = starsField.getText();

                // Basic validation
                if (reviewerId.isEmpty() || movieId.isEmpty() || stars.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // For now, display the input data
                    JOptionPane.showMessageDialog(null, "Consultation Requested!\nReviewer ID: " + reviewerId + "\nMovie ID: " + movieId + "\nStars: " + stars);
                    dispose(); // Close the "Consult Movie" form
                }
            }
        });

        // Panel for button with padding
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        buttonPanel.add(submitButton);

        // Add components to main frame
        setLayout(new BorderLayout());
        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultMovie());
    }
}

