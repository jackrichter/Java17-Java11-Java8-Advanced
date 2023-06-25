package org.advanced.java.security;

import org.advanced.java.jdbc.BankAccount;

import java.sql.*;

public class SQLInjectionExample {

    private static Connection con;
    private static SQLInjectionExample bankService = new SQLInjectionExample();

    public SQLInjectionExample() {
        try {
            con = createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Statements can be exposed to SQL injection attacks!
//        bankService.retrieveUsingSimpleStatement("123456");     // This one is ok. No dangerous code
//        bankService.retrieveUsingSimpleStatement("123456 OR BRANCH_CODE IS NOT NULL");      // Attack!

        // Using a PreparedStatement with bind variables will protect against such attacks!
        bankService.retrieveUsingPreparedStatement("123456");
        bankService.retrieveUsingPreparedStatement("123456 OR BRANCH_CODE IS NOT NULL");
    }

    private Connection createConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccode", "jackr", "mysql@123");
        return conn;
    }

    public void retrieveUsingSimpleStatement(String branchCode) {
        String selectSql = "select * from jdbccode.bank_table where branch_code = " + branchCode;
        BankAccount account = null;

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(selectSql);

            while (rs.next()) {
                account = new BankAccount(
                        rs.getString("BRANCH_CODE"),
                        rs.getString(2),
                        rs.getString("CUST_NAME"),
                        rs.getString("CUST_ADDRESS"),
                        rs.getDouble("BALANCE")
                );
                System.out.println(account);
            }

        } catch (SQLException e) {
            System.err.println("SQLException in retrieveUsingSimpleStatement()");
            e.printStackTrace();
        }
    }

    public void retrieveUsingPreparedStatement(String branchCode) {
        String selectSql = "select * from jdbccode.bank_table where branch_code = ?";

        BankAccount account = null;

        try (PreparedStatement ps = con.prepareStatement(selectSql)) {          // OBS. Resources are automatically closed

            ps.setString(1, branchCode);        // OBS. Columns start at 1 in JDBC

            ResultSet rs = ps.executeQuery();           // Implies SELECT

            if (rs.next()) {
                account = new BankAccount(
                        rs.getString("BRANCH_CODE"),
                        rs.getString(2),
                        rs.getString("CUST_NAME"),
                        rs.getString(4),
                        rs.getDouble("BALANCE")
                );
                System.out.println(account);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
