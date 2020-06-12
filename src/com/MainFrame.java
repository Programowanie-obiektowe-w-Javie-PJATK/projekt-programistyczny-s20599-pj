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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainFrame extends JFrame {
    private GameLogic game;
    //Panel which contains every panel
    private JPanel mainPanel;
    //Panel which contains game tiles
    private GamePanel gamePanel;
    private GamePanel gamePanelLoad;
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

    private void gameOver(){
        if(!this.game.canPlay() && getGameOverSet() == 0) {
            setGameOverSet(1);
            String[] options = {"New game","Exit"};
            JDialog.setDefaultLookAndFeelDecorated(false);
            int option = JOptionPane.showOptionDialog(this.mainPanel,"Game Over! Your score was " + this.game.getScore(),"Game Over",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
            if(option == 0){
                newGame();
            }
            else{
                System.exit(0);
            }
        }
    }
    private void gameWin(){
        for(int col = 0; col < 4; col++)
            for (int row = 0; row < 4; row++){
                if(MainFrame.this.game.getTileValue(col,row) == 2048){
                    setGameOverSet(1);
                    String[] options = {"New game","Exit"};
                    JDialog.setDefaultLookAndFeelDecorated(false);
                    int option = JOptionPane.showOptionDialog(this.mainPanel,"You Won! Your score was " + this.game.getScore(),"You Won!",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[0]);

                    if(option == 0){
                        newGame();
                    }
                    else{
                        System.exit(0);
                    }
                    break;
                }
            }
    }

    private void setScreenSize(){
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }

    private void updateTiles(){
        for (int col = 0; col < GameLogic.getCOLUMNS(); col++){
            for (int row = 0; row < GameLogic.getROWS(); row++){
                this.gamePanel.setValue(col, row, this.game.getTileValue(col, row));
            }
        }
        this.gamePanel.revalidate();
        this.gamePanel.repaint();
        System.out.println(this.game.getScore());
    }

    private void save(){
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

    private void newGame(){
        MainFrame.this.gamePanel.reset();
        MainFrame.this.game = new GameLogic();
        MainFrame.this.game.generateTile();
        MainFrame.this.game.generateTile();
        MainFrame.this.updateTiles();
        if(MainFrame.getGameOverSet() == 1){
            setGameOverSet(0);
            scoreboardTimer();
        }
    }

    private void scoreboardTimer(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable timer = new Runnable() {
            @Override
            public void run() {
                if (MainFrame.this.game.getScore() > 0)
                    MainFrame.this.game.setScore(-1);
                MainFrame.this.scoreboard.setScore(MainFrame.this.game.getScore());
                MainFrame.this.scoreboard.repaint();
                if (getGameOverSet() == 1) {
                    try {
                        executorService.awaitTermination(0, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    executorService.shutdownNow();
                }
            }
        };
        if(getGameOverSet() == 0) {
            executorService.scheduleAtFixedRate(timer, 0, 1, TimeUnit.SECONDS);
        }
    }
    //Checks if file exists
    public static boolean fileExists(Object object){
        return new File(object.getClass() + ".dat").isFile();
    }

    public void load(){
        if(fileExists(MainFrame.this.game) && fileExists(MainFrame.this.gamePanel)){
            FileInputStream file;
            FileInputStream file1;
            try{
                this.gamePanelLoad = new GamePanel();
                file = new FileInputStream(MainFrame.this.game.getClass() + ".dat");
                ObjectInputStream input = new ObjectInputStream(file);
                MainFrame.this.game = (GameLogic)input.readObject();
                input.close();
                file1 = new FileInputStream(MainFrame.this.gamePanel.getClass() + ".dat");
                ObjectInputStream input1 = new ObjectInputStream(file1);
                this.gamePanelLoad = (GamePanel)input1.readObject();
                input1.close();
            }
            catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this.mainPanel, e.getMessage() , "Error message", JOptionPane.PLAIN_MESSAGE);

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
                newGame();
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
                for(int col = 0; col < 4; col++)
                    for(int row = 0; row < 4; row++)
                        MainFrame.this.gamePanel.setValue(col,row,MainFrame.this.gamePanelLoad.getValue(col,row));
                MainFrame.this.updateTiles();
                if(MainFrame.getGameOverSet() == 1){
                    setGameOverSet(0);
                    scoreboardTimer();
                }
            }
        });
        this.menuPanel.add(this.loadGame, constraints);
        this.scoreboard = new Scoreboard(this.game.getScore());
        constraints.weightx = 0;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        this.menuPanel.add(this.scoreboard,constraints);
        scoreboardTimer();
    }
    private void moveAction(){
        MainFrame.this.game.generateTile();
        MainFrame.this.updateTiles();
        MainFrame.this.gameOver();
        MainFrame.this.gameWin();
        MainFrame.this.scoreboard.setScore(MainFrame.this.game.getScore());
        MainFrame.this.scoreboard.repaint();
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
                    moveAction();
                }
                else if (keyEvent.getKeyCode() == KeyEvent.VK_UP){
                    MainFrame.this.game.goUp();
                    moveAction();
                }
                else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
                    MainFrame.this.game.goRight();
                    moveAction();
                }
                else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
                    MainFrame.this.game.goDown();
                    moveAction();
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
