package com.mycompany.typingtestapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Single extends JFrame implements KeyListener, ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            wrongLetters=0;
            for(int i=0;i<=centerPanel.pointer;i++)
                if(centerPanel.charList.get(i).color==2)
                    wrongLetters++;
            acc = 100 - (100 * (((float) wrongLetters) / centerPanel.paragraph.length()));
            upperPanel.acc = Float.toString(acc) + "%";
            double dis = centerPanel.pointer / 5;
            if (wordTime != 0) {
                speed = 1.0 * dis / ((wordTime * 1.0) / (60000));
            }

            int s = (int) speed;
            upperPanel.speed = (Integer.toString(s) + " WPM");
            upperPanel.repaint();
            wordTime += 500;

        }
        if (e.getSource() == countDownTimer) {
            if (countDown > 1) {
                cSize += cV;
                countDown--;
                if (countDown%5==0) {
                    cV = cV * -1;
                }
   
            } else {
                countRunning = true;
                countDownTimer.stop();
                timer.start();
                upperPanel.timer.start();
                upperPanel.s();
                wordTime = 0;
                countDown--;
                canType = true;
            }
        
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        Letter l = centerPanel.charList.get(centerPanel.pointer);
        char c = e.getKeyChar();
        if (c == l.value && canType  ) {
            if(l.color!=2)
                l.color = 1;
            
            l.drawLetter(centerPanel.gg, l.size);
            l.counter = 0;
            l.v = 1;
            l.timer.start();
            if (centerPanel.pointer != centerPanel.paragraph.length() - 1) {
                centerPanel.pointer++;
            } else {
                upperPanel.timer.stop();
                timer.stop();
                JOptionPane.showMessageDialog(null, (int)speed, "finished", JOptionPane.PLAIN_MESSAGE);
                int s = 0;
                if (speed > ((int) speed) + 0.5) {
                    s = ((int) speed) + 1;
                } else {
                    s = ((int) speed);
                }
                upperPanel.speed = (Integer.toString(s) + " WPM");
                this.removeKeyListener(this);
            }
        }
        else if(canType && !backSpace&&l.color!=2){
            
//            centerPanel.pointer++;
            l.color = 2;
            l.drawLetter(centerPanel.gg, l.size);
            centerPanel.repaint();
        }
        backSpace=false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!countRunning) {

            countDownTimer.start();

        }
        Letter l = centerPanel.charList.get(centerPanel.pointer);
        if (e.getKeyCode() == 8 && l.color == 0 && centerPanel.pointer > 0) {
            Letter ll = centerPanel.charList.get(--centerPanel.pointer);
            ll.color = 0;
            backSpace = true;
            ll.drawLetter(centerPanel.gg, l.size);
            ll.counter = 0;
            ll.v = 0;
            centerPanel.repaint();
        } else if (e.getKeyCode() == 8 && l.color == 2  && centerPanel.pointer > 0) {
            l.color = 0;
            backSpace=true;
            l.drawLetter(centerPanel.gg, l.size);
            centerPanel.repaint();
        } 


    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    Socket socket;
    MyPanel centerPanel;
    UpperPanel upperPanel;
    Timer timer;
    Timer countDownTimer;
    double speed = 0;
    long wordTime = 0;
    int countDown = 50; 
    int cSize=30; int cV=5;
    boolean canType = false, countRunning = false;
    int wrongLetters=0;
    float acc=0f;
    boolean backSpace=false;
 

    Single(String title, Socket socket) {
        this.socket = socket;
        
        timer = new Timer(500, this);
        countDownTimer = new Timer(100, this);
        if (this.socket.isConnected()) {
            countDownTimer.start();
        }
        this.getContentPane().setBackground(Color.decode("#0D1036"));

        centerPanel = new MyPanel();
        centerPanel.parentColor = this.getContentPane().getBackground();
        upperPanel = new UpperPanel();
        upperPanel.parentColor = this.getContentPane().getBackground();

       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 700);
        this.setLayout(null);
        this.add(centerPanel);
        this.add(upperPanel);

        this.addKeyListener(this);
        this.setResizable(false);
        this.setTitle(title);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gg = (Graphics2D) g;
        gg.setPaint(Color.decode("#FFD6E4"));
        gg.setFont(new Font("Arial", Font.PLAIN, cSize));
        if (countDown > 0) {
            gg.drawString(Integer.toString(countDown/10), 600, 150);
        }
    }
}
