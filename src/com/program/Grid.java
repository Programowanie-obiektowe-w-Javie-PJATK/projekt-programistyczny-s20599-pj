package com.program;

import javax.swing.*;
import java.awt.*;

public final class Grid extends JPanel {
    Grid(){
        updateGrid();
    }
    public void updateGrid(){
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        //set color
        g.setColor(Color.BLACK);
        //vertical lines
        g.drawRect(4,181,576,576);
        g.drawLine(148,181,148,757);
        g.drawLine(292,181,292,757);
        g.drawLine(436,181,436,757);
        //horizontal lines
        g.drawLine(4,325,580,325);
        g.drawLine(4,469,580,469);
        g.drawLine(4,613,580,613);
    }
}
