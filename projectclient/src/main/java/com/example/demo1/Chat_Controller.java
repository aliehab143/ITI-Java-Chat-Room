package com.example.demo1;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demo1.Start_APP.port;

public class Chat_Controller implements Initializable {

    Socket server;
    DataInputStream dis;   // -->rec from sever
    PrintStream ps;  // ---> send to sever
    String msg_received;
    @FXML
    private TextArea Messages_Area;

    @FXML
    private TextField Message_TextField;

    @FXML
    private RadioButton Online_Button;

    private String status ="Available";

    @FXML
    private TextArea Userlist;

    @FXML
    private Text text;

    private Stage stage;


    public Chat_Controller()  {
        try{
            server = new Socket("192.168.1.19", port);
            dis=new DataInputStream(server.getInputStream());
            ps = new PrintStream(server.getOutputStream(), true);
            ps.println("status");
            ps.println(Login_Controller.Current_username);
            ps.println(status);
            Receive();
            ps.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }



    private void handleSceneClose() throws IOException {
        // Send the status as "Offline" to the server
        server = new Socket("192.168.1.19", port);
        ps = new PrintStream(server.getOutputStream(), true);
        ps.println("status");
        ps.println(Login_Controller.Current_username);
        ps.println("Offline");
        ps.close();
        dis.close();
        server.close();
        // Close the application
        System.exit(0);
    }


    private void initializeStageCloseHandler() {
        if (stage != null) {
            stage.setOnCloseRequest(event -> {
                try {
                    handleSceneClose();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }


    public void setStage(Stage stage) {
        this.stage = stage;
        initializeStageCloseHandler();
    }



    @FXML
    void Send_Message(ActionEvent event) throws IOException {
        server = new Socket("192.168.1.19", port);
        ps = new PrintStream(server.getOutputStream(), true);
        String msg = Message_TextField.getText();
        if (!msg.equals("")){
            ps.println("chat");
            ps.println(msg);
            ps.println(Login_Controller.Current_username);
            Message_TextField.clear();
            ps.close();}



    }


    @FXML
    void satuts_Clicked(ActionEvent event) throws IOException {
        working = 1;
        if (status.equals("Available")){
            status="Busy";
        }
        else {
            status="Available";
        }
        server = new Socket("192.168.1.19", port);
        ps = new PrintStream(server.getOutputStream(), true);
        ps.println("status");
        ps.println(Login_Controller.Current_username);
        ps.println(status);
        ps.close();
        server.close();
        check = 1;
        working = 0;
    }



    @FXML
    public void Save_Chat(ActionEvent event) throws IOException {
        String filePath = Login_Controller.Current_username + ".txt";
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(Messages_Area.getText());
        fileWriter.close();
    }

    public void load_Chat() throws IOException {
        String filePath = Login_Controller.Current_username + ".txt";
        File file = new File(filePath);
        if (file.exists()) {

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            StringBuilder loaded = new StringBuilder();
            while ((line = br.readLine()) != null) {
                loaded.append(line).append("\n");
                Messages_Area.appendText(line+"\n");

            }


        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            load_Chat();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    int working = 0;
    int check = 0;

    @FXML
    void Refresh_client_Userlist(ActionEvent event) throws IOException {
        working = 1;
        server = new Socket("192.168.1.19", port);
        dis=new DataInputStream(server.getInputStream());
        ps = new PrintStream(server.getOutputStream(), true);
        ps.println("get_online_list");

        String recivce  = dis.readLine();
        String[] a = recivce.split("##");
        StringBuilder list2 = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            list2.append(a[i]).append("\n");
        }
        Userlist.setText(String.valueOf(list2));

        ps.close();
        dis.close();
        server.close();
        check = 1;
        working = 0;

    }



    public void Receive() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    server = new Socket("192.168.1.19", port);
                    dis=new DataInputStream(server.getInputStream());
                    ps = new PrintStream(server.getOutputStream(), true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                while (server.isConnected()) {
                    if (working == 0) {
                        if (check == 1) {
                            try {
                                server = new Socket("192.168.1.19", port);
                                dis = new DataInputStream(server.getInputStream());
                                ps = new PrintStream(server.getOutputStream(), true);
                            } catch (Exception e) {
                                System.out.println("Error");
                            }
                            check = 0;
                        }
                        try {
                            msg_received = dis.readLine();
                            if (msg_received != null) {

                                Messages_Area.appendText(msg_received + "\n");
                            }
                        } catch (IOException e) {
                            System.out.print("");
                        }
                    }

                }
            }
        }).start();
    }




}
