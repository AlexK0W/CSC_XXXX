package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateMovie extends JFrame {
    private JTextField movieIdField;
    private JTextField titleField;
    private JTextField yearField;
    private JTextField directorField;
    private JButton submitButton;

    public UpdateMovie() {
        setTitle("Update Movie");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel to hold the input fields with padding
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Movie ID
        fieldsPanel.add(new JLabel("Movie ID:"));
        movieIdField = new JTextField(15);
        fieldsPanel.add(movieIdField);

        // Title
        fieldsPanel.add(new JLabel("Title:"));
        titleField = new JTextField(15);
        fieldsPanel.add(titleField);

        // Year
        fieldsPanel.add(new JLabel("Year:"));
        yearField = new JTextField(15);
        fieldsPanel.add(yearField);

        // Director
        fieldsPanel.add(new JLabel("Director:"));
        directorField = new JTextField(15);
        fieldsPanel.add(directorField);

        // Submit Button
        submitButton = new JButton("Modify");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve input values
                String movieId = movieIdField.getText();
                String title = titleField.getText();
                String year = yearField.getText();
                String director = directorField.getText();

                // Basic validation
                if (movieId.isEmpty() || title.isEmpty() || year.isEmpty() || director.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // For now, display the input data
                    JOptionPane.showMessageDialog(null, "Movie updated!\nMovie ID: " + movieId + "\nTitle: " + title + "\nYear: " + year + "\nDirector: " + director);
                    dispose(); // Close the "Update Movie" form
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
        SwingUtilities.invokeLater(() -> new UpdateMovie());
    }
}

