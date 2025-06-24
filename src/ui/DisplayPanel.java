package ui;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {

    private JTextField displayField;

    public DisplayPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(34, 34, 34));

        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setBackground(new Color(50, 50, 50));
        displayField.setForeground(Color.WHITE);
        displayField.setPreferredSize(new Dimension(350, 50));
        add(displayField, BorderLayout.CENTER);
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
}
