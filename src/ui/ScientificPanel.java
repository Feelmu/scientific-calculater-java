package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ScientificPanel extends JPanel {

    private JButton sinButton, cosButton, tanButton;
    private JButton asinButton, acosButton, atanButton;
    private JButton logButton, lnButton;
    private JButton expButton, tenPowerButton;
    private JButton sqrtButton, powerButton, factorialButton;

    public ScientificPanel() {
        setLayout(new GridLayout(4, 4, 5, 5));

        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        asinButton = new JButton("asin");
        acosButton = new JButton("acos");
        atanButton = new JButton("atan");
        logButton = new JButton("log");
        lnButton = new JButton("ln");
        expButton = new JButton("e^x");
        tenPowerButton = new JButton("10^x");
        sqrtButton = new JButton("√");
        powerButton = new JButton("^");
        factorialButton = new JButton("n!");

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
        add(powerButton);
        add(factorialButton);
    }

    public void addSinListener(ActionListener listener) {
        sinButton.addActionListener(listener);
    }

    public void addCosListener(ActionListener listener) {
        cosButton.addActionListener(listener);
    }

    public void addTanListener(ActionListener listener) {
        tanButton.addActionListener(listener);
    }

    public void addAsinListener(ActionListener listener) {
        asinButton.addActionListener(listener);
    }

    public void addAcosListener(ActionListener listener) {
        acosButton.addActionListener(listener);
    }

    public void addAtanListener(ActionListener listener) {
        atanButton.addActionListener(listener);
    }

    public void addLogListener(ActionListener listener) {
        logButton.addActionListener(listener);
    }

    public void addLnListener(ActionListener listener) {
        lnButton.addActionListener(listener);
    }

    public void addExpListener(ActionListener listener) {
        expButton.addActionListener(listener);
    }

    public void addTenPowerListener(ActionListener listener) {
        tenPowerButton.addActionListener(listener);
    }

    public void addSqrtListener(ActionListener listener) {
        sqrtButton.addActionListener(listener);
    }

    public void addPowerListener(ActionListener listener) {
        powerButton.addActionListener(listener);
    }

    public void addFactorialListener(ActionListener listener) {
        factorialButton.addActionListener(listener);
    }
}
