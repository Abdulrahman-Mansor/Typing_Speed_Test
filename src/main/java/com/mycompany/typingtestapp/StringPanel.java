package com.mycompany.typingtestapp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StringPanel extends javax.swing.JPanel implements ActionListener {

  

    @Override
    public void actionPerformed(ActionEvent e) {
    
        if(e.getSource()==timer){
            Letter letter = charList.get(4);
            letter.size+=v;
            if(!(letter.size>25 && letter.size<30))v=v*-1;
            
           
           counter++;
           if(counter==10)
            timer.stop();
           repaint();
 
        }
    }
    
    Vector<Letter> charList;
    String paragraph= "Mansour is the best of the best";
    Timer timer ;
    Graphics2D gg;
    int v=1;
    int counter =0;
    int pointer = 0;
    char currentChar;
    
   
    public StringPanel() {
        initComponents();
        start();
    }

  public void start(){
      charList = new Vector<>(0);
//      Scanner sc = new Scanner(System.in);
//      paragraph=sc.nextLine();
      for(int i = 0; i<paragraph.length();i++){
          char c = paragraph.charAt(i);
         
          charList.add(new Letter(c, 10 + 25/2 * i, 20,25,new JPanel()));
      }
      timer = new Timer(50,this);
      timer.start();
    
     currentChar=paragraph.charAt(pointer);
  }
    @Override
  public void paint(Graphics g){
      super.paint(g);
       gg = (Graphics2D)g;
      for(Letter l : charList){
          l.drawLetter(gg,l.size);
      }
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 756, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

  


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
