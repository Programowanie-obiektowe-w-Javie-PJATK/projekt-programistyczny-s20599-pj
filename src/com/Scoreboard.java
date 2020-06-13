package com;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class Scoreboard extends JComponent {
    private static final int FONT_SIZE = 20;
    private static Font ARIAL_ROUND = null;
    @Setter
    @Getter
    private int score;
    Scoreboard(int score){
        try {
            ARIAL_ROUND = Font.createFont(Font.TRUETYPE_FONT, new File("ARLRDBD.TTF")).deriveFont(Font.PLAIN,FONT_SIZE);
        } catch (FontFormatException | IOException | NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Error occurred while retrieving save file." , "Error message", JOptionPane.PLAIN_MESSAGE);
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(ARIAL_ROUND);
        setScore(score);
        setFont(ARIAL_ROUND);
        setPreferredSize(new Dimension(200,90));
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(0x884C4C));
        //Turn on antialiasing in the object
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0,0,200,90,30,30);
        g.setColor(Color.BLACK);
        g.drawString("Score",(getWidth() - (getFontMetrics(ARIAL_ROUND).stringWidth("score"))) / 2,40);
        g.drawString(Integer.toString(score),
                (getWidth() - (getFontMetrics(ARIAL_ROUND).stringWidth((Integer.toString(score))))) / 2,
                70);
    }
}
