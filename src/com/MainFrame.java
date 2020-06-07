package com;


import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class MainFrame extends JFrame{
    private GameLogic game;
    //Panel which contains every panel
    private JPanel mainPanel;
    //Panel which contains game tiles
    private GamePanel gamePanel;
    //Panel which contains menu buttons and scoreboard
    private JPanel menuPanel;
    @Getter
    private Dimension screenSize;
    @Getter
    @Setter
    private static int screenWidth;
    @Getter
    @Setter
    private static int screenHeight;

    public void setScreenSize(){
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }
    public void updateTiles(){
        for (int col = 0; col < this.game.getCOLUMNS(); col++){
            for (int row = 0; row < this.game.getROWS(); row++){
                this.gamePanel.setValue(col, row, this.game.getTileValue(col, row));
            }
        }
        this.gamePanel.repaint();
    }
    public MainFrame(){
        super("2048 by Kamil Rominski");
        //Create frame somewhere in the middle of the screen
        setScreenSize();
        setScreenWidth((int)getScreenSize().getWidth());
        setScreenHeight((int)getScreenSize().getHeight());
        setLocation(getScreenWidth()/3,getScreenHeight()/10);
        //Creating main panel to attach other components
        this.mainPanel = new JPanel();
        add(this.mainPanel);
        this.game = new GameLogic();
        this.gamePanel = new GamePanel();
        this.gamePanel.setBorder(BorderFactory.createLineBorder(new Color(0x7A92D9),10));
        this.menuPanel = new JPanel();
        this.menuPanel.setBackground(new Color(0x333B60));
        this.menuPanel.setPreferredSize(new Dimension(450,200));
        //Add other panels to mainPanel
        this.mainPanel.add(this.menuPanel);
        this.mainPanel.add(this.gamePanel);
        //Align panels one under other
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel,1));
        //Generate 2 starting tiles
        this.game.generateTile();
        this.game.generateTile();
        updateTiles();
        //Key detection
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
                    MainFrame.this.game.goLeft();
                    MainFrame.this.game.generateTile();
                    MainFrame.this.updateTiles();
                }
                else if (keyEvent.getKeyCode() == KeyEvent.VK_UP){
                    MainFrame.this.game.goUp();
                    MainFrame.this.game.generateTile();
                    MainFrame.this.updateTiles();
                }
                else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
                    MainFrame.this.game.goRight();
                    MainFrame.this.game.generateTile();
                    MainFrame.this.updateTiles();
                }
                else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
                    MainFrame.this.game.goDown();
                    MainFrame.this.game.generateTile();
                    MainFrame.this.updateTiles();
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {}
        });
    }
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setSize(new Dimension(450,650));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
