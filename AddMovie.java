package MVBE;

import MVBE.MovieManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddMovie extends JFrame {
    private JTextField movieIdField;
    private JTextField titleField;
    private JTextField yearField;
    private JTextField directorField;
    private JButton submitButton;

    public AddMovie() {
        setTitle("Add Movie");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel to hold the input fields with padding
        JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
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
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieManager m = null;
                try {
                     m = new   MovieManager("root","Tnight2Sky@1stEarth","jdbc:mysql://Pheggiger-1:3306/moviedb");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                // Validate Movie ID as an integer
                int movieId;
                int year;
                try {
                    movieId = Integer.parseInt(movieIdField.getText());
                    year = Integer.parseInt(yearField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Movie ID or year must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get other input values
                String title = titleField.getText();
                String director = directorField.getText();

                // Validate other fields (simple validation)
                if (title.isEmpty() ||  director.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Process the data (for now just show it)
                    JOptionPane.showMessageDialog(null, "Movie Added!\nID: " + movieId + "\nTitle: " + title + "\nYear: " + year + "\nDirector: " + director);
                    dispose(); // Close the "Add Movie" form
                }
                m.addMovie(movieId,title,year,director);
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
        SwingUtilities.invokeLater(() -> new AddMovie());
    }
}
