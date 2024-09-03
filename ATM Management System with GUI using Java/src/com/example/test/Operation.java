package com.example.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Operation {
    public Operation(String operation, User user, Database database){
        JFrame frame = new JFrame("ATM");
        frame.setLayout(new BorderLayout());

        JLabel title = GUIConstants.jLabel(operation,30);
        title.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

        panel.add(GUIConstants.jLabel("Amount:", 23));
        JTextField amount = GUIConstants.jTextField();
        panel.add(amount);

        JButton cancel = GUIConstants.jButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        panel.add(cancel);

        JButton confirm = GUIConstants.jButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amountIn = Double.parseDouble(amount.getText());
                if (operation.equals("Deposit")){
                    Transaction transaction = new Transaction(amountIn, user);
                    transaction.setID(TransactionsDatabase.getNextID(database));
                    user.setBalance(user.getBalance()+amountIn);
                    TransactionsDatabase.saveTransaction(transaction, database);
                    UserDatabase.updateUserBalance(user, database);
                    JOptionPane.showMessageDialog(null, "Operation done successfully");
                    frame.dispose();
                } else if (operation.equals("Withdraw")) {
                    Transaction transaction = new Transaction(amountIn*-1, user);
                    transaction.setID(TransactionsDatabase.getNextID(database));
                    user.setBalance(user.getBalance()-amountIn);
                    TransactionsDatabase.saveTransaction(transaction, database);
                    UserDatabase.updateUserBalance(user,database);
                    JOptionPane.showMessageDialog(null, "Operation done successfully");
                    frame.dispose();

                }
            }
        });
        panel.add(confirm);

        frame.add(panel, BorderLayout.CENTER);


        frame.setSize(600, 200);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(GUIConstants.backgroundColor);
        frame.setVisible(true);
    }
}
