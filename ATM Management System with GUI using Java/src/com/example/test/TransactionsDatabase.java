package com.example.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionsDatabase {
    private static ArrayList<Transaction> getAllTransaction(Database database){
        ArrayList<Transaction> transaction = new ArrayList<>();
        ArrayList<Integer> usersIDs = new ArrayList<>();
        String select = "SELECT * FROM `transations` ;";
        try {
            ResultSet rs = database.getStatement().executeQuery(select);
            while (rs.next()){
                Transaction t = new Transaction();
                t.setID(rs.getInt("ID"));
                t.setAmount(rs.getDouble("Amount"));
                t.setDateTime(rs.getString("DateTime"));
                usersIDs.add(rs.getInt("UserID"));
                transaction.add(t);
            }

            for (int i = 0; i < transaction.size(); i++){
                User user = UserDatabase.getUserByID(usersIDs.get(i), database);
                transaction.get(i).setUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;

    }

    public static int getNextID(Database database){
        int ID = 0;
        ArrayList<Transaction> transactions = getAllTransaction(database);
        int size = transactions.size();
        if (size!=0){
            Transaction last = transactions.get(size-1);
            ID = last.getID()+1;
        }
        return ID;
    }

    public static void saveTransaction(Transaction t, Database database){
        String insert = "INSERT INTO `transations`(`ID`, `Amoun`, `DateTime`, `UserId`) VALUES ('"+t.getID()+
                "','"+t.getAmount()+"','"+t.getDateTime()+"','"
                +t.getUser().getID()+"');" ;
        try {
            database.getStatement().executeQuery(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String[][] getUserTransaction(Database database, int id){
        ArrayList<Transaction> transaction = new ArrayList<>();

        String select = "SELECT * FROM `transations` WHERE `UserId` = "+id+";";
        try {
            ResultSet rs = database.getStatement().executeQuery(select);
            while (rs.next()){
                Transaction t = new Transaction();
                t.setID(rs.getInt("ID"));
                t.setAmount(rs.getDouble("Amount"));
                t.setDateTime(rs.getString("DateTime"));
                transaction.add(t);
            }

            User user = UserDatabase.getUserByID(id, database);
            for (Transaction t : transaction){
                t.setUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[][] data = new String[transaction.size()][5];
        for (int j = 0; j<transaction.size(); j++){
            data[j][0] = String.valueOf(transaction.get(j).getID());
            if (transaction.get(j).getAmount()>0){
                data[j][1] = "Deposit";
            }else {
                data[j][1] = "Withdraw";
            }
            data[j][2] = String.valueOf(Math.abs(transaction.get(j).getAmount()));
            data[j][3] = transaction.get(j).getDate();
            data[j][4] = transaction.get(j).getTime();

        }
        return data;
    }

}
