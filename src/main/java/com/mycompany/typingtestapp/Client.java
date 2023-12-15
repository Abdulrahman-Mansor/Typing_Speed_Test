
package com.mycompany.typingtestapp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;



public class Client {

    public static void main(String[] args) throws Exception {
        String name="netBeans";
        Socket sock = new Socket("localhost", 1234);
        InputStream in = sock.getInputStream();
        OutputStream out = sock.getOutputStream();

        out.write(name.getBytes());
        byte oName[] = new byte[1024];
        in.read(oName);
        System.out.println(new String(oName));
        MyFrame frame = new MyFrame(name,new String(oName).trim(), sock);
        frame.setVisible(true);
    }

}
