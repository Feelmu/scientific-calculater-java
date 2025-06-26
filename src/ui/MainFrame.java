package ui;

import memory.MemoryManager;
import logic.CalculatorEngine;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // UI components
    private DisplayPanel displayPanel;
    private MemoryPanel memoryPanel;
    private ScientificPanel scientificPanel;
    private NumericKeypadPanel numericKeypadPanel;
    private BasicOperationsPanel basicOperationsPanel;
    // Logic components
    private CalculatorEngine calculatorEngine;
    private MemoryManager memoryManager;
    private StringBuilder inputExpression;
    private boolean isDegreeMode = true;

    // Constructor - sets up the main window and components
    public MainFrame() {
        setTitle("Scientific Calculator");
        setSize(360, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize core logic components
        calculatorEngine = new CalculatorEngine(isDegreeMode);
        memoryManager = new MemoryManager();
        inputExpression = new StringBuilder();

        // Initialize UI panels
        displayPanel = new DisplayPanel();
        memoryPanel = new MemoryPanel();
        scientificPanel = new ScientificPanel();
        numericKeypadPanel = new NumericKeypadPanel();
        basicOperationsPanel = new BasicOperationsPanel();

        // Set layout and background color for main frame
        setLayout(new BorderLayout(5, 5));
        getContentPane().setBackground(new Color(34, 34, 34));

        // Build top panel with display and memory indicators
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBackground(new Color(34, 34, 34));
        topPanel.add(displayPanel, BorderLayout.NORTH);
        topPanel.add(memoryPanel, BorderLayout.SOUTH);

        // Build center panel with scientific buttons and numeric keypad
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBackground(new Color(34, 34, 34));
        centerPanel.add(scientificPanel, BorderLayout.NORTH);
        centerPanel.add(numericKeypadPanel, BorderLayout.CENTER);

        // Build right panel with basic operation buttons (add, subtract, etc.)
        JPanel rightPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        rightPanel.setBackground(new Color(34, 34, 34));
        rightPanel.add(basicOperationsPanel.getAddButton());
        rightPanel.add(basicOperationsPanel.getSubtractButton());
        rightPanel.add(basicOperationsPanel.getMultiplyButton());
        rightPanel.add(basicOperationsPanel.getDivideButton());
        rightPanel.add(basicOperationsPanel.getEqualsButton());
        rightPanel.add(basicOperationsPanel.getClearButton());

        // Add all major panels to the frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        applyDarkMode();
        attachListeners();
        setVisible(true);
    }

    // Applies consistent dark color theme to UI components
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

    // Attaches event listeners for all buttons and user input components
    private void attachListeners() {
        // Numeric keypad digits
        numericKeypadPanel.addDigitListener(e -> {
            JButton btn = (JButton) e.getSource();
            inputExpression.append(btn.getText());
            displayPanel.setNormalFontSize();
            updateDisplay();
        });

        // Decimal point input - only allow one decimal point
        numericKeypadPanel.addDecimalListener(e -> {
            String current = inputExpression.toString();
            if (!current.endsWith(".") && !current.contains(".")) {
                inputExpression.append(".");
                displayPanel.setNormalFontSize();
                updateDisplay();
            }
        });

        // Plus/minus toggle for negative numbers
        numericKeypadPanel.addPlusMinusListener(e -> {
            if (inputExpression.length() > 0 && inputExpression.charAt(0) == '-') {
                inputExpression.deleteCharAt(0);
            } else {
                inputExpression.insert(0, "-");
            }
            displayPanel.setNormalFontSize();
            updateDisplay();
        });

        // Basic operations listeners
        basicOperationsPanel.addAddListener(e -> appendOperator("+"));
        basicOperationsPanel.addSubtractListener(e -> appendOperator("-"));
        basicOperationsPanel.addMultiplyListener(e -> appendOperator("*"));
        basicOperationsPanel.addDivideListener(e -> appendOperator("/"));
        basicOperationsPanel.addClearListener(e -> {
            inputExpression.setLength(0);
            displayPanel.setNormalFontSize();
            displayPanel.setDisplayText("");
        });
        basicOperationsPanel.addEqualsListener(e -> calculateResult());

        // Scientific functions listeners
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

        // Memory functionality listeners with error handling and display updates
        memoryPanel.addMemoryStoreListener(e -> {
            try {
                double val = Double.parseDouble(displayPanel.getDisplayText());
                memoryManager.store(val);
                displayPanel.showMemoryIndicator(true);
                displayPanel.setNormalFontSize();
                displayPanel.setDisplayText("Stored");
            } catch (Exception ex) {
                displayPanel.setErrorFontSize();
                displayPanel.setDisplayText("Invalid Store");
            }
        });

        memoryPanel.addMemoryRecallListener(e -> {
            double val = memoryManager.recall();
            displayPanel.showMemoryIndicator(true);
            displayPanel.setNormalFontSize();
            displayPanel.setDisplayText(String.valueOf(val));
            inputExpression.setLength(0);
            inputExpression.append(val);
        });

        memoryPanel.addMemoryClearListener(e -> {
            memoryManager.clear();
            displayPanel.showMemoryIndicator(false);
            displayPanel.setNormalFontSize();
            displayPanel.setDisplayText("Memory Cleared");
        });

        memoryPanel.addMemoryAddListener(e -> {
            try {
                double val = Double.parseDouble(displayPanel.getDisplayText());
                memoryManager.add(val);
                displayPanel.showMemoryIndicator(true);
                displayPanel.setNormalFontSize();
                displayPanel.setDisplayText("Added to Memory");
            } catch (Exception ex) {
                displayPanel.setErrorFontSize();
                displayPanel.setDisplayText("Invalid Add");
            }
        });

        memoryPanel.addMemorySubtractListener(e -> {
            try {
                double val = Double.parseDouble(displayPanel.getDisplayText());
                memoryManager.subtract(val);
                displayPanel.showMemoryIndicator(true);
                displayPanel.setNormalFontSize();
                displayPanel.setDisplayText("Subtracted from Memory");
            } catch (Exception ex) {
                displayPanel.setErrorFontSize();
                displayPanel.setDisplayText("Invalid Subtract");
            }
        });
    }

    // Helper method to append an operator (+, -, *, /, ^) to the input expression
    private void appendOperator(String op) {
        if (inputExpression.length() == 0) return;
        char lastChar = inputExpression.charAt(inputExpression.length() - 1);
        if ("+-*/^".indexOf(lastChar) >= 0) {
            // Replace last operator if inputting consecutive operators
            inputExpression.setCharAt(inputExpression.length() - 1, op.charAt(0));
        } else {
            inputExpression.append(" ").append(op).append(" ");
        }
        displayPanel.setNormalFontSize();
        updateDisplay();
    }

    // Apply scientific functions (sin, cos, log, factorial, etc.) to current display value
    private void applyScientificFunction(String func) {
        try {
            double value = Double.parseDouble(displayPanel.getDisplayText());
            double result = calculatorEngine.calculate(func + " " + value);
            displayPanel.setNormalFontSize();
            displayPanel.setDisplayText(String.valueOf(result));
            inputExpression.setLength(0);
            inputExpression.append(result);
        } catch (Exception ex) {
            displayPanel.setErrorFontSize();
            displayPanel.setDisplayText("Error: " + ex.getMessage());
            inputExpression.setLength(0);
        }
    }

    // Calculate the result of the full expression entered by the user
    private void calculateResult() {
        try {
            String expr = inputExpression.toString().trim();
            if (expr.isEmpty()) return;
            double result = calculatorEngine.calculate(expr);
            displayPanel.setNormalFontSize();
            displayPanel.setDisplayText(String.valueOf(result));
            inputExpression.setLength(0);
            inputExpression.append(result);
        } catch (Exception ex) {
            displayPanel.setErrorFontSize();
            displayPanel.setDisplayText("Error: " + ex.getMessage());
            inputExpression.setLength(0);
        }
    }
    // Update the display panel text with current input expression
    private void updateDisplay() {
        displayPanel.setDisplayText(inputExpression.toString());
    }

    // Main method to launch the calculator UI on the Swing event thread
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
