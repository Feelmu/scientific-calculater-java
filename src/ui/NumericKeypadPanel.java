package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NumericKeypadPanel extends JPanel {

    private JButton[] digitButtons;
    private JButton decimalButton;
    private JButton plusMinusButton;

    public NumericKeypadPanel() {
        setLayout(new GridLayout(4, 3, 5, 5)); // 4 rows x 3 columns grid
        setBackground(new Color(34, 34, 34));

        digitButtons = new JButton[10];
        for (int i = 0; i <= 9; i++) {
            digitButtons[i] = createButton(String.valueOf(i));
        }

        decimalButton = createButton(".");
        plusMinusButton = createButton("+/-");

        // Add buttons to layout in calculator numeric keypad order
        // Row 1: 7 8 9
        // Row 2: 4 5 6
        // Row 3: 1 2 3
        // Row 4: +/- 0 .
        add(digitButtons[7]);
        add(digitButtons[8]);
        add(digitButtons[9]);

        add(digitButtons[4]);
        add(digitButtons[5]);
        add(digitButtons[6]);

        add(digitButtons[1]);
        add(digitButtons[2]);
        add(digitButtons[3]);

        add(plusMinusButton);
        add(digitButtons[0]);
        add(decimalButton);
    }

    // Helper method to create a styled button
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(60, 63, 65));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    // Set button colors for dark mode or theming
    public void setButtonColors(Color bg, Color fg) {
        for (Component c : getComponents()) {
            if (c instanceof JButton) {
                c.setBackground(bg);
                c.setForeground(fg);
            }
        }
    }

    // Add action listeners to digit buttons 0-9
    public void addDigitListener(ActionListener listener) {
        for (JButton btn : digitButtons) {
            btn.addActionListener(listener);
        }
    }

    // Add listener for decimal point button
    public void addDecimalListener(ActionListener listener) {
        decimalButton.addActionListener(listener);
    }

    // Add listener for plus-minus toggle button
    public void addPlusMinusListener(ActionListener listener) {
        plusMinusButton.addActionListener(listener);
    }


    public void removeParenthesesAndPercentButtons() {
    }
}
