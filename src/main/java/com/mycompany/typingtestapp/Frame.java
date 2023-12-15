
package com.mycompany.typingtestapp;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Frame extends java.awt.Frame implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        Letter l = stringPanel1.charList.get(stringPanel1.pointer);
        char c = e.getKeyChar();
        if (c == l.value) {
            l.drawLetter(stringPanel1.gg,  l.size+10);
            stringPanel1.pointer++;
            stringPanel1.repaint();
            System.out.println(l.value);
            System.out.println(c);
        }
    }
    

   

    
    public Frame() {
        initComponents();
        this.addKeyListener(this);
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stringPanel1 = new com.mycompany.typingtestapp.StringPanel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        stringPanel1.setBackground(new java.awt.Color(13, 16, 54));

        javax.swing.GroupLayout stringPanel1Layout = new javax.swing.GroupLayout(stringPanel1);
        stringPanel1.setLayout(stringPanel1Layout);
        stringPanel1Layout.setHorizontalGroup(
            stringPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        stringPanel1Layout.setVerticalGroup(
            stringPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(stringPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(stringPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.typingtestapp.StringPanel stringPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
