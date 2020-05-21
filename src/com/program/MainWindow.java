package com.program;

import lombok.Getter;
import javax.swing.*;
import java.awt.*;

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
