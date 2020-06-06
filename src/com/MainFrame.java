package com;


import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
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
