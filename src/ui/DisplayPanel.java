package ui;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {

    private JTextField displayField;
    private JLabel errorLabel;

    public DisplayPanel() {
        setLayout(new BorderLayout());

        // Display field for expressions/results
        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setBackground(Color.WHITE);
        displayField.setPreferredSize(new Dimension(400, 50));
        add(displayField, BorderLayout.CENTER);

        // Error label (below the display field)
        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        errorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(errorLabel, BorderLayout.SOUTH);
    }

    // Set the text shown in the main display
    public void setDisplayText(String text) {
        displayField.setText(text);
    }

    public String getDisplayText() {
        return displayField.getText();
    }

    public JTextField getDisplayField() {
        return displayField;
    }

    // Show an error message in red
    public void showError(String message) {
        errorLabel.setText(message);
    }

    // Clear any existing error message
    public void clearError() {
        errorLabel.setText(" ");
    }
}
