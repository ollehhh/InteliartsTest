package connection;
import domain.Purchase;

import java.sql.*;
import java.util.ArrayList;


public class Connector {

    private static final String DB_URL = "jdbc:mysql://db4free.net:3306/ollehhh";
    private static final String USER = "ollehhh";
    private static final String PASS = "lhgfei111";
    Connection conn = null;
    Statement stmt = null;

    public void startConnection(){
        try {


            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
        }
        catch (SQLException se) {

            se.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException se2) {
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public ArrayList select(String select){
        ArrayList <Purchase> purchaseList = new ArrayList<Purchase>();
        Purchase purchase;
         ResultSet rs = null;
         try {
             rs = stmt.executeQuery(select);
             while (rs.next()) {
                purchase = new Purchase();
                purchase.setCurrency(rs.getString("Currency"));
                purchase.setItem(rs.getString("Item"));
                purchase.setLocalTime(rs.getDate("DTime").toLocalDate());
                purchase.setPrice(rs.getDouble("Price"));
                purchaseList.add(purchase);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }



        return purchaseList;
     }
    public String update(String sql){
        String message = null;
        try{
            stmt.executeUpdate(sql);
            message = "All goes ok!";

        } catch (SQLException e) {
          message = "Something was broken, pls try again!";
        }

         return message;

    }
}
