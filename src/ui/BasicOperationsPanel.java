package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BasicOperationsPanel extends JPanel {

    private JButton addButton, subtractButton, multiplyButton, divideButton;
    private JButton equalsButton, clearButton;

    public BasicOperationsPanel() {
        setLayout(new GridLayout(6, 1, 5, 5));
        setBackground(new Color(34, 34, 34));

        addButton = createButton("+");
        subtractButton = createButton("-");
        multiplyButton = createButton("×");
        divideButton = createButton("÷");
        equalsButton = createButton("=");
        clearButton = createButton("C");

        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);
        add(equalsButton);
        add(clearButton);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setFocusPainted(false);
        return btn;
    }

    public void setButtonColors(Color bg, Color fg) {
        JButton[] buttons = {addButton, subtractButton, multiplyButton, divideButton, equalsButton, clearButton};
        for (JButton btn : buttons) {
            btn.setBackground(bg);
            btn.setForeground(fg);
        }
    }

    // Getters for adding to container in MainFrame
    public JButton getAddButton() { return addButton; }
    public JButton getSubtractButton() { return subtractButton; }
    public JButton getMultiplyButton() { return multiplyButton; }
    public JButton getDivideButton() { return divideButton; }
    public JButton getEqualsButton() { return equalsButton; }
    public JButton getClearButton() { return clearButton; }

    // Listeners
    public void addAddListener(ActionListener l) { addButton.addActionListener(l); }
    public void addSubtractListener(ActionListener l) { subtractButton.addActionListener(l); }
    public void addMultiplyListener(ActionListener l) { multiplyButton.addActionListener(l); }
    public void addDivideListener(ActionListener l) { divideButton.addActionListener(l); }
    public void addEqualsListener(ActionListener l) { equalsButton.addActionListener(l); }
    public void addClearListener(ActionListener l) { clearButton.addActionListener(l); }
}
