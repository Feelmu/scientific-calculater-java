package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ScientificPanel extends JPanel {

    private JButton sinBtn, cosBtn, tanBtn, asinBtn, acosBtn, atanBtn;
    private JButton logBtn, lnBtn, expBtn, tenPowBtn, sqrtBtn, powerBtn, factorialBtn;

    public ScientificPanel() {
        setLayout(new GridLayout(4, 4, 5, 5));
        setBackground(new Color(34, 34, 34));

        sinBtn = createButton("sin");
        cosBtn = createButton("cos");
        tanBtn = createButton("tan");
        asinBtn = createButton("asin");
        acosBtn = createButton("acos");

        atanBtn = createButton("atan");
        logBtn = createButton("log");
        lnBtn = createButton("ln");
        expBtn = createButton("e^x");
        tenPowBtn = createButton("10^x");

        sqrtBtn = createButton("√");
        powerBtn = createButton("x^y");
        factorialBtn = createButton("n!");

        add(sinBtn);
        add(cosBtn);
        add(tanBtn);
        add(asinBtn);
        add(acosBtn);

        add(atanBtn);
        add(logBtn);
        add(lnBtn);
        add(expBtn);
        add(tenPowBtn);

        add(sqrtBtn);
        add(powerBtn);
        add(factorialBtn);

        // Fill remaining grid cells with empty labels to keep layout consistent
        for (int i = 0; i < 2; i++) {
            add(new JLabel(""));
        }
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        return btn;
    }

    public void setButtonColors(Color bg, Color fg) {
        JButton[] buttons = {sinBtn, cosBtn, tanBtn, asinBtn, acosBtn,
                atanBtn, logBtn, lnBtn, expBtn, tenPowBtn,
                sqrtBtn, powerBtn, factorialBtn};
        for (JButton btn : buttons) {
            btn.setBackground(bg);
            btn.setForeground(fg);
        }
    }

    // Listeners
    public void addSinListener(ActionListener l) { sinBtn.addActionListener(l); }
    public void addCosListener(ActionListener l) { cosBtn.addActionListener(l); }
    public void addTanListener(ActionListener l) { tanBtn.addActionListener(l); }
    public void addAsinListener(ActionListener l) { asinBtn.addActionListener(l); }
    public void addAcosListener(ActionListener l) { acosBtn.addActionListener(l); }
    public void addAtanListener(ActionListener l) { atanBtn.addActionListener(l); }
    public void addLogListener(ActionListener l) { logBtn.addActionListener(l); }
    public void addLnListener(ActionListener l) { lnBtn.addActionListener(l); }
    public void addExpListener(ActionListener l) { expBtn.addActionListener(l); }
    public void addTenPowerListener(ActionListener l) { tenPowBtn.addActionListener(l); }
    public void addSqrtListener(ActionListener l) { sqrtBtn.addActionListener(l); }
    public void addPowerListener(ActionListener l) { powerBtn.addActionListener(l); }
    public void addFactorialListener(ActionListener l) { factorialBtn.addActionListener(l); }
}
