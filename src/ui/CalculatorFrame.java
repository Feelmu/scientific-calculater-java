package ui;

import memory.MemoryManager;
import logic.*;
import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {
    private JTextField displayField;
    private JTextArea historyArea;
    private StringBuilder currentInput = new StringBuilder();
    private CalculatorEngine engine = new CalculatorEngine(true);
    private MemoryManager memoryManager = new MemoryManager();
    private boolean isDegree = true;
    private JButton modeButton;

    public CalculatorFrame() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout());

        // Display Panel
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 24));
        historyArea = new JTextArea(3, 20);
        historyArea.setEditable(false);

        modeButton = new JButton("Deg");
        modeButton.addActionListener(e -> toggleMode());

        JPanel topRow = new JPanel(new BorderLayout());
        topRow.add(displayField, BorderLayout.CENTER);
        topRow.add(modeButton, BorderLayout.EAST);

        displayPanel.add(topRow, BorderLayout.NORTH);
        displayPanel.add(new JScrollPane(historyArea), BorderLayout.CENTER);
        add(displayPanel, BorderLayout.NORTH);

        // Main Panels Container
        JPanel centerPanel = new JPanel(new GridLayout(2, 2));
        centerPanel.add(createNumericPanel());
        centerPanel.add(createBasicOperationsPanel());
        centerPanel.add(createScientificPanel());
        centerPanel.add(createMemoryPanel());
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void toggleMode() {
        isDegree = !isDegree;
        engine.setDegreeMode(isDegree);
        modeButton.setText(isDegree ? "Deg" : "Rad");
    }

    private JPanel createNumericPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 3));
        for (int i = 1; i <= 9; i++) addButton(panel, String.valueOf(i));
        addButton(panel, ".");
        addButton(panel, "0");
        addButton(panel, "+/-");
        return panel;
    }

    private JPanel createBasicOperationsPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        addButton(panel, "+");
        addButton(panel, "-");
        addButton(panel, "*");
        addButton(panel, "/");
        addButton(panel, "=");
        addButton(panel, "C");
        return panel;
    }

    private JPanel createScientificPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 3));
        String[] funcs = {"sin", "cos", "tan", "asin", "acos", "atan", "log", "ln", "exp", "10^x", "sqrt", "x^y", "n!"};
        for (String func : funcs) addButton(panel, func);
        return panel;
    }

    private JPanel createMemoryPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3));
        String[] mems = {"MS", "MR", "MC", "M+", "M-"};
        for (String mem : mems) addButton(panel, mem);
        return panel;
    }

    private void addButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        button.addActionListener(e -> handleInput(text));
        panel.add(button);
    }

    private void handleInput(String input) {
        try {
            if (input.equals("C")) {
                currentInput.setLength(0);
                displayField.setText("");
            } else if (input.equals("=")) {
                String expression = currentInput.toString().trim();
                double result = engine.calculate(expression);
                displayField.setText(String.valueOf(result));
                historyArea.append(expression + " = " + result + "\n");
                currentInput.setLength(0);
            } else if (input.equals("MS")) {
                double value = Double.parseDouble(displayField.getText());
                memoryManager.store(value);
            } else if (input.equals("MR")) {
                currentInput.append(memoryManager.recall());
                displayField.setText(currentInput.toString());
            } else if (input.equals("MC")) {
                memoryManager.clear();
            } else if (input.equals("M+")) {
                double value = Double.parseDouble(displayField.getText());
                memoryManager.add(value);
            } else if (input.equals("M-")) {
                double value = Double.parseDouble(displayField.getText());
                memoryManager.subtract(value);
            } else if (input.equals("+/-")) {
                if (displayField.getText().startsWith("-")) {
                    displayField.setText(displayField.getText().substring(1));
                } else {
                    displayField.setText("-" + displayField.getText());
                }
                currentInput.setLength(0);
                currentInput.append(displayField.getText());
            } else {
                // Append number or operator
                if (input.matches("[+\\-*/^%]") || input.matches("[a-zA-Z]+") || input.equals("10^x")) {
                    currentInput.append(" ").append(input).append(" ");
                } else {
                    currentInput.append(input);
                }
                displayField.setText(currentInput.toString());
            }
        } catch (Exception e) {
            displayField.setText("Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorFrame::new);
    }
}
