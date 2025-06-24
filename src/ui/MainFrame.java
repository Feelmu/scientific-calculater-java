package ui;

import logic.CalculatorEngine;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private DisplayPanel displayPanel;
    private NumericKeypadPanel numericKeypadPanel;
    private BasicOperationsPanel basicOperationsPanel;
    private ScientificPanel scientificPanel;
    private MemoryPanel memoryPanel;

    private CalculatorEngine calculatorEngine;
    private StringBuilder inputExpression;
    private boolean isDegreeMode = true;

    public MainFrame() {
        setTitle("Scientific Calculator");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        inputExpression = new StringBuilder();
        calculatorEngine = new CalculatorEngine(isDegreeMode);

        displayPanel = new DisplayPanel();
        numericKeypadPanel = new NumericKeypadPanel();
        basicOperationsPanel = new BasicOperationsPanel();
        scientificPanel = new ScientificPanel();
        memoryPanel = new MemoryPanel();

        setLayout(new BorderLayout(5, 5));

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.add(numericKeypadPanel, BorderLayout.CENTER);
        centerPanel.add(basicOperationsPanel, BorderLayout.EAST);

        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.add(scientificPanel, BorderLayout.CENTER);
        rightPanel.add(memoryPanel, BorderLayout.SOUTH);

        add(displayPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        attachListeners();
        setVisible(true);
    }

    private void attachListeners() {
        numericKeypadPanel.addDigitListener(e -> {
            JButton source = (JButton) e.getSource();
            inputExpression.append(source.getText());
            updateDisplay();
            displayPanel.clearError();
        });

        numericKeypadPanel.addDecimalListener(e -> {
            String currentText = inputExpression.toString();
            if (!currentText.endsWith(".") && !currentText.contains(".")) {
                inputExpression.append(".");
                updateDisplay();
                displayPanel.clearError();
            }
        });

        numericKeypadPanel.addPlusMinusListener(e -> {
            if (inputExpression.length() > 0 && inputExpression.charAt(0) == '-') {
                inputExpression.deleteCharAt(0);
            } else {
                inputExpression.insert(0, "-");
            }
            updateDisplay();
            displayPanel.clearError();
        });

        basicOperationsPanel.addAddListener(e -> appendOperator("+"));
        basicOperationsPanel.addSubtractListener(e -> appendOperator("-"));
        basicOperationsPanel.addMultiplyListener(e -> appendOperator("*"));
        basicOperationsPanel.addDivideListener(e -> appendOperator("/"));

        basicOperationsPanel.addClearListener(e -> {
            inputExpression.setLength(0);
            displayPanel.setDisplayText("");
            displayPanel.clearError();
        });

        basicOperationsPanel.addEqualsListener(e -> calculateResult());

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
        displayPanel.clearError();
    }

    private void applyScientificFunction(String func) {
        try {
            double currentValue = Double.parseDouble(displayPanel.getDisplayText());
            String expression = func + " " + currentValue;
            double result = calculatorEngine.calculate(expression);
            displayPanel.setDisplayText(String.valueOf(result));
            displayPanel.clearError();
            inputExpression.setLength(0);
            inputExpression.append(result);
        } catch (NumberFormatException nfe) {
            displayPanel.showError("Invalid number format");
        } catch (IllegalArgumentException iae) {
            displayPanel.showError(iae.getMessage());
        } catch (Exception ex) {
            displayPanel.showError("Error: " + ex.getMessage());
        }
    }

    private void calculateResult() {
        try {
            String expr = inputExpression.toString().trim();
            if (expr.isEmpty()) return;

            double result = calculatorEngine.calculate(expr);
            displayPanel.setDisplayText(String.valueOf(result));
            displayPanel.clearError();
            inputExpression.setLength(0);
            inputExpression.append(result);
        } catch (ArithmeticException ae) {
            displayPanel.showError("Division by zero");
        } catch (IllegalArgumentException iae) {
            displayPanel.showError(iae.getMessage());
        } catch (Exception ex) {
            displayPanel.showError("Error: " + ex.getMessage());
        }
    }

    private void updateDisplay() {
        displayPanel.setDisplayText(inputExpression.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
