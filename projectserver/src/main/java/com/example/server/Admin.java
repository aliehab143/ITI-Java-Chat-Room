package com.example.server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Objects;

public class Admin {

    //create
    String t;

    @FXML
    private TextField username_delete;
    @FXML
    private TextArea read_veiw_area;
    @FXML
    private Button read;

    @FXML
    private TextField Read_username;


    @FXML
    private TextField Add_password;

    @FXML
    private TextField Add_confirm_password;

    @FXML
    private TextField Add_username;



    @FXML
    private TextField username_ipdate_password;

    @FXML
    private TextField new_pasword_update;


    @FXML
    void Read_User_Info(ActionEvent event) throws IOException { //Screen 1
        Stage admin = (Stage) read.getScene().getWindow();
        Parent Admin_view = FXMLLoader.load(getClass().getResource("admin_read.fxml"));
        Scene scene = new Scene(Admin_view);
        admin.setScene(scene);
        admin.show();
    }


    @FXML
    void Read_user_dataReadScreen(ActionEvent event) throws SQLException {
        String username = Read_username.getText();
        if (!username.equals("")) {
            read_veiw_area.setText(App_Controller.read(username));
        }
    }

    @FXML
    void Back_mainadminScreen(ActionEvent event) throws IOException {
        Stage admin = (Stage) Read_username.getScene().getWindow();
        Parent Admin_view = FXMLLoader.load(getClass().getResource("admin1.fxml"));
        Scene scene = new Scene(Admin_view);
        admin.setScene(scene);
        admin.show();
    }

    @FXML
    void Delete_User(ActionEvent event) throws IOException {
        Stage admin = (Stage) read.getScene().getWindow();
        Parent Admin_view = FXMLLoader.load(getClass().getResource("adminDelete.fxml"));
        Scene scene = new Scene(Admin_view);
        admin.setScene(scene);
        admin.show();
    }


    @FXML
    void delete_delete_Screen(ActionEvent event) throws SQLException, IOException {//delete screen
        String username = username_delete.getText();

        boolean x = App_Controller.Delete_client(username);
        if (x) {
            Alert("The Client has been deleted Successfully", "Success ");
            Stage admin = (Stage) username_delete.getScene().getWindow();
            Parent Admin_view = FXMLLoader.load(getClass().getResource("admin1.fxml"));
            Scene scene = new Scene(Admin_view);
            admin.setScene(scene);
            admin.show();
        } else Alert("Try Again Failed to Delete ", "Error ");
    }


    @FXML
    void create_user(ActionEvent event) throws IOException {
        Stage admin = (Stage) read.getScene().getWindow();
        Parent Admin_view = FXMLLoader.load(getClass().getResource("adminAdd.fxml"));
        Scene scene = new Scene(Admin_view);
        admin.setScene(scene);
        admin.show();
    }
    @FXML
    void Add_user(ActionEvent event) {
        String username = Add_username.getText();
        String password = Add_password.getText();
        String confirm_password = Add_confirm_password.getText();
        if (!(Objects.equals(username, "null")) && !(Objects.equals(password, "null"))) {

            if (!Objects.equals(password, "") && !Objects.equals(username, "") || !Objects.equals(username, "") && !Objects.equals(confirm_password, "")) {
                if (username.length() < 80 && password.length() < 45) {
                    if (password.equals(confirm_password)) {
                        try {
                            String t = App_Controller.Register(username, password);

                            if (t.equals("true")) {
                                Alert("you Registered a client  Successfully", "Success ");
                                Stage admin = (Stage) Add_username.getScene().getWindow();
                                Parent Admin_view = FXMLLoader.load(getClass().getResource("admin1.fxml"));
                                Scene scene = new Scene(Admin_view);
                                admin.setScene(scene);
                                admin.show();
                            } else {

                                Alert("Username has already taken", "Error ");

                            }

                        } catch (IOException e) {
                            Alert("Try Again ", "Error ");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    } else {
                        Alert("The password and confirm password not the same  ", "Error ");
                    }


                } else {
                    Alert("The maximum username size is 80 you entered : " + username.length() + "\n" + "The maximum password size is 45 you entered :" + password.length(), "Error ");
                }
            } else {
                Alert("PLease Enter Values ", "Error");
            }
        } else {
            Alert("Username / Password Cannot be null", "Error");
        }
    }



    @FXML
    void update_passowrd(ActionEvent event) throws SQLException, IOException {
        String username = username_ipdate_password.getText();
        String newPassword= new_pasword_update.getText();
        boolean x =   App_Controller.Update_user_password(username,newPassword);
        if (x) {

            Alert("Password has been changed Successfully", "Success ");
            Stage admin = (Stage) username_ipdate_password.getScene().getWindow();
            Parent Admin_view = FXMLLoader.load(getClass().getResource("admin1.fxml"));
            Scene scene = new Scene(Admin_view);
            admin.setScene(scene);
            admin.show();

        }
        else    Alert("Try Again Failed to Change Password ", "Error ");
    }

    @FXML
    void Update_user_info(ActionEvent event) throws IOException {
        Stage admin = (Stage) read.getScene().getWindow();
        Parent Admin_view = FXMLLoader.load(getClass().getResource("Admin_Update.fxml"));
        Scene scene = new Scene(Admin_view);
        admin.setScene(scene);
        admin.show();
    }


    public void Alert(String tell, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(tell);
        alert.showAndWait();
    }


}


