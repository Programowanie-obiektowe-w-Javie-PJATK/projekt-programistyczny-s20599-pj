package com;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

class GamePanel extends JPanel implements Serializable {
    @Getter
    private static final int COLUMNS = 4;
    @Getter
    private static final int ROWS = 4;
    private Tile[][] TILES;

    public int getValue(int column, int row){
        return this.TILES[column][row].getValue();
    }

    public void setValue(int column, int row, int value){
        this.TILES[column][row].setValue(value);
    }

    public void initialization(){
        removeAll();
        setLayout(new GridLayout(getROWS(),getCOLUMNS(),10,10));
        setBackground(new Color(0x884C4C));
        this.TILES = null;
        this.TILES = new Tile[getCOLUMNS()][getROWS()];
        for (int col = 0; col < getCOLUMNS(); col++){
            for (int row = 0; row < getROWS(); row++){
                this.TILES[col][row] = new Tile(0);
                add(this.TILES[col][row]);
            }
        }
    }
    public void reset(){
        for (int col = 0; col < getCOLUMNS(); col++){
            for (int row = 0; row < getROWS(); row++){
                this.TILES[col][row].setValue(0);
            }
        }
    }


    public GamePanel(){
        initialization();
    }
}
