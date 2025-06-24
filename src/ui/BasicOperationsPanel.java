package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BasicOperationsPanel extends JPanel {

    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton equalsButton;
    private JButton clearButton;

    public BasicOperationsPanel() {
        setLayout(new GridLayout(3, 2, 5, 5));

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("×");
        divideButton = new JButton("÷");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");

        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);
        add(equalsButton);
        add(clearButton);
    }

    public void addAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addSubtractListener(ActionListener listener) {
        subtractButton.addActionListener(listener);
    }

    public void addMultiplyListener(ActionListener listener) {
        multiplyButton.addActionListener(listener);
    }

    public void addDivideListener(ActionListener listener) {
        divideButton.addActionListener(listener);
    }

    public void addEqualsListener(ActionListener listener) {
        equalsButton.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }
}
