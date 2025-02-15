package com.example.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    public Login(Database database){
        JFrame frame = new JFrame("ATM");
        frame.setLayout(new BorderLayout());

        JLabel title = GUIConstants.jLabel("Welcome to the ATM",30);
        title.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        frame.add(title,BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

        JLabel lb1 = GUIConstants.jLabel("ID:", 23);
        panel.add(lb1);
        JTextField id = GUIConstants.jTextField();
        panel.add(id);
        JLabel lb2 = GUIConstants.jLabel("PIN Code:", 23);
        panel.add(lb2);
        JPasswordField pincode = GUIConstants.jPasswordField();
        panel.add(pincode);

        JButton newAcc = GUIConstants.jButton("Create New Account");
        newAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            new UserData(true, database,UserDatabase.getNextID(database));
            frame.dispose();
            }
        });
        panel.add(newAcc);

        JButton login = GUIConstants.jButton("Continue");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int idIn = 0, pinIn = 0;
            try{
                idIn = Integer.parseInt(id.getText());
                pinIn = Integer.parseInt(pincode.getText());

            }catch (Exception e1){
                JOptionPane.showMessageDialog(frame, "ID & PIN must be int");
            }
            if(UserDatabase.login(idIn,pinIn,database)){
                User user = UserDatabase.getUser(idIn, pinIn, database);
                new List(database, user);
                frame.dispose();
            }else {
                JOptionPane.showMessageDialog(frame, "ID or PIN doesn't match");
            }


            }
        });
        panel.add(login);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(650, 350);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(GUIConstants.backgroundColor);
        frame.setVisible(true);

    }
}
