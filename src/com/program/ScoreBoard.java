package com.program;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {

    public void Update(){
        repaint();
    }
    public void paintComponent(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRoundRect(340,30,200,50,10,10);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN,35));
        g.drawString("Score: 0",350,65);
    }
}
