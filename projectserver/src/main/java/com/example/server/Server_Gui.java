package com.example.server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server_Gui {
    @FXML
    private TextArea Busy_server_list;

    @FXML
    private TextArea avaliable_server_list;

    @FXML
    private TextArea Offline_server_list;




    public Server_Gui() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                Server server = null;
                try {
                    server = new Server(new ServerSocket(7500));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                server.Start_Server();
            }
        }).start();







        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String>Command= new ArrayList<String>();
                Command.add("get_available_list");
                while (true) {
                    try {
                        String list = App_Controller.control(Command);

                        if (list != null) {
                            String[] a = list.split("##");
                            StringBuilder list2 = new StringBuilder();
                            for (int i = 0; i < a.length; i++) {
                                list2.append(a[i]).append("\n");
                            }
                            avaliable_server_list.setText(String.valueOf(list2));
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String>Command= new ArrayList<String>();
                Command.add("get_busy_list");
                while (true) {
                    try {
                        String list = App_Controller.control(Command);

                        if (list != null) {
                            String[] a = list.split("##");
                            StringBuilder list2 = new StringBuilder();
                            for (int i = 0; i < a.length; i++) {
                                list2.append(a[i]).append("\n");
                            }
                            Busy_server_list.setText(String.valueOf(list2));
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String>Command= new ArrayList<String>();
                Command.add("get_offline_list");
                while (true) {
                    try {
                        String list = App_Controller.control(Command);
                        if (list != null) {
                            String[] a = list.split("##");
                            StringBuilder list2 = new StringBuilder();
                            for (int i = 0; i < a.length; i++) {
                                list2.append(a[i]).append("\n");
                            }
                            Offline_server_list.setText(String.valueOf(list2));
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }).start();


    }


    @FXML
    void Admin_Screen(ActionEvent event) throws IOException {
        Stage admin = (Stage) Offline_server_list.getScene().getWindow();
        Parent Admin_view= FXMLLoader.load(getClass().getResource("admin1.fxml"));
        Scene scene = new Scene(Admin_view);
        admin.setScene(scene);
        admin.show();
    }



}
