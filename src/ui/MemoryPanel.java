package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel with memory function buttons: MS, MR, MC, M+, M-
 */
public class MemoryPanel extends JPanel {

    private JButton msButton, mrButton, mcButton, mPlusButton, mMinusButton;

    public MemoryPanel() {
        setLayout(new GridLayout(1, 5, 5, 5));
        setBackground(new Color(34, 34, 34));

        msButton = createButton("MS");
        mrButton = createButton("MR");
        mcButton = createButton("MC");
        mPlusButton = createButton("M+");
        mMinusButton = createButton("M-");

        add(msButton);
        add(mrButton);
        add(mcButton);
        add(mPlusButton);
        add(mMinusButton);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(60, 63, 65));
        btn.setForeground(Color.WHITE);
        return btn;
    }

    public void setButtonColors(Color bg, Color fg) {
        msButton.setBackground(bg); msButton.setForeground(fg);
        mrButton.setBackground(bg); mrButton.setForeground(fg);
        mcButton.setBackground(bg); mcButton.setForeground(fg);
        mPlusButton.setBackground(bg); mPlusButton.setForeground(fg);
        mMinusButton.setBackground(bg); mMinusButton.setForeground(fg);
    }

    // Listeners
    public void addMemoryStoreListener(ActionListener l) { msButton.addActionListener(l); }
    public void addMemoryRecallListener(ActionListener l) { mrButton.addActionListener(l); }
    public void addMemoryClearListener(ActionListener l) { mcButton.addActionListener(l); }
    public void addMemoryAddListener(ActionListener l) { mPlusButton.addActionListener(l); }
    public void addMemorySubtractListener(ActionListener l) { mMinusButton.addActionListener(l); }
}
