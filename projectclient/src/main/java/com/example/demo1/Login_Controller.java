package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static com.example.demo1.Start_APP.port;

public class Login_Controller {


     Socket server;
    DataInputStream dis;
    PrintStream ps;
    String t ;
    @FXML
    private TextField Username_TextField;
    public static String Current_username ;

    @FXML
    private TextField password_text;






    @FXML
    void Choose_Login(ActionEvent event) throws IOException {// user and pass cannot be null
        String username=  Username_TextField.getText();
        String password = password_text.getText();

       server = new Socket("192.168.1.19", port);
        //sending from client to server
        ps = new PrintStream(server.getOutputStream(),true);
        ps.println("login");
        ps.println(username);
        ps.println(password);
      ///////////////////////////////////////
        dis=new DataInputStream(server.getInputStream());
        String t = dis.readLine();
        ps.close();
       dis.close();
       server.close();





       if (t.equals("true")){
           Current_username=Username_TextField.getText();
           Stage chat = (Stage) Username_TextField.getScene().getWindow();
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Chat.fxml"));
           Parent chat_view = loader.load();
           Scene scene = new Scene(chat_view);
           chat.setScene(scene);
           chat.show();
            // Pass the stage to the chat_controller
           Chat_Controller chatController = loader.getController();
           chatController.setStage(chat);


       }
       else {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Invalid Input");
           alert.setHeaderText("User not found error ");
           alert.setContentText("Wrong USERNAME /PASSWORD");
           alert.showAndWait();
       }

    }





    @FXML
    void Cancel_login(ActionEvent event) {
        System.exit(0);
    }



    @FXML
    void Choose_Register(ActionEvent event) throws IOException {
        Stage Register = (Stage) Username_TextField.getScene().getWindow();
        Parent Register_veiw= FXMLLoader.load(getClass().getResource("Signup2.fxml"));
        Scene scene = new Scene(Register_veiw);
        Register.setScene(scene);
        Register.show();

    }


}

