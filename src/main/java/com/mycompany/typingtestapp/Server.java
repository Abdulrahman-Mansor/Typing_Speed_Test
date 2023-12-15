package com.mycompany.typingtestapp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) throws Exception {
        String name="Mansour";
        ServerSocket ser = new ServerSocket(1234);
        System.out.println(ser.getInetAddress());
       
            System.out.println("Waiting for client");
            Socket sock = ser.accept();
            InputStream in = sock.getInputStream();
            OutputStream out = sock.getOutputStream();

            byte oName[] = new byte[1024];
            in.read(oName);

            System.out.println(oName);
            MyFrame frame = new MyFrame(name,new String(oName).trim(), sock);
            out.write(name.getBytes());
            frame.setVisible(true);
        

    }

}
