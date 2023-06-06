package org.advanced.java.jdbc;

import java.sql.*;

public class CallingStoreProcedures {

    private static Connection con = null;

    public static void main(String[] args) {
//        noParams();
//        inParam("Athlone");
//        outParam();
        inOutParam();
    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccode", "jackr", "mysql@123");
        } catch (SQLException e) {
            System.out.println("Failed to create a Connection.");
            e.printStackTrace();
        }
        return null;
    }

    private static void noParams() {
        String noParamsSql = "{call read_dublin_addresses()}";
        con = getConnection();

        if (con != null) {
            // try-with-resources will tidy up
            try (CallableStatement cs = con.prepareCall(noParamsSql)) {
                ResultSet rs = cs.executeQuery();

                while (rs.next()) {
                    System.out.println(rs.getString("cust_name") + ", " + rs.getString("cust_address"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void inParam(String val) {
        String inParamSql = "{call read_address(?)}";
        con = getConnection();

        if (con != null) {
            try (CallableStatement cs = con.prepareCall(inParamSql)) {
                cs.setString(1, val);

                ResultSet rs = cs.executeQuery();

                while (rs.next()) {
                    System.out.println(rs.getString("cust_name") + ", " + rs.getString("cust_address"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void outParam() {
        String outParamSql = "{call count_customers(?)}";
        con = getConnection();

        try (CallableStatement cs = con.prepareCall(outParamSql)) {
            cs.registerOutParameter(1, Types.INTEGER);          // Do this for every out or inout parameter
            cs.execute();                                                   // No ResulSet coming back
            System.out.println(cs.getInt("count"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void inOutParam() {
        String inOutParamSql = "{call square_number(?)}";
        con = getConnection();

        try (CallableStatement cs = con.prepareCall(inOutParamSql)) {

            cs.setInt(1, 5);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();

            int result = cs.getInt("theNumber");
            System.out.println(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
