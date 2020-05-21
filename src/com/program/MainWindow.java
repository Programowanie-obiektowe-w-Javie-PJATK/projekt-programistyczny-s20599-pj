package com.program;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    public MainWindow(){
        //Declaration of variables and new objects
        JFrame mainWindow = new JFrame();
        JPanel panel = new JPanel();
        Grid grid = new Grid();
        ScoreBoard scoreBoard = new ScoreBoard();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();
        //Creating window and setting it's parameters
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setLocation(screenWidth/3,screenHeight/10);
        mainWindow.setSize(new Dimension(600,800));
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        //Adding content to the frame and panel
        grid.setSize(new Dimension(600,800));
        //panel.add(scoreBoard);
        panel.setLayout(null);
        panel.add(grid);
        mainWindow.setContentPane(panel);
    }
    public static void main(String[] args){
        new MainWindow();

    }
}
