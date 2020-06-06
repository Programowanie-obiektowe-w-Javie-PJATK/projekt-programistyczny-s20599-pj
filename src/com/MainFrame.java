package com;


import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame{
    //Panel which contains every panel
    private JPanel mainPanel;
    //Panel which contains game tiles
    private JPanel gamePanel;
    //Panel which contains menu buttons and scoreboard
    private JPanel menuPanel;
    public MainFrame(){
        super("2048 by Kamil Rominski");
        this.mainPanel = new JPanel();

        add(this.mainPanel);
        this.gamePanel = new JPanel();
        this.gamePanel.setPreferredSize(new Dimension(600,600));
        this.gamePanel.setBackground(Color.PINK);
        this.menuPanel = new JPanel();
        this.menuPanel.setBackground(Color.GREEN);
        this.menuPanel.setPreferredSize(new Dimension(600,200));
        //Add other panels to mainPanel
        this.mainPanel.add(this.menuPanel);
        this.mainPanel.add(this.gamePanel);
        //Align panels one under other
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel,1));
        //Creating grid in gamePanel
        this.gamePanel.setLayout(new GridLayout(4,4));
        for (int i = 0; i < 16; i++){
            gamePanel.add(new JLabel("test"+i));
        }

    }
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
