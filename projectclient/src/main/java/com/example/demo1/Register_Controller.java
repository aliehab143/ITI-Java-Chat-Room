package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Objects;

import static com.example.demo1.Start_APP.port;

public class Register_Controller {
    Socket server;
    DataInputStream dis;
    PrintStream ps;
    @FXML
    private TextField Username_TextField;

    @FXML
    private TextField Password_TextField;

    @FXML
    private TextField Password_confirm_TextField;



    @FXML
    void Sign_in(ActionEvent event) {
        String username = Username_TextField.getText();
        String password = Password_TextField.getText();
        String confirm_password = Password_confirm_TextField.getText();
        if (!(Objects.equals(username, "null")) && !(Objects.equals(password, "null"))){

            if (!Objects.equals(password, "") && !Objects.equals(username, "") || !Objects.equals(username, "") && !Objects.equals(confirm_password, "")) { if (username.length() < 80 && password.length() < 45) {
                if (password.equals(confirm_password)) {
                    try {
                        server = new Socket("192.168.1.19", port);
                        ps = new PrintStream(server.getOutputStream(), true);
                        ps.println("register");
                        ps.println(username);
                        ps.println(password);
                        ///////////////////////////////////////
                        dis = new DataInputStream(server.getInputStream());
                        String t = dis.readLine();
                        ps.close();
                        dis.close();
                        server.close();


                        if (t.equals("true")) {
                            Alert("you Registered Successfully", "Success ");
                            change();
                        } else {

                            Alert("Username has already taken", "Error ");

                        }

                    } catch (IOException e) {
                        Alert("Try Again ", "Error ");
                    }

                } else {
                    Alert("The password and confirm password not the same  ", "Error ");
                }


            } else {
                Alert("The maximum username size is 80 you entered : " + username.length() + "\n" + "The maximum password size is 45 you entered :" + password.length(), "Error ");
            }
            }
            else {
                Alert("PLease Enter Values ", "Error");
            }
        }else {
            Alert("Username / Password Cannot be null", "Error");
        }

    }


    @FXML
    void Signup_Cancel(ActionEvent event) throws IOException {
                change();
    }

    public void change() throws IOException {
        Stage chat = (Stage) Password_confirm_TextField.getScene().getWindow();
        Parent login= FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(login);
        chat.setScene(scene);
        chat.show();
    }

    public void Alert(String tell , String title ) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(tell);
        alert.showAndWait();
    }


}
