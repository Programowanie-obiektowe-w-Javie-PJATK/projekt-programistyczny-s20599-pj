package com.program;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    @Setter
    @Getter
    private int _x = 5;
    @Setter
    @Getter
    private int _y = 182;
    @Setter
    private int x_padding = 20;
    @Getter
    @Setter
    private int tile_value = 0;
    //Tile constructor
    Tile(int x, int y){
        set_x(x);
        set_y(y);
    }
    //Setting color of tile background based on the value
    Color tileBackground(int value){
        if(value == 2){
            return new Color(255,255,255);
        }
        if(value == 4) {
            return new Color(255,0,0);
        }
        if(value == 8){
            return new Color(0,255,0);
        }
        if(value == 16){
            return new Color(0,0,255);
        }
        if(value == 32){
            return new Color(255,255,0);
        }
        if(value == 64){
            return new Color(0,255,255);
        }
        if(value == 128){
            return new Color(255,128,0);
        }
        if(value == 256){
            return new Color(255,0,128);
        }
        if(value == 512){
            return new Color(255,0,255);
        }
        if(value == 1024){
            return new Color(128,255,0);
        }
        if(value == 2048){
            return new Color(128,128,128);
        }
        return new Color(0,0,0);
    }
    //Create new tile after move
    public void createTile(){
        int random = (int)(Math.random()*2);
        if(random == 2){
            setTile_value(2);
        }
        else{
            setTile_value(4);
        }
        repaint();
    }
    //Setting font and padding for text in tile
    Font setFontAndPadding(int value){
        if(value >8 && value <128){
            setX_padding(20);
            return new Font("Arial", Font.BOLD,24);
        }
        if(value >= 128 && value <= 1024){
            setX_padding(15);
            return new Font("Arial",Font.BOLD,20);
        }
        if(value >=1024){
            setX_padding(10);
            return new Font("Arial",Font.BOLD,16);
        }
        setX_padding(25);
        return new Font("Arial",Font.BOLD,28);
    }
    public void updateTile(){
        this.repaint();
    }
    //Main painting component used to draw tile in grid
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.YELLOW);
        g.drawRect(_x,_y,144,144);
        if(tile_value != 0) {
            g.setColor(tileBackground(getTile_value()));
        }
        g.setColor(Color.WHITE);
        g.setFont(setFontAndPadding(getTile_value()));
        if(tile_value != 0){
            g.drawString(String.valueOf(getTile_value()),_x+x_padding,_y+72);
        }

    }
}
