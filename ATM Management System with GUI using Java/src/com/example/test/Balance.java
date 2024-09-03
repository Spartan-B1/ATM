package com.example.test;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Balance {
    public Balance( User user, Database database){
        JFrame frame = new JFrame("ATM");
        frame.setLayout(new BorderLayout());

        JLabel content = GUIConstants.jLabel("Your current balance is:" + user.getBalance() +
                                            "$", 30);
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        frame.add(content, BorderLayout.CENTER);

        frame.setSize(600, 170);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(GUIConstants.backgroundColor);
        frame.setVisible(true);
    }
}
