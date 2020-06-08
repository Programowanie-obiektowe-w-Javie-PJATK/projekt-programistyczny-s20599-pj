package com;


import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    //New game button
    private JButton newGame;
    private JButton saveGame;
    private JButton loadGame;
    @Getter
    private Dimension screenSize;
    @Getter
    @Setter
    private static int screenWidth;
    @Getter
    @Setter
    private static int screenHeight;
    @Getter
    @Setter
    private static int gameOverSet = 0;

    public void gameOver(){

        if(!this.game.canPlay() && getGameOverSet() == 0) {
            JOptionPane.showMessageDialog(this.mainPanel, "Game Over! Your score was ", "Game Over", JOptionPane.PLAIN_MESSAGE);
            setGameOverSet(1);
        }
    }

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
        //Add buttons to menuPanel
        this.newGame = new JButton("New Game");
        this.newGame.setFocusable(false);
        this.newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.gamePanel.initialization();
                MainFrame.this.game = new GameLogic();
                MainFrame.this.game.generateTile();
                MainFrame.this.game.generateTile();
                MainFrame.this.setGameOverSet(0);
                MainFrame.this.updateTiles();
            }
        });
        this.menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.menuPanel.add(this.newGame, constraints);
        this.saveGame = new JButton("Save Game");
        constraints.gridy = 1;
        this.saveGame.setFocusable(false);
        this.menuPanel.add(this.saveGame, constraints);
        this.loadGame = new JButton("Load Game");
        constraints.gridy = 2;
        this.loadGame.setFocusable(false);
        this.menuPanel.add(this.loadGame, constraints);

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
                    MainFrame.this.gameOver();
                }
                else if (keyEvent.getKeyCode() == KeyEvent.VK_UP){
                    MainFrame.this.game.goUp();
                    MainFrame.this.game.generateTile();
                    MainFrame.this.updateTiles();
                    MainFrame.this.gameOver();
                }
                else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
                    MainFrame.this.game.goRight();
                    MainFrame.this.game.generateTile();
                    MainFrame.this.updateTiles();
                    MainFrame.this.gameOver();
                }
                else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
                    MainFrame.this.game.goDown();
                    MainFrame.this.game.generateTile();
                    MainFrame.this.updateTiles();
                    MainFrame.this.gameOver();
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
