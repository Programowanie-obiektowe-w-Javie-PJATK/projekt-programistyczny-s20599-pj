package com;


import com.sun.tools.javac.Main;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class MainFrame extends JFrame{
    private GameLogic game;
    //Panel which contains every panel
    private JPanel mainPanel;
    //Panel which contains game tiles
    private GamePanel gamePanel;
    //Panel which contains menu buttons and scoreboard
    private JPanel menuPanel;
    //Scoreboard
    private Scoreboard scoreboard;
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
        System.out.println(this.game.getScore());
        //System.out.println(SwingUtilities.isEventDispatchThread());
    }

    public void save(){
        FileOutputStream file;
        try{
            file = new FileOutputStream(this.game.getClass() + ".dat");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(this.game);
            output.flush();
            output.close();
            file = new FileOutputStream(this.gamePanel.getClass() + ".dat");
            output = new ObjectOutputStream(file);
            output.writeObject(this.gamePanel);
            output.flush();
            output.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //Checks if file exists
    public static boolean fileExists(Object object){
        return new File(object.getClass() + ".dat").isFile();
    }

    public void load(){
        if(fileExists(this.game) && fileExists(this.gamePanel)){
            FileInputStream file;
            FileInputStream file1;
            try{
                file = new FileInputStream(this.game.getClass() + ".dat");
                ObjectInputStream input = new ObjectInputStream(file);
                this.game = (GameLogic)input.readObject();
                input.close();
                file1 = new FileInputStream(this.gamePanel.getClass() + ".dat");
                ObjectInputStream input1 = new ObjectInputStream(file1);
                this.gamePanel = (GamePanel)input1.readObject();
                input1.close();

            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void menuPanel(){
        this.menuPanel = new JPanel();
        this.menuPanel.setBackground(new Color(0x603333));
        this.menuPanel.setPreferredSize(new Dimension(450,200));
        //Add buttons to menuPanel
        this.newGame = new JButton("New Game");
        this.newGame.setFocusable(false);
        this.newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //MainFrame.this.gamePanel = new GamePanel();
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
        this.saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.save();
            }
        });
        this.menuPanel.add(this.saveGame, constraints);
        this.loadGame = new JButton("Load Game");
        constraints.gridy = 2;
        this.loadGame.setFocusable(false);
        this.loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.load();
                MainFrame.this.updateTiles();
            }
        });
        this.menuPanel.add(this.loadGame, constraints);
        this.scoreboard = new Scoreboard();
        constraints.ipady = 0;
        constraints.ipadx = 0;
        constraints.weightx = 0.5;
        constraints.gridy = 0;
        constraints.gridx = 2;
        constraints.gridheight = 3;
        constraints.gridwidth = 3;
        this.menuPanel.add(this.scoreboard,constraints);
    }

    private void gamePanel(){
        this.game = new GameLogic();
        this.gamePanel = new GamePanel();
        this.gamePanel.setBorder(BorderFactory.createLineBorder(new Color(0x884C4C),10));
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
        gamePanel();
        menuPanel();


        //Add other panels to mainPanel

        this.mainPanel.add(this.menuPanel);
        this.mainPanel.add(this.gamePanel);
        //Align panels one under other
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel,1));
        //Generate 2 starting tiles

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
