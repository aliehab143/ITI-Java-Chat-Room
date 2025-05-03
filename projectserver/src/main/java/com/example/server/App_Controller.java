package com.example.server;

import java.sql.SQLException;
import java.util.ArrayList;

public class App_Controller {



    public static String login(String username , String password) throws SQLException {
        boolean x =  Database.user_login_check_info(Database.Connect_DB(),username,password);
        if (x)return "true";
        else return "false";
    }

    public static String Register(String username , String password) throws Exception {
        boolean x =Database.register(Database.Connect_DB(),username,password);
        if (x)return "true";
        else return "false";

    }

    public static void Status(String username,String status) throws SQLException {
        Database.status(Database.Connect_DB(),username,status);

    }

    public static boolean Update_user_password(String username,String password ) throws SQLException {
        return Database.update_password(Database.Connect_DB(),username,password);

    }
    public static String read(String username ) throws SQLException {
        return Database.get_data(Database.Connect_DB(),username);
    }

    public static boolean Delete_client(String username) throws SQLException {
        return Database.delete_client_database(Database.Connect_DB(),username);
    }

    public static String control (ArrayList<String>array) throws Exception {
        if (array.get(0).equals("login")){
           return  login(array.get(1), array.get(2));
        }
        else if (array.get(0).equals("chat")){
            ClientHandler.sendMessageToAll(array.get(2)+" : "+array.get(1));
        }
        else if (array.get(0).equals("register")) {
            return Register(array.get(1), array.get(2));
        }
        else if (array.get(0).equals("status"))
        {
            Status( array.get(1), array.get(2) );
        }
        else if (array.get(0).equals("get_online_list")){
            return Database.status_online_list(Database.Connect_DB());
        }
        else if (array.get(0).equals("get_available_list")){
            return Database.status_Available_list(Database.Connect_DB());
        }
        else if (array.get(0).equals("get_busy_list")){
            return Database.status_Busy_list(Database.Connect_DB());
        }
        else if (array.get(0).equals("get_offline_list")){
            return Database.status_offline_list(Database.Connect_DB());
        }
        return null;
    }
}
