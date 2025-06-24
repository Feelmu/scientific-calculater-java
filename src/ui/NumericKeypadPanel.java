package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NumericKeypadPanel extends JPanel {

    private JButton[] digitButtons;
    private JButton decimalButton;
    private JButton plusMinusButton;

    public NumericKeypadPanel() {
        setLayout(new GridLayout(4, 3, 5, 5));  // 4 rows, 3 cols

        digitButtons = new JButton[10];
        for (int i = 1; i <= 9; i++) {
            digitButtons[i] = new JButton(String.valueOf(i));
            add(digitButtons[i]);
        }

        plusMinusButton = new JButton("+/-");
        add(plusMinusButton);

        digitButtons[0] = new JButton("0");
        add(digitButtons[0]);

        decimalButton = new JButton(".");
        add(decimalButton);
    }

    // Add listener for digits 0-9
    public void addDigitListener(ActionListener listener) {
        for (int i = 0; i <= 9; i++) {
            digitButtons[i].addActionListener(listener);
        }
    }

    // Listener for decimal point
    public void addDecimalListener(ActionListener listener) {
        decimalButton.addActionListener(listener);
    }

    // Listener for +/- toggle
    public void addPlusMinusListener(ActionListener listener) {
        plusMinusButton.addActionListener(listener);
    }
}

