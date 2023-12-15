package com.mycompany.typingtestapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Thread.interrupted;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
/*Steps:
1-Append every new socket
2-

*/
 class DominantServer {

    
    ArrayList<Socket> sockets = new ArrayList(0);
    ArrayList<InputStream> ins = new ArrayList(0);
    ArrayList<OutputStream> outs = new ArrayList(0);
    ServerSocket ser = new ServerSocket(1234);
    String name = "DominantServer";
    
    DominantServer() throws IOException{
        while (true) {
            System.out.println("Waiting for client");
            Socket sock = ser.accept();
            InputStream in = sock.getInputStream();
            OutputStream out = sock.getOutputStream();
            out.write(name.getBytes());
            sockets.add(sock);
            ins.add(in);
            outs.add(out);
        }
    }

    
    
    class ClientReceiver extends Thread {

        public void run() {
            while(!interrupted());
        }
    }

}

public class toRun{
    public static void main(String[] args) {
        new DominantServer();
    }
}


