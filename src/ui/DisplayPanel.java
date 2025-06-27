package ui;

import javax.swing.*;
import java.awt.*;

/**
 * DisplayPanel shows the current input or result,
 * memory indicator, and degree/radian mode indicator.
 */
public class DisplayPanel extends JPanel {

    private final JTextField displayField;   // Displays current expression/result
    private final JLabel memoryIndicator;    // Shows "M" if memory is active
    private final JLabel modeIndicator;      // Shows DEG or RAD mode

    public DisplayPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(34, 34, 34));

        memoryIndicator = new JLabel(" "); // Initially no memory stored
        memoryIndicator.setForeground(Color.GREEN);
        memoryIndicator.setFont(new Font("Arial", Font.PLAIN, 12));
        memoryIndicator.setHorizontalAlignment(SwingConstants.LEFT);

        modeIndicator = new JLabel("DEG"); // Default angle mode label
        modeIndicator.setForeground(Color.CYAN);
        modeIndicator.setFont(new Font("Arial", Font.PLAIN, 12));
        modeIndicator.setHorizontalAlignment(SwingConstants.RIGHT);
        modeIndicator.setPreferredSize(new Dimension(30, 20));

        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setBackground(new Color(50, 50, 50));
        displayField.setForeground(Color.WHITE);
        displayField.setPreferredSize(new Dimension(350, 50));

        // Wrap memory indicator, display, and mode indicator in one panel
        JPanel displayWrapper = new JPanel(new BorderLayout());
        displayWrapper.setBackground(new Color(50, 50, 50));
        displayWrapper.add(memoryIndicator, BorderLayout.WEST);
        displayWrapper.add(displayField, BorderLayout.CENTER);
        displayWrapper.add(modeIndicator, BorderLayout.EAST);

        add(displayWrapper, BorderLayout.CENTER);
    }

    // Update displayed text (input or result)
    public void setDisplayText(String text) {
        displayField.setText(text);
    }

    // Retrieve current text shown on display
    public String getDisplayText() {
        return displayField.getText();
    }

    // Set display font smaller and text red for error messages
    public void setErrorFontSize() {
        displayField.setFont(new Font("Arial", Font.BOLD, 14));
        displayField.setForeground(Color.RED);
    }

    // Reset display font size and color to normal
    public void setNormalFontSize() {
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setForeground(Color.WHITE);
    }

    // Show or hide memory indicator "M"
    public void showMemoryIndicator(boolean show) {
        memoryIndicator.setText(show ? "M" : " ");
    }

    /**
     * Set the degree/radian mode indicator label.
     * @param degreeMode true = "DEG", false = "RAD"
     */
    public void setDegreeModeIndicator(boolean degreeMode) {
        modeIndicator.setText(degreeMode ? "DEG" : "RAD");
    }
    /**
     * Provides direct access to the internal JTextField.
     */
    public JTextField getDisplayField() {
        return displayField;
    }

}
