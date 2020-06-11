package com;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class Tile extends JComponent {
    private static final int SCALE = 100;
    private static final int BORDER = 10;
    private static final int FONT_SIZE = 38;
    private static Font ARIAL_ROUND = null;
    @Getter
    @Setter
    private int value;

    public Tile(int value){
        try {
            ARIAL_ROUND = Font.createFont(Font.TRUETYPE_FONT, new File("ARLRDBD.TTF")).deriveFont(Font.PLAIN,FONT_SIZE);
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
        super.paintComponent(g);
        Color color;
        switch (getValue()){
            case 2:
                color = new Color(0xDEB297);
                break;
            case 4:
                color = new Color(0xD99E7A);
                break;
            case 8:
                color = new Color(0xB37F66);
                break;
            case 16:
                color = new Color(0x88664C);
                break;
            case 32:
                color = new Color(0x714733);
                break;
            case 64:
                color = new Color(0x604233);
                break;
            case 128:
                color = new Color(0x5A3928);
                break;
            case 256:
                color = new Color(0x483020);
                break;
            case 512:
                color = new Color(0x3D2619);
                break;
            case 1024:
                color = new Color(0x2C1D12);
                break;
            case 2048:
                color = new Color(0x251810);
                break;
            default:
                color = new Color(0xD97A7A);
                break;
        }
        //Turning on antialiasing in a tile
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //Creating a tile as an filled rectangle
        g.setColor(color);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.BLACK);
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
