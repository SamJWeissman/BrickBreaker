package com.games.screens;

import com.games.managers.GameManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 * Created by SJWeissman on 1/24/14.
 */

public class TitleScreen extends JPanel implements ActionListener, Screen {
    private String name = "Shitty Break Out";
    private Container c;
    private JFrame menuScreen;
    private JButton startButton;
    private GameManager _gameMgr;

    public TitleScreen(GameManager gm)
    {
       _gameMgr = gm;
       initializeTitleScreen();
    }
    public void paintComponent(Graphics g)
    {
        g.setFont(new Font(name, 10, 18));
        g.setColor(Color.red);
        g.drawString(name, 90, 20);
    }
    public void actionPerformed(ActionEvent ae)
    {
        Object a = ae.getSource();
        if (a == startButton) {
            _gameMgr.getStateMgr().transitionGameScreen();
        }
    }
    public void initializeTitleScreen()
    {
        {
            menuScreen = new JFrame("Breakout Menu Screen");
            startButton = new JButton("Start");
            startButton.setPreferredSize(new Dimension(100, 100));
            startButton.setBounds(110, 110, 100, 100);
            startButton.addActionListener(this);
            menuScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            c = menuScreen.getContentPane();
            this.setLayout(null);
            this.add(startButton);
            c.add(this);
            this.setPreferredSize(new Dimension(300, 300));
            menuScreen.pack();
        }
    }

    @Override
    public void repaintScreen()
    {
        repaint();
    }

    @Override
    public void activate()
    {
        menuScreen.setVisible(true);
    }

    @Override
    public void deactivate()
    {
        menuScreen.setVisible(false);
    }
}