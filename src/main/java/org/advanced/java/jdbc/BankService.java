package org.advanced.java.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankService {

    private static Connection con;
    private static BankService bankService = new BankService();

    public BankService() {
        try {
            con = createConnection();
            System.out.println("We've got a connection!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        bankService.retrieveOne();
//        bankService.retrieveAll();
//        bankService.add();
//        bankService.deleteOne();
//        bankService.deleteAll();
        bankService.update();
        // Last
        bankService.closeResources();
    }

    private Connection createConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccode", "jackr", "mysql@123");
        return conn;
    }

    private void closeResources() {
        try {
            con.close();
            System.out.println("Resources are now closed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void retrieveOne() {
        BankAccount account = getAccountDetails("123456", "12345678");
        System.out.println(account);
    }

    private void retrieveAll() {
        List<BankAccount> accounts = getAllAccounts();
        accounts.forEach(account -> System.out.println(account));
    }

    private void add() {
        BankAccount account = new BankAccount(
                "999999", "88888888", "SK", "Dublin", 100.0);

        int nRows = addBankAccount(account);

        if (nRows == 1) {
            System.out.println("Add OK: " + nRows);
        } else {
            System.out.println("Add error: " + nRows);
        }
    }

    private void deleteOne() {
        int nRows = deleteBankAccount("123456", "12345678");
        if (nRows == 1) {
            System.out.println("Delete OK: " + nRows);
        } else {
            System.out.println("Delete error: " + nRows);
        }
    }

    private void deleteAll() {
        bankService.deleteAllAccounts();
    }

    public void update() {
        BankAccount oldAccount = this.getAccountDetails("123456", "12345678");
        System.out.println("Before Update: " + oldAccount);

        BankAccount updatedAccount = new BankAccount("123456", "12345678", "J. Bloggs", "London", 300);

        int nRows = updateBankAccount(updatedAccount);
        if (nRows == 1) {
            System.out.println("After Update: " + getAccountDetails("123456", "12345678"));
        } else {
            System.out.println("Update error: "+ nRows);
        }
    }

    private BankAccount getAccountDetails(String branchCode, String accountNumber) {
//        String selectSql = "SELECT * FROM jdbccode.bank_table WHERE (BRANCH_CODE = ? AND ACCOUNT_NUMBER =?)";
//        String selectSql = "select * from jdbccode.bank_table where (BRANCH_CODE = ? AND ACCOUNT_NUMBER =?)";
        String selectSql = "select * from jdbccode.bank_table where (branch_code = ? AND account_number =?)";

        BankAccount account = null;

        try (PreparedStatement ps = con.prepareStatement(selectSql)) {          // OBS. Resources are automatically closed
            ps.setString(1, branchCode);        // OBS. Columns start at 1 in JDBC
            ps.setString(2, accountNumber);

            ResultSet rs = ps.executeQuery();           // Implies SELECT

            if (rs.next()) {
                account = new BankAccount(
                        rs.getString("BRANCH_CODE"),
                        rs.getString(2),
                        rs.getString("CUST_NAME"),
                        rs.getString(4),
                        rs.getDouble("BALANCE")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("SQLException in getAccountDetails()");
            e.printStackTrace();
        }

        return account;
    }

    private List<BankAccount> getAllAccounts() {
        List<BankAccount> accounts = new ArrayList<>();
        String selectQuery = "select * from jdbccode.bank_table";

        try (PreparedStatement ps = con.prepareStatement(selectQuery)) {         // OBS. Resources are automatically closed
            boolean isResultSet = ps.execute();

            if(isResultSet) {               // Should be True as it is a SELECT query
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    BankAccount bankAccount = new BankAccount(
                            rs.getString("branch_code"),
                            rs.getString("account_number"),
                            rs.getString("cust_name"),
                            rs.getString("cust_address"),
                            rs.getDouble("balance")
                    );
                    accounts.add(bankAccount);
                }
            } else {
                System.out.println("An UPDATE was performed!");
            }
        } catch (SQLException e) {
            System.err.println("SQLException in getAllAccounts()");
            e.printStackTrace();
        }

        return accounts;
    }

    private int addBankAccount(BankAccount account) {
        int nRows = -1;
        String insertQuery = "insert into jdbccode.bank_table"
                + "(branch_code, account_number, cust_name, cust_address, balance)"
                + "values (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setString(1, account.getBranchCode());               // Obs. Bind variables start at 1!
            ps.setString(2, account.getAccountNo());
            ps.setString(3, account.getCustName());
            ps.setString(4, account.getCustAddress());
            ps.setDouble(5, account.getBalance());
            nRows = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException in addBankAccount()");
            e.printStackTrace();
        }

        return nRows;
    }

    private int deleteBankAccount(String branchCode, String accountNumber) {
        int nRows = -1;
        String deleteQuery = "delete from jdbccode.bank_table where (branch_code = ? and account_number = ?)";

        try (PreparedStatement ps = con.prepareStatement(deleteQuery)) {
            ps.setString(1, branchCode);
            ps.setString(2, accountNumber);

            nRows = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("SQLException in deleteBankAccount()");
            e.printStackTrace();
        }

        return nRows;
    }

    private void deleteAllAccounts() {
        String deleteQuery = "delete from jdbccode.bank_table";

        try (PreparedStatement ps = con.prepareStatement(deleteQuery)) {
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("SQLException in deleteAllAccounts()");
            e.printStackTrace();
        }
    }

    private int updateBankAccount(BankAccount account) {
        int nRows = -1;
        String updateQuery = "update jdbccode.bank_table "
                + "set cust_name=?, cust_address=?, balance=? "
                + "where (branch_code = ? and account_number = ?)";

        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1, account.getCustName());
            ps.setString(2, account.getCustAddress());
            ps.setDouble(3, account.getBalance());
            ps.setString(4, account.getBranchCode());
            ps.setString(5, account.getAccountNo());

            nRows = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("SQLException in updateBankAccount()");
            e.printStackTrace();
            return nRows;
        }

        return nRows;
    }
}
