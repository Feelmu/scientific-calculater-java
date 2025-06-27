package ui;

import memory.MemoryManager;
import logic.CalculatorEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private DisplayPanel displayPanel;
    private MemoryPanel memoryPanel;
    private ScientificPanel scientificPanel;
    private NumericKeypadPanel numericKeypadPanel;
    private BasicOperationsPanel basicOperationsPanel;
    private JTextArea historyArea;

    private CalculatorEngine calculatorEngine;
    private MemoryManager memoryManager;
    private StringBuilder inputExpression;
    private boolean isDegreeMode = true;

    private double lastResult = Double.NaN;
    private boolean lastInputWasResult = false;

    private JButton toggleDegreeRadianButton;

    public MainFrame() {
        setTitle("Scientific Calculator");
        setSize(360, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize calculator engine and memory manager
        calculatorEngine = new CalculatorEngine(isDegreeMode);
        memoryManager = new MemoryManager();
        inputExpression = new StringBuilder();

        // Initialize UI panels
        displayPanel = new DisplayPanel();
        memoryPanel = new MemoryPanel();
        scientificPanel = new ScientificPanel();
        numericKeypadPanel = new NumericKeypadPanel();
        basicOperationsPanel = new BasicOperationsPanel();

        // Remove parentheses and percent buttons from NumericKeypadPanel (only used in ScientificPanel)
        numericKeypadPanel.removeParenthesesAndPercentButtons();

        // Initialize degree/radian toggle button
        toggleDegreeRadianButton = new JButton("Deg");
        toggleDegreeRadianButton.setFont(new Font("Arial", Font.BOLD, 14));
        toggleDegreeRadianButton.setBackground(new Color(60, 63, 65));
        toggleDegreeRadianButton.setForeground(Color.WHITE);
        toggleDegreeRadianButton.setFocusPainted(false);

        setLayout(new BorderLayout(5, 5));
        getContentPane().setBackground(new Color(34, 34, 34));

        // History display area setup
        historyArea = new JTextArea(5, 30);
        historyArea.setEditable(false);
        historyArea.setBackground(new Color(50, 50, 50));
        historyArea.setForeground(Color.LIGHT_GRAY);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setPreferredSize(new Dimension(350, 100));

        // Panel containing display and history area
        JPanel displayAndHistory = new JPanel(new BorderLayout(5, 5));
        displayAndHistory.setBackground(new Color(34, 34, 34));
        displayAndHistory.add(displayPanel, BorderLayout.NORTH);
        displayAndHistory.add(scrollPane, BorderLayout.CENTER);

        // Top panel with display and memory panel
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBackground(new Color(34, 34, 34));
        topPanel.add(displayAndHistory, BorderLayout.NORTH);
        topPanel.add(memoryPanel, BorderLayout.SOUTH);

        // Center panel with scientific and numeric keypad panels
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBackground(new Color(34, 34, 34));
        centerPanel.add(scientificPanel, BorderLayout.NORTH);
        centerPanel.add(numericKeypadPanel, BorderLayout.CENTER);

        // Right panel with basic operation buttons and toggle button
        JPanel rightPanel = new JPanel(new GridLayout(7, 1, 5, 5)); // 7 rows to fit toggle button
        rightPanel.setBackground(new Color(34, 34, 34));
        rightPanel.add(basicOperationsPanel.getAddButton());
        rightPanel.add(basicOperationsPanel.getSubtractButton());
        rightPanel.add(basicOperationsPanel.getMultiplyButton());
        rightPanel.add(basicOperationsPanel.getDivideButton());
        rightPanel.add(basicOperationsPanel.getEqualsButton());
        rightPanel.add(basicOperationsPanel.getClearButton());
        rightPanel.add(toggleDegreeRadianButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        applyDarkMode();
        attachListeners();
        setupKeyBindings();

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

        toggleDegreeRadianButton.setBackground(btnBg);
        toggleDegreeRadianButton.setForeground(btnFg);

        historyArea.setBackground(new Color(50, 50, 50));
        historyArea.setForeground(Color.LIGHT_GRAY);
    }

    private void attachListeners() {
        // Digit buttons on numeric keypad
        numericKeypadPanel.addDigitListener(e -> appendInput(((JButton) e.getSource()).getText()));

        // Decimal point with validation to avoid multiple decimals
        numericKeypadPanel.addDecimalListener(e -> {
            if (lastInputWasResult) {
                inputExpression.setLength(0);
                lastInputWasResult = false;
            }
            if (!inputExpression.toString().endsWith(".") && !inputExpression.toString().contains(".")) {
                inputExpression.append(".");
                updateDisplay();
            }
        });

        // Plus-minus toggle for negating current input
        numericKeypadPanel.addPlusMinusListener(e -> {
            if (lastInputWasResult) {
                inputExpression.setLength(0);
                lastInputWasResult = false;
            }
            if (inputExpression.length() > 0 && inputExpression.charAt(0) == '-') {
                inputExpression.deleteCharAt(0);
            } else {
                inputExpression.insert(0, "-");
            }
            updateDisplay();
        });

        // Basic arithmetic operation listeners
        basicOperationsPanel.addAddListener(e -> appendOperator("+"));
        basicOperationsPanel.addSubtractListener(e -> appendOperator("-"));
        basicOperationsPanel.addMultiplyListener(e -> appendOperator("*"));
        basicOperationsPanel.addDivideListener(e -> appendOperator("/"));

        // Clear button clears input and display
        basicOperationsPanel.addClearListener(e -> {
            inputExpression.setLength(0);
            displayPanel.setDisplayText("");
            lastInputWasResult = false;
        });

        // Equals button calculates the result
        basicOperationsPanel.addEqualsListener(e -> calculateResult());

        // Scientific function buttons append function calls with open parenthesis
        scientificPanel.addSinListener(e -> appendFunction("sin("));
        scientificPanel.addCosListener(e -> appendFunction("cos("));
        scientificPanel.addTanListener(e -> appendFunction("tan("));
        scientificPanel.addAsinListener(e -> appendFunction("asin("));
        scientificPanel.addAcosListener(e -> appendFunction("acos("));
        scientificPanel.addAtanListener(e -> appendFunction("atan("));
        scientificPanel.addLogListener(e -> appendFunction("log("));
        scientificPanel.addLnListener(e -> appendFunction("ln("));
        scientificPanel.addExpListener(e -> appendFunction("exp("));
        scientificPanel.addTenPowerListener(e -> appendFunction("10^"));
        scientificPanel.addSqrtListener(e -> appendFunction("sqrt("));
        scientificPanel.addFactorialListener(e -> appendFunction("fact("));
        scientificPanel.addLeftParenListener(e -> appendInput("("));
        scientificPanel.addRightParenListener(e -> appendInput(")"));
        scientificPanel.addPercentListener(e -> appendOperator("%"));

        // Memory panel button listeners with try-catch for input parsing
        memoryPanel.addMemoryStoreListener(e -> {
            try {
                double val = Double.parseDouble(displayPanel.getDisplayText());
                memoryManager.store(val);
                displayPanel.showMemoryIndicator(true);
                displayPanel.setDisplayText("Stored");
            } catch (Exception ex) {
                displayPanel.setDisplayText("Invalid Store");
            }
        });

        memoryPanel.addMemoryRecallListener(e -> {
            double val = memoryManager.recall();
            displayPanel.showMemoryIndicator(true);
            displayPanel.setDisplayText(String.valueOf(val));
            inputExpression.setLength(0);
            inputExpression.append(val);
            lastInputWasResult = false;
        });

        memoryPanel.addMemoryClearListener(e -> {
            memoryManager.clear();
            displayPanel.showMemoryIndicator(false);
            displayPanel.setDisplayText("Memory Cleared");
            historyArea.setText("");
        });

        memoryPanel.addMemoryAddListener(e -> {
            try {
                double val = Double.parseDouble(displayPanel.getDisplayText());
                memoryManager.add(val);
                displayPanel.setDisplayText("Added to Memory");
            } catch (Exception ex) {
                displayPanel.setDisplayText("Invalid Add");
            }
        });

        memoryPanel.addMemorySubtractListener(e -> {
            try {
                double val = Double.parseDouble(displayPanel.getDisplayText());
                memoryManager.subtract(val);
                displayPanel.setDisplayText("Subtracted from Memory");
            } catch (Exception ex) {
                displayPanel.setDisplayText("Invalid Subtract");
            }
        });

        // Toggle Degree/Radian mode, update button label, engine, and display indicator
        toggleDegreeRadianButton.addActionListener(e -> {
            isDegreeMode = !isDegreeMode;
            toggleDegreeRadianButton.setText(isDegreeMode ? "Deg" : "Rad");
            calculatorEngine = new CalculatorEngine(isDegreeMode);

            // Update the small DEG/RAD sign on the display panel
            displayPanel.setDegreeModeIndicator(isDegreeMode);
        });
    }

    private void setupKeyBindings() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        String digits = "0123456789";
        for (char d : digits.toCharArray()) {
            inputMap.put(KeyStroke.getKeyStroke(d), "digit" + d);
            actionMap.put("digit" + d, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    appendInput(String.valueOf(d));
                }
            });
        }

        String ops = "+-*/^%";
        for (char op : ops.toCharArray()) {
            inputMap.put(KeyStroke.getKeyStroke(op), "op" + op);
            actionMap.put("op" + op, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    appendOperator(String.valueOf(op));
                }
            });
        }

        inputMap.put(KeyStroke.getKeyStroke('.'), "decimal");
        actionMap.put("decimal", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendInput(".");
            }
        });

        inputMap.put(KeyStroke.getKeyStroke('('), "openParen");
        actionMap.put("openParen", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendInput("(");
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(')'), "closeParen");
        actionMap.put("closeParen", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendInput(")");
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "equals");
        actionMap.put("equals", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "backspace");
        actionMap.put("backspace", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputExpression.length() > 0) {
                    inputExpression.deleteCharAt(inputExpression.length() - 1);
                    updateDisplay();
                }
            }
        });
    }

    private void appendInput(String input) {
        if (lastInputWasResult) {
            inputExpression.setLength(0);
            lastInputWasResult = false;
        }
        inputExpression.append(input);
        updateDisplay();
    }

    private void appendOperator(String op) {
        if (inputExpression.length() == 0) {
            if (op.equals("-")) { // allow negative start
                inputExpression.append(op);
                updateDisplay();
            }
            return;
        }
        char lastChar = inputExpression.charAt(inputExpression.length() - 1);
        if ("+-*/^%".indexOf(lastChar) >= 0) {
            // Replace last operator
            inputExpression.setCharAt(inputExpression.length() - 1, op.charAt(0));
        } else {
            inputExpression.append(op);
        }
        updateDisplay();
    }

    private void appendFunction(String func) {
        if (lastInputWasResult) {
            inputExpression.setLength(0);
            lastInputWasResult = false;
        }
        inputExpression.append(func);
        updateDisplay();
    }

    private void calculateResult() {
        try {
            String expr = inputExpression.toString().trim();

            // Auto close open parentheses if unbalanced
            int openParens = countChar(expr, '(');
            int closeParens = countChar(expr, ')');
            for (int i = 0; i < openParens - closeParens; i++) expr += ")";

            if (expr.isEmpty()) return;

            expr = wrapFunctionArguments(expr);
            expr = insertImplicitMultiplication(expr);
            expr = replacePowerOperator(expr);
            expr = replacePercentOperator(expr);

            double result = calculatorEngine.calculate(expr);
            displayPanel.setDisplayText(String.valueOf(result));

            historyArea.append(expr + " = " + result + "\n");
            historyArea.setCaretPosition(historyArea.getDocument().getLength());

            inputExpression.setLength(0);
            inputExpression.append(result);
            lastResult = result;
            lastInputWasResult = true;

        } catch (Exception ex) {
            displayPanel.setDisplayText("Error");
            inputExpression.setLength(0);
            lastInputWasResult = false;
        }
    }

    private String wrapFunctionArguments(String expr) {
        String[] functions = {"sin", "cos", "tan", "asin", "acos", "atan", "log", "ln", "exp", "sqrt", "fact"};
        for (String func : functions) {
            expr = expr.replaceAll(func + "(\\d+(\\.\\d+)?)", func + "($1)");
        }
        return expr;
    }

    private String insertImplicitMultiplication(String expr) {
        StringBuilder fixed = new StringBuilder();
        for (int i = 0; i < expr.length() - 1; i++) {
            char current = expr.charAt(i);
            char next = expr.charAt(i + 1);
            fixed.append(current);
            boolean insertMul = (Character.isDigit(current) || current == ')' || current == '.') &&
                    (Character.isLetter(next) || next == '(');
            if (insertMul) fixed.append('*');
        }
        fixed.append(expr.charAt(expr.length() - 1));
        return fixed.toString();
    }

    private String replacePowerOperator(String expr) {
        String regex = "(\\([^\\)]+\\)|\\d+(\\.\\d+)?|Ans)\\s*\\^\\s*(\\([^\\)]+\\)|\\d+(\\.\\d+)?|Ans)";
        while (expr.contains("^")) {
            expr = expr.replaceAll(regex, "pow($1,$3)");
        }
        return expr;
    }

    private String replacePercentOperator(String expr) {
        // Convert "number%" to (number*0.01)
        expr = expr.replaceAll("(\\d+(\\.\\d+)?)%", "($1*0.01)");
        return expr;
    }

    private int countChar(String str, char ch) {
        int count = 0;
        for (char c : str.toCharArray()) if (c == ch) count++;
        return count;
    }

    private void updateDisplay() {
        displayPanel.setDisplayText(inputExpression.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
