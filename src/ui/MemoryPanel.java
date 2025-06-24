package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemoryPanel extends JPanel {

    private JButton msButton, mrButton, mcButton, mPlusButton, mMinusButton;

    public MemoryPanel() {
        setLayout(new GridLayout(1, 5, 5, 5));

        msButton = new JButton("MS");
        mrButton = new JButton("MR");
        mcButton = new JButton("MC");
        mPlusButton = new JButton("M+");
        mMinusButton = new JButton("M-");

        add(msButton);
        add(mrButton);
        add(mcButton);
        add(mPlusButton);
        add(mMinusButton);
    }

    public void addMemoryStoreListener(ActionListener listener) {
        msButton.addActionListener(listener);
    }

    public void addMemoryRecallListener(ActionListener listener) {
        mrButton.addActionListener(listener);
    }

    public void addMemoryClearListener(ActionListener listener) {
        mcButton.addActionListener(listener);
    }

    public void addMemoryAddListener(ActionListener listener) {
        mPlusButton.addActionListener(listener);
    }

    public void addMemorySubtractListener(ActionListener listener) {
        mMinusButton.addActionListener(listener);
    }
}
