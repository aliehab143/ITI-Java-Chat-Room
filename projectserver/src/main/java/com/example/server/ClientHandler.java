package com.example.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class ClientHandler implements Runnable {
    private DataInputStream in;
    private PrintStream out;
    private String client_username;
    private Socket socket;
    static Vector<ClientHandler> clients = new Vector<ClientHandler>();

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new PrintStream(socket.getOutputStream(),true);
           ClientHandler. clients.add(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void run() {
        ArrayList<String>array = new ArrayList<String>();
        String msg;

        try{
            msg=in.readLine();
            if (msg!=null){
                array.add(msg);
                if (!msg.equals("get_online_list")) {

                    for (int i = 0; i < 2; i++) {
                        msg = in.readLine();
                        array.add(msg);
                    }
                }


                String output =  App_Controller.control(array);
                if (output != null){
                    out.print(output);
                }
                out.close();

            }}catch (IOException e){
            System.out.println("Connection Closed");
        }
        catch (Exception e){
            System.out.println("Error");
        }


    }






    public static  void  sendMessageToAll(String msg) {
        try{
            for (ClientHandler clientHandler : clients) {
                clientHandler.out.println(msg);
            }

        }catch (Exception e){
            System.out.println("error");
        }
    }
}