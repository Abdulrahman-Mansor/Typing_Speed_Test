
package com.mycompany.typingtestapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public class test {
    public static void main(String[] args) {
        for(int i =0;i<3;i++){
            try {
                Th th = new Th();
                th.start();
                Thread.sleep(1009);
            } catch (InterruptedException ex) {
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }
}
class Th extends Thread {

   
    @Override
    public void run() {
       for(int i=0;i<11;i++){
           System.out.println(i);
           try {
               Thread.sleep(1000);
           } catch (InterruptedException ex) {
               
           }
       }
    }
}
