package org.advanced.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClosingResources {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbccode";
        String user = "jackr", pwd = "mysql@123";
        String sql = "SELECT * FROM jdbccode.BANK_TABLE";
        // These resources will be closed automatically in reverse order:
        //      ResultSet, PreparedStatement, Connection
        // This is the order that we want.
        try(Connection con       = DriverManager.getConnection(url, user, pwd);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs         = ps.executeQuery()){
                // process 'rs'
                while (rs.next()) {
                    // process the record
                    BankAccount bankAccount = new BankAccount(
                            rs.getString(1),    // "BRANCH_CODE"
                            rs.getString("ACCOUNT_NUMBER"),
                            rs.getString("CUST_NAME"),
                            rs.getString("CUST_ADDRESS"),
                            rs.getDouble("BALANCE"));

                    System.out.println(bankAccount);
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
}

