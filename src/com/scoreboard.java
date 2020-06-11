package com;

import javax.swing.*;
import java.awt.*;

class Scoreboard extends JComponent {

    Scoreboard(){

        setPreferredSize(new Dimension(200,100));
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        //Turn on antialiasing in the object
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0,0,200,100,30,30);
        g.setColor(Color.BLACK);
        g.drawString("Score",5,10);
    }
}
