
package com.mycompany.typingtestapp;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class UpperPanel extends JPanel implements ActionListener  {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer){
            if(minuits<=0&&seconds<=0)
                timer.stop();
            else if(seconds<=0&&minuits>0){
                seconds=59; minuits--;
            }
            else if(seconds>0)seconds--;
            
            if(minuits<10 && seconds < 10){
                time="0"+minuits;
                time+=":0"+seconds;
            }
            else if(seconds>=10&&minuits>=10)time = minuits +":"+ seconds;
            else if(seconds>=10 && minuits<10)time = "0"+minuits+":"+seconds;
            else if(seconds<10 && minuits>=10)time = minuits+":0"+seconds;
         
            repaint();
        }
        else if(e.getSource()==reloadTimer){
            if(r==0)r=1;
           repaint();
        }
    }
    Timer reloadTimer;
    int r =0;
    Color parentColor;
    Timer timer;
    int seconds = 0;
    int minuits = 02;
    Graphics2D gg;
    String time,speed="0 WPM",acc="100%";
    
    public UpperPanel() {
        reloadTimer = new Timer(30,this);
        timer = new Timer(1000,this);
        if (minuits < 10 && seconds < 10) {
            time = "0" + minuits;
            time += ":0" + seconds;
        } else if (seconds >= 10 && minuits >= 10)
            time = minuits + ":" + seconds;
        else if (seconds >= 10 && minuits < 10)
            time = "0" + minuits + ":" + seconds;
        else if (seconds < 10 && minuits >= 10)
            time = minuits + ":0" + seconds;
        this.setLayout(new FlowLayout());
        this.setBounds(10, 0, 1162, 60);
//        this.setBackground(parentColor);
        this.setOpaque(false);
        
    }
    public void s(){
        repaint();
        reloadTimer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        gg = (Graphics2D) g;
        gg.setPaint(new Color(0, 153, 153));
//        if(r==0)gg.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
//        else if(r<17){
//            gg.fillRect(getWidth() / 2 - r * 30, 0, 50 + 2*r * 30, getHeight());
//        r++;
//        }
//        
//        else {
//            gg.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
//            reloadTimer.stop();
//        }
        
//        reloadTimer.start();
        gg.setPaint(Color.white);
        gg.setFont(new Font("Arial",Font.PLAIN,30));
        gg.drawString(time,540,40);
        gg.drawString(speed, 700, 40);
        gg.drawString(acc, 370, 40);
       
    }
    
}
