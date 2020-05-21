package com.program;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {
    public void paintComponent(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRoundRect(5,10,15,25,35,45);
        g.setColor(Color.BLACK);
        g.drawString("2",5,5);
    }
    public void Update(){
        repaint();
    }
}
