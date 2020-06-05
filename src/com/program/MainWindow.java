package com.program;

import lombok.Getter;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    @Getter
    private Dimension screenSize;

    public void setScreenSize() {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }

    public MainWindow(){
        //Declaration of variables and new objects
        JFrame mainWindow = new JFrame();
        JPanel panel = new JPanel();
        Grid grid = new Grid();
        ScoreBoard scoreBoard = new ScoreBoard();
        setScreenSize();
        int screenWidth = (int)getScreenSize().getWidth();
        int screenHeight = (int)getScreenSize().getHeight();
        //Creating window and setting it's parameters
        mainWindow.setTitle("2048 by Kamil Rominski");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setLocation(screenWidth/3,screenHeight/10);
        mainWindow.setSize(new Dimension(600,800));
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        //Creating matrix and first tiles on it
        List<Tile> tiles = new ArrayList<>();
        TilesMatrix tilesMatrix = new TilesMatrix(tiles);
        tilesMatrix.createMatrix();
        int random = (int)(Math.random() * 16);
        int get_row = random/4;
        int get_column = random - (get_row*4);
        tiles.get(random).createTile();
        tilesMatrix.setTileValue(get_row,get_column,tiles.get(random).getTile_value());
        random = (int)(Math.random() * 16);
        get_row = random/4;
        get_column = random - (get_row*4);
        tiles.get(random).createTile();
        tilesMatrix.setTileValue(get_row,get_column,tiles.get(random).getTile_value());
        //Adding content to the frame and panel
        grid.setSize(new Dimension(600,800));
        scoreBoard.setSize(new Dimension(600,800));
        panel.add(scoreBoard);
        panel.setLayout(null);
        panel.add(grid);
        mainWindow.setContentPane(panel);
    }

    public static void main(String[] args){
        new MainWindow();

    }
}
