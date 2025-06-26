package ui;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {

    private JTextField displayField;
    private JLabel memoryIndicator;

    public DisplayPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(34, 34, 34));

        memoryIndicator = new JLabel(" ");
        memoryIndicator.setForeground(Color.GREEN);
        memoryIndicator.setFont(new Font("Arial", Font.PLAIN, 12));
        memoryIndicator.setHorizontalAlignment(SwingConstants.LEFT);

        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setBackground(new Color(50, 50, 50));
        displayField.setForeground(Color.WHITE);
        displayField.setPreferredSize(new Dimension(350, 50));

        JPanel displayWrapper = new JPanel(new BorderLayout());
        displayWrapper.setBackground(new Color(50, 50, 50));
        displayWrapper.add(memoryIndicator, BorderLayout.WEST);
        displayWrapper.add(displayField, BorderLayout.CENTER);

        add(displayWrapper, BorderLayout.CENTER);
    }

    public void setDisplayText(String text) {
        displayField.setText(text);
    }

    public String getDisplayText() {
        return displayField.getText();
    }

    public JTextField getDisplayField() {
        return displayField;
    }

    public void setErrorFontSize() {
        displayField.setFont(new Font("Arial", Font.BOLD, 14));
    }

    public void setNormalFontSize() {
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
    }

    public void showMemoryIndicator(boolean show) {
        memoryIndicator.setText(show ? "M" : " ");
    }
}
