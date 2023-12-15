
package com.mycompany.typingtestapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Letter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer){
            
            size += v;
            if (!(size >=25 && size < 30)) {
                v = v * -1;
            }
            counter++;
            if (counter == 10) {
                timer.stop();
               
            }
            panel.repaint();
            panel.revalidate();
            
        }
    }
    JPanel panel;
    char value;
    int x,y;
    int size;
    int color=0;

    Timer timer;
    int counter = 0;
    int v = 1;
    Graphics2D gg;
    Letter(char value,int x, int y,int size,JPanel panel ){
        this.value=value;
        this.x=x;
        this.y=y;
        this.size=size;
        this.panel=panel;
        timer=new Timer(10,this);
        
    }
    public void drawLetter(Graphics2D g,int size){
        gg=g;
        switch (color) {
            case 0:
                g.setPaint(Color.black);
                break;
            case 1:
                g.setPaint(Color.decode("#02DD8E"));
                break;
            case 2:
                g.setPaint(Color.red);
                break;
        }
        
        
        g.setFont(new Font("Arial",Font.PLAIN,size));
        if(value==' '&&color==2)
            g.fillOval(x, y-12, 6, 6);
        else
            g.drawString(String.valueOf(value),x,y);
        
    }
 
}
