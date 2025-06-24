package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NumericKeypadPanel extends JPanel {

    private JButton[] digitButtons;
    private JButton decimalButton;
    private JButton plusMinusButton;

    public NumericKeypadPanel() {
        setLayout(new GridLayout(4, 3, 5, 5));
        setBackground(new Color(34, 34, 34));

        digitButtons = new JButton[10];
        for (int i = 1; i <= 9; i++) {
            digitButtons[i] = createButton(String.valueOf(i));
            add(digitButtons[i]);
        }

        plusMinusButton = createButton("+/-");
        add(plusMinusButton);

        digitButtons[0] = createButton("0");
        add(digitButtons[0]);

        decimalButton = createButton(".");
        add(decimalButton);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setFocusPainted(false);
        return btn;
    }

    public void addDigitListener(ActionListener listener) {
        for (int i = 0; i <= 9; i++) {
            digitButtons[i].addActionListener(listener);
        }
    }

    public void addDecimalListener(ActionListener listener) {
        decimalButton.addActionListener(listener);
    }

    public void addPlusMinusListener(ActionListener listener) {
        plusMinusButton.addActionListener(listener);
    }

    public void setButtonColors(Color bg, Color fg) {
        for (int i = 0; i <= 9; i++) {
            digitButtons[i].setBackground(bg);
            digitButtons[i].setForeground(fg);
        }
        decimalButton.setBackground(bg);
        decimalButton.setForeground(fg);
        plusMinusButton.setBackground(bg);
        plusMinusButton.setForeground(fg);
    }
}
