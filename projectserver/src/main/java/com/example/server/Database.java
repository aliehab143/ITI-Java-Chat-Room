package com.example.server;
import java.sql.*;



public class Database {
    /// we must make method throw ex
    public static Connection Connect_DB() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "root");
            return connection;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean user_login_check_info(Connection con, String username, String password) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM chat WHERE Username = ? AND password = ?");
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            return true;
        } else {
            return false;

        }
    }


    public static boolean register(Connection con, String username, String password) throws Exception {

        PreparedStatement ps = con.prepareStatement("SELECT * FROM chat WHERE Username = ? ");
        ps.setString(1, username);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.isBeforeFirst()) {
            return false;
        } else {

            ps = con.prepareStatement("INSERT INTO chat (Username, password,Status) VALUES (?, ?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3,"Offline");
            ps.executeUpdate();
            return true;

        }
    }

    public static void status(Connection con, String username, String status) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE chat SET Status = ? WHERE Username = ?");

        ps.setString(1, status);
        ps.setString(2, username);
        ps.executeUpdate();

    }


    public static String status_online_list(Connection con) throws SQLException { // for clients
        PreparedStatement ps = con.prepareStatement( "SELECT Username, Status FROM chat WHERE Status = ? OR Status = ?");
        ps.setString(1,"Available");
        ps.setString(2,"Busy");
        ResultSet resultSet = ps.executeQuery();

        StringBuilder resultBuilder = new StringBuilder();
        while (resultSet.next()) {
            String username = resultSet.getString("Username");
            String status = resultSet.getString("Status");

            // Concatenate the values
            resultBuilder.append(username).append( ": " ).append(status).append("##");
        }

        // Get the final result string
        return resultBuilder.toString();
    }






public static boolean update_password(Connection con,String username , String new_password)  {//for admin
        try {


            PreparedStatement ps = con.prepareStatement("UPDATE chat SET password = ? WHERE Username = ?");
            ps.setString(1, new_password);
            ps.setString(2, username);
            ps.executeUpdate();
            return true ;
        }catch (SQLException E ){
            return false;
        }
}

public static boolean delete_client_database (Connection con, String username)  {//for admin
       try{
    PreparedStatement ps = con.prepareStatement("DELETE FROM chat WHERE Username = ?");
    ps.setString(1, username);
    ps.executeUpdate();
    return true;
        }catch (SQLException e){
           return false;
       }
}
    public static String get_data(Connection con,String username) throws SQLException {
        PreparedStatement ps = con.prepareStatement( "SELECT Username, password,Status FROM chat WHERE Username = ? ");
        ps.setString(1,username);
        ResultSet resultSet = ps.executeQuery();
        StringBuilder resultBuilder = new StringBuilder();
        while (resultSet.next()) {
            String user_name = resultSet.getString("Username");
            String status = resultSet.getString("Status");
           String pass = resultSet.getString("password");
            // Concatenate the values
            resultBuilder.append("Username : ").append(user_name).append("\n").append("Password : ").append(pass).append("\n").append("Status : ").append(status);
        }

        // Get the final result string
        return resultBuilder.toString();
    }





    //queries for server gui

    public static String status_Available_list(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement( "SELECT Username, Status FROM chat WHERE Status = ? ");
        ps.setString(1,"Available");
        ResultSet resultSet = ps.executeQuery();
        StringBuilder resultBuilder = new StringBuilder();
        while (resultSet.next()) {
            String username = resultSet.getString("Username");
            String status = resultSet.getString("Status");
            // Concatenate the values
            resultBuilder.append(username).append("##");
        }

        // Get the final result string
        return resultBuilder.toString();
    }
    public static String status_Busy_list(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement( "SELECT Username, Status FROM chat WHERE Status = ? ");
        ps.setString(1,"Busy");
        ResultSet resultSet = ps.executeQuery();
        StringBuilder resultBuilder = new StringBuilder();
        while (resultSet.next()) {
            String username = resultSet.getString("Username");
            String status = resultSet.getString("Status");

            // Concatenate the values
            resultBuilder.append(username).append("##");
        }

        // Get the final result string
        return resultBuilder.toString();
    }

    public static String status_offline_list(Connection con) throws SQLException {
            PreparedStatement ps = con.prepareStatement( "SELECT Username, Status FROM chat WHERE Status = ? ");
            ps.setString(1,"Offline");
            ResultSet resultSet = ps.executeQuery();
            StringBuilder resultBuilder = new StringBuilder();
            while (resultSet.next()) {
            String username = resultSet.getString("Username");
            String status = resultSet.getString("Status");

            // Concatenate the values
            resultBuilder.append(username).append("##");
        }

        // Get the final result string
        return resultBuilder.toString();
    }


}