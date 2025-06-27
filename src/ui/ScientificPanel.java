package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ScientificPanel extends JPanel {
    private JButton sinButton, cosButton, tanButton;
    private JButton asinButton, acosButton, atanButton;
    private JButton logButton, lnButton, expButton;
    private JButton tenPowerButton, sqrtButton, factorialButton;
    private JButton leftParenButton, rightParenButton, percentButton;

    public ScientificPanel() {
        // Set 4x4 grid layout with 5px gaps and dark background
        setLayout(new GridLayout(4, 4, 5, 5));
        setBackground(new Color(34, 34, 34));

        // Initialize all scientific function buttons
        sinButton = createButton("sin");
        cosButton = createButton("cos");
        tanButton = createButton("tan");
        asinButton = createButton("asin");
        acosButton = createButton("acos");
        atanButton = createButton("atan");
        logButton = createButton("log");
        lnButton = createButton("ln");
        expButton = createButton("exp");
        tenPowerButton = createButton("10^x");
        sqrtButton = createButton("√");
        factorialButton = createButton("n!");

        // Parentheses and percent buttons
        leftParenButton = createButton("(");
        rightParenButton = createButton(")");
        percentButton = createButton("%");

        // Add buttons in a logical order to fill the grid
        add(sinButton);
        add(cosButton);
        add(tanButton);
        add(asinButton);

        add(acosButton);
        add(atanButton);
        add(logButton);
        add(lnButton);

        add(expButton);
        add(tenPowerButton);
        add(sqrtButton);
        add(factorialButton);

        add(leftParenButton);
        add(rightParenButton);
        add(percentButton);
        add(new JLabel(""));  // empty cell to keep grid balanced
    }

    // Helper method to create styled buttons
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(60, 63, 65));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    // Update button and label colors for dark mode or other themes
    public void setButtonColors(Color bg, Color fg) {
        for (Component comp : getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                btn.setBackground(bg);
                btn.setForeground(fg);
            }
            if (comp instanceof JLabel) {
                JLabel lbl = (JLabel) comp;
                lbl.setForeground(fg);
            }
        }
    }

    // Listener setters for each button
    public void addSinListener(ActionListener l) { sinButton.addActionListener(l); }
    public void addCosListener(ActionListener l) { cosButton.addActionListener(l); }
    public void addTanListener(ActionListener l) { tanButton.addActionListener(l); }
    public void addAsinListener(ActionListener l) { asinButton.addActionListener(l); }
    public void addAcosListener(ActionListener l) { acosButton.addActionListener(l); }
    public void addAtanListener(ActionListener l) { atanButton.addActionListener(l); }
    public void addLogListener(ActionListener l) { logButton.addActionListener(l); }
    public void addLnListener(ActionListener l) { lnButton.addActionListener(l); }
    public void addExpListener(ActionListener l) { expButton.addActionListener(l); }
    public void addTenPowerListener(ActionListener l) { tenPowerButton.addActionListener(l); }
    public void addSqrtListener(ActionListener l) { sqrtButton.addActionListener(l); }
    public void addFactorialListener(ActionListener l) { factorialButton.addActionListener(l); }
    public void addLeftParenListener(ActionListener l) { leftParenButton.addActionListener(l); }
    public void addRightParenListener(ActionListener l) { rightParenButton.addActionListener(l); }
    public void addPercentListener(ActionListener l) { percentButton.addActionListener(l); }
}
