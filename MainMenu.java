package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JButton addMovieButton;
    private JButton updateButton;
    private JButton consultButton;
    private JButton exitButton;

    public MainMenu() {
        setTitle("Main Menu");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel to hold buttons with padding
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add Movie Button
        addMovieButton = new JButton("Add Movie");
        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMovie(); // Open the AddMovie form when the button is clicked
            }
        });

        // Update Button
        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Update button clicked!");
                // Add functionality for updating here
            }
        });

        // Consult Button
        consultButton = new JButton("Consult");
        consultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Consult button clicked!");
                // Add functionality for consulting here
            }
        });

        // Exit Button (Added for closing the application)
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        consultButton = new JButton("Consult");
        consultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultMovie(); // Opens the ConsultMovie window
            }
        });

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateMovie(); // Opens the UpdateMovie window
            }
        });



        // Add buttons to panel
        buttonPanel.add(addMovieButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(consultButton);
        buttonPanel.add(exitButton);

        // Add panel to frame
        add(buttonPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}


