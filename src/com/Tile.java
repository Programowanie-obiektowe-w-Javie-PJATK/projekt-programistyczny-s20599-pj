package com;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

class Tile extends JComponent {
    private static final int SCALE = 100;
    private static final int BORDER = 10;
    private static final int FONT_SIZE = 40;
    private static Font ARIAL_ROUND = null;
    @Getter
    @Setter
    private int value;

    public Tile(int value){
        try {
            ARIAL_ROUND = Font.createFont(Font.TRUETYPE_FONT, new File("ARLRDBD.TTF")).deriveFont(Font.PLAIN,38);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(ARIAL_ROUND);
        setValue(value);
        setFont(ARIAL_ROUND);
        setPreferredSize(new Dimension(SCALE,SCALE));
    }
    @Override
    public void paintComponent(Graphics g){
        Color color;
        switch (getValue()){
            case 2:
                color = new Color(0x333b60);
                break;
            case 4:
                color = new Color(0x4C5688);
                break;
            case 8:
                color = new Color(0x6673b3);
                break;
            case 16:
                color = new Color(0x333b60);
                break;
            case 32:
                color = new Color(0x333b60);
                break;
            case 64:
                color = new Color(0x333b60);
                break;
            case 128:
                color = new Color(0x333b60);
                break;
            case 256:
                color = new Color(0x333b60);
                break;
            case 512:
                color = new Color(0x333b60);
                break;
            case 1024:
                color = new Color(0x333b60);
                break;
            case 2048:
                color = new Color(0x333b60);
                break;
            default:
                color = new Color(0x6673B3);
                break;
        }
        //Turning on antialiasing in a tile
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //Creating a tile as an filled rectangle
        //g.setColor(new Color(0x6673B3));
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(color);
        String text;
        if(getValue() == 0){
            text = "";
        }
        else{
            text = Integer.toString(getValue());
        }

        //Drawing string in the center of a tile

        g.drawString(text,
                (getWidth() - (getFontMetrics(ARIAL_ROUND).stringWidth(text))) / 2,
                getHeight() / 2 + getFontMetrics(ARIAL_ROUND).getAscent() / 3);
    }
}
