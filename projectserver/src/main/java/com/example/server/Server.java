package com.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class Server {
    private ServerSocket Connection_Listener;

    public Server(ServerSocket serverSocket){
        Connection_Listener=serverSocket;
    }
    public void Start_Server() {
        try {
            while (!Connection_Listener.isClosed()) {
                Socket socket = Connection_Listener.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
           e.printStackTrace();
        }

    }
    public void close_ServerSocket(){
        try{
            if (Connection_Listener!=null){
               Connection_Listener.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}

