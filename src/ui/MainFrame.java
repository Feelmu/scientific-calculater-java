package ui;

import logic.CalculatorEngine;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private DisplayPanel displayPanel;
    private MemoryPanel memoryPanel;
    private ScientificPanel scientificPanel;
    private NumericKeypadPanel numericKeypadPanel;
    private BasicOperationsPanel basicOperationsPanel;

    private CalculatorEngine calculatorEngine;
    private StringBuilder inputExpression;
    private boolean isDegreeMode = true;

    public MainFrame() {
        setTitle("Scientific Calculator");
        setSize(360, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        inputExpression = new StringBuilder();
        calculatorEngine = new CalculatorEngine(isDegreeMode);

        displayPanel = new DisplayPanel();
        memoryPanel = new MemoryPanel();
        scientificPanel = new ScientificPanel();
        numericKeypadPanel = new NumericKeypadPanel();
        basicOperationsPanel = new BasicOperationsPanel();

        setLayout(new BorderLayout(5, 5));
        getContentPane().setBackground(new Color(34, 34, 34)); // Dark background

        // Top panel: display + memory
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBackground(new Color(34, 34, 34));
        topPanel.add(displayPanel, BorderLayout.NORTH);
        topPanel.add(memoryPanel, BorderLayout.SOUTH);

        // Center panel: scientific (top), numeric keypad (bottom)
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBackground(new Color(34, 34, 34));
        centerPanel.add(scientificPanel, BorderLayout.NORTH);
        centerPanel.add(numericKeypadPanel, BorderLayout.CENTER);

        // Right panel: basic ops vertical
        JPanel rightPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        rightPanel.setBackground(new Color(34, 34, 34));
        rightPanel.add(basicOperationsPanel.getAddButton());
        rightPanel.add(basicOperationsPanel.getSubtractButton());
        rightPanel.add(basicOperationsPanel.getMultiplyButton());
        rightPanel.add(basicOperationsPanel.getDivideButton());
        rightPanel.add(basicOperationsPanel.getEqualsButton());
        rightPanel.add(basicOperationsPanel.getClearButton());

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        applyDarkMode();

        attachListeners();

        setVisible(true);
    }

    private void applyDarkMode() {
        Color bg = new Color(34, 34, 34);
        Color fg = Color.WHITE;
        Color btnBg = new Color(60, 63, 65);
        Color btnFg = Color.BLACK;

        getContentPane().setBackground(bg);

        displayPanel.setBackground(bg);
        displayPanel.getDisplayField().setBackground(new Color(50, 50, 50));
        displayPanel.getDisplayField().setForeground(fg);

        memoryPanel.setBackground(bg);
        memoryPanel.setButtonColors(btnBg, btnFg);

        scientificPanel.setBackground(bg);
        scientificPanel.setButtonColors(btnBg, btnFg);

        numericKeypadPanel.setBackground(bg);
        numericKeypadPanel.setButtonColors(btnBg, btnFg);

        basicOperationsPanel.setBackground(bg);
        basicOperationsPanel.setButtonColors(btnBg, btnFg);
    }

    private void attachListeners() {
        // Digits 0-9
        numericKeypadPanel.addDigitListener(e -> {
            JButton btn = (JButton) e.getSource();
            inputExpression.append(btn.getText());
            updateDisplay();
        });

        // Decimal point
        numericKeypadPanel.addDecimalListener(e -> {
            String current = inputExpression.toString();
            if (!current.endsWith(".") && !current.contains(".")) {
                inputExpression.append(".");
                updateDisplay();
            }
        });

        // Plus/minus toggle
        numericKeypadPanel.addPlusMinusListener(e -> {
            if (inputExpression.length() > 0 && inputExpression.charAt(0) == '-') {
                inputExpression.deleteCharAt(0);
            } else {
                inputExpression.insert(0, "-");
            }
            updateDisplay();
        });

        // Basic operations
        basicOperationsPanel.addAddListener(e -> appendOperator("+"));
        basicOperationsPanel.addSubtractListener(e -> appendOperator("-"));
        basicOperationsPanel.addMultiplyListener(e -> appendOperator("*"));
        basicOperationsPanel.addDivideListener(e -> appendOperator("/"));
        basicOperationsPanel.addClearListener(e -> {
            inputExpression.setLength(0);
            displayPanel.setDisplayText("");
        });
        basicOperationsPanel.addEqualsListener(e -> calculateResult());

        // Scientific functions
        scientificPanel.addSinListener(e -> applyScientificFunction("sin"));
        scientificPanel.addCosListener(e -> applyScientificFunction("cos"));
        scientificPanel.addTanListener(e -> applyScientificFunction("tan"));
        scientificPanel.addAsinListener(e -> applyScientificFunction("asin"));
        scientificPanel.addAcosListener(e -> applyScientificFunction("acos"));
        scientificPanel.addAtanListener(e -> applyScientificFunction("atan"));
        scientificPanel.addLogListener(e -> applyScientificFunction("log"));
        scientificPanel.addLnListener(e -> applyScientificFunction("ln"));
        scientificPanel.addExpListener(e -> applyScientificFunction("exp"));
        scientificPanel.addTenPowerListener(e -> applyScientificFunction("10^"));
        scientificPanel.addSqrtListener(e -> applyScientificFunction("sqrt"));
        scientificPanel.addPowerListener(e -> appendOperator("^"));
        scientificPanel.addFactorialListener(e -> applyScientificFunction("fact"));

        // Memory buttons (placeholders)
        memoryPanel.addMemoryStoreListener(e -> JOptionPane.showMessageDialog(this, "Memory Store clicked"));
        memoryPanel.addMemoryRecallListener(e -> JOptionPane.showMessageDialog(this, "Memory Recall clicked"));
        memoryPanel.addMemoryClearListener(e -> JOptionPane.showMessageDialog(this, "Memory Clear clicked"));
        memoryPanel.addMemoryAddListener(e -> JOptionPane.showMessageDialog(this, "Memory Add clicked"));
        memoryPanel.addMemorySubtractListener(e -> JOptionPane.showMessageDialog(this, "Memory Subtract clicked"));
    }

    private void appendOperator(String op) {
        if (inputExpression.length() == 0) return;
        char lastChar = inputExpression.charAt(inputExpression.length() - 1);
        if ("+-*/^".indexOf(lastChar) >= 0) {
            inputExpression.setCharAt(inputExpression.length() - 1, op.charAt(0));
        } else {
            inputExpression.append(" ").append(op).append(" ");
        }
        updateDisplay();
    }

    private void applyScientificFunction(String func) {
        try {
            double currentValue = Double.parseDouble(displayPanel.getDisplayText());
            String expression = func + " " + currentValue;
            double result = calculatorEngine.calculate(expression);
            displayPanel.setDisplayText(String.valueOf(result));
            inputExpression.setLength(0);
            inputExpression.append(result);
        } catch (Exception ex) {
            displayPanel.setDisplayText("Error: " + ex.getMessage());
            inputExpression.setLength(0);
        }
    }

    private void calculateResult() {
        try {
            String expr = inputExpression.toString().trim();
            if (expr.isEmpty()) return;
            double result = calculatorEngine.calculate(expr);
            displayPanel.setDisplayText(String.valueOf(result));
            inputExpression.setLength(0);
            inputExpression.append(result);
        } catch (Exception ex) {
            displayPanel.setDisplayText("Error: " + ex.getMessage());
            inputExpression.setLength(0);
        }
    }

    private void updateDisplay() {
        displayPanel.setDisplayText(inputExpression.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
