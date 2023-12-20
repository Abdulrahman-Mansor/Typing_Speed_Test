
package com.mycompany.typingtestapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Vector;

import javax.swing.JPanel;
//was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do: once or twice she had peeped into the book her sister was reading, but it had no pictures or conversations in it, and where is the use of a book, thought Alice, without pictures or conversations? So she was considering in her own mind, (as well as she could, for the hot day made her feel very sleepy and stupid,) whether the pleasure of making a daisy-chain was worth the trouble of getting up and picking the daisies, when a white rabbit with pink eyes ran close by her.
public class MyPanel extends JPanel {
    private Font font = new Font(null, Font.PLAIN, 25);//Arial
    Vector<Letter> charList;
    String paragraph = "Hello, everyone! I'm excited to announce my new project, TypingSpeed! I developed this project using Java Programming Language.";
    Graphics2D gg;
   
    int pointer = 0;
    FontMetrics fm ;
    int lineNo=0;
    int x=10;
   Color parentColor;
   boolean incLine=false;
    MyPanel(){
        this.setOpaque(false);
        fm = getFontMetrics(font);
        this.setBounds(10,300,1200-38,400-50);
//        this.setBackground(parentColor);
        charList = new Vector<>(0);
        
        for (int i = 0; i < paragraph.length(); i++) {
            
            char c = paragraph.charAt(i);
            if(c==' '&&x+fm.charWidth(' ')<1100){
                int j=i+1;
                int xx=x+fm.charWidth(' ');
                char cc=paragraph.charAt(j);
                while(cc!=' '){                  
                    xx += fm.charWidth(cc);
                    if (xx >= 1100) {
                        incLine = true;
                        break;
                    }
                    if(j==paragraph.length()-1)break;
                    j++;
                    cc=paragraph.charAt(j);
                }
            }
            if(incLine){
                lineNo++;
                x = 10;
                incLine=false;
            }
            charList.add(new Letter(c,x, 50 + 25 *lineNo , 25, this));//x=dif +( (15) * i)%1100
            x+=fm.charWidth(c);
            if(x>=1100){
                lineNo++;
                x = 10;
            }
        }
    }
    @Override
    public void paint(Graphics g) {
//        super.paint(g);
        gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gg.setFont(font);
        GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#5E25A8"), getWidth(), getHeight(), Color.decode("#d74eff"));
        gg.setPaint(gradient);
//        gg.fillRect(0, 0, getWidth(), getHeight());
//        gg.setPaint(new Color(200,200,200));
        gg.fillRoundRect(0, 0, 1200-38, 350, 50,50);
//        gg.fillRect(0,0,1200-38,350);
        gg.setPaint(Color.red);
        for (Letter l : charList) {
            l.drawLetter(gg, l.size);
        }
    }
        
}
