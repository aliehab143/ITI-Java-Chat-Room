package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Start_APP extends Application {

    public  static int port =7500;

    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(Start_APP.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 480);
        stage.setTitle("Chat_APP");
        stage.setOnCloseRequest(e -> {
            closeProgram(stage);}
        );
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void closeProgram(Stage stage){
        System.exit(0);

    }

    public static void main(String[] args) {
        launch();
    }
}