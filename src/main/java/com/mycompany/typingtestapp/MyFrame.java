
package com.mycompany.typingtestapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Thread.interrupted;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MyFrame extends JFrame implements KeyListener, ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {

            double dis = centerPanel.pointer / 5;
            if (wordTime != 0) {
                speed = 1.0 * dis / ((wordTime * 1.0) / (60000));
            }

            int s = (int) speed;
            upperPanel.speed = (Integer.toString(s) + " WPM");
            upperPanel.repaint();
            wordTime += 500;
            String message = Integer.toString(s) + " " + Integer.toString(progress);
            try {
                dout.writeUTF(message);
            } catch (Exception exp) {
            }
            repaint();

        }

        if (e.getSource() == countDownTimer) {
            if (countDown > 1) {
                cSize += cV;
                countDown--;
                if (countDown % 5 == 0) {
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
        if (c == l.value && canType) {

            l.color = 1;
            l.drawLetter(centerPanel.gg, l.size);
            l.counter = 0;
            l.v = 1;
            l.timer.start();
            double p = 1.0 * centerPanel.pointer / (centerPanel.paragraph.length() - 1);
            progress = (int) (p * 100);
            System.out.println(progress + " " + oProgress);
            if (centerPanel.pointer != centerPanel.paragraph.length() - 1) {
                centerPanel.pointer++;
            } else {
                upperPanel.timer.stop();
                timer.stop();
                int s = 0;
                if (speed > ((int) speed) + 0.5) {
                    s = ((int) speed) + 1;
                } else {
                    s = ((int) speed);
                }
                upperPanel.speed = (Integer.toString(s) + " WPM");
                this.removeKeyListener(this);
                progress = 100;

                if (oProgress < 100) {
                    JOptionPane.showMessageDialog(null, "U WON!");
                    try {
                        dout.writeUTF(Integer.toString(s) + " " + Integer.toString(progress));
                    } catch (Exception exp) {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "U LOSE:(");
                }
                repaint();
            }
        }
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
            ll.drawLetter(centerPanel.gg, l.size);
            ll.counter = 0;
            ll.v = 0;
            centerPanel.repaint();
        } else if (e.getKeyCode() == 8 && l.color == 2 && centerPanel.pointer > 0) {
            l.color = 0;
            l.drawLetter(centerPanel.gg, l.size);
            centerPanel.repaint();
        } else if (centerPanel.pointer > 0 && e.getKeyCode() != 16) {
            l.color = 2;
            l.drawLetter(centerPanel.gg, l.size);
            centerPanel.repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    //    boolean heWin=false;
    boolean iWin = false;
    Socket socket;
    DataInputStream din;
    DataOutputStream dout;
    ReceiverThread receiver;
    MyPanel centerPanel;
    UpperPanel upperPanel;
    Timer timer;
    Timer countDownTimer;
    double speed = 0;
    long wordTime = 0;
    int countDown = 50;
    int cSize = 20;
    int cV = 5;
    boolean canType = false, countRunning = false;
    int progress = 0;
    int oProgress = 0;
    int oSpeed = 0;
    String name = "", oName = " ";

    MyFrame(String name, String oName, Socket socket) {
        this.socket = socket;
        timer = new Timer(500, this);
        countDownTimer = new Timer(100, this);
        if (this.socket.isConnected()) {
            countDownTimer.start();
        }
        setupSocket();
        this.getContentPane().setBackground(Color.decode("#0D1036"));

        centerPanel = new MyPanel();
        centerPanel.parentColor = this.getContentPane().getBackground();
        upperPanel = new UpperPanel();
        upperPanel.parentColor = this.getContentPane().getBackground();
        this.name = name;
        this.oName = oName;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 700);
        this.setLayout(null);
        this.add(centerPanel);
        this.add(upperPanel);
        this.addKeyListener(this);
        this.setResizable(false);
        this.setTitle(name);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    private void setupSocket() {
        // 1. Open Streams
        try {
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
        }

        // 2. Start the receiver thread (for read)
        receiver = new ReceiverThread();
        receiver.start();
    }

    private void isEnd(String info) {
        StringTokenizer st = new StringTokenizer(info);
        String s = st.nextToken();
        String p = st.nextToken();
        if (Integer.parseInt(p) == 100 && this.progress == 100) {
            try {
                din.close();
                dout.close();
                socket.close();

            } catch (Exception e) {
            }

            receiver.interrupt();
        } else {
            oProgress = Integer.parseInt(p);
            oSpeed = Integer.parseInt(s);
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gg.setPaint(Color.decode("#FFD6E4"));
        gg.setFont(new Font("Arial", Font.PLAIN, 18));
        gg.drawString(name, 300, 200);
        gg.drawString(oName, 300, 250);
        gg.drawLine(350, 200, 850, 200);
        gg.drawLine(350, 250, 850, 250);
        gg.drawString(Integer.toString((int) speed) + " WPM", 900, 200);
        gg.drawString(Integer.toString(oSpeed) + " WPM", 900, 250);

        gg.drawOval(350 + progress * 5, 200, 20, 20);
        gg.drawOval(350 + oProgress * 5, 250, 20, 20);

        gg.setFont(new Font("Arial", Font.PLAIN, cSize));
        if (countDown > 0) {
            gg.drawString(Integer.toString(countDown / 10), 1100, 150);
        }
    }

    class ReceiverThread extends Thread {

        public void run() {
            while (!interrupted()) {
                try {
                    String info = din.readUTF();
                    isEnd(info);
                } catch (IOException e) {
                }
            }
        }
    }
}
