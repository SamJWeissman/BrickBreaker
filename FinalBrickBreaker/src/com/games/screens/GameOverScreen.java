package com.games.screens;

import com.games.managers.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SJWeissman on 1/28/14.
 */
public class GameOverScreen extends JPanel implements ActionListener, Screen
{
    private String name;
    private Container c;
    private JFrame gameOverScreen;
    private JButton tryAgain;
    private JButton exit;
    private GameManager _gameMgr;

    public GameOverScreen( String title, GameManager gm )
    {
        name = title;
        _gameMgr = gm;
        initializeGameOverScreen();
    }
    public void paintComponent(Graphics g)
    {
        g.setFont(new Font(name, 10, 18));
        g.setColor(Color.red);
        g.drawString(name, getWidth()/2 - 40, 20);
    }

    public void actionPerformed(ActionEvent ae)
    {
        Object a = ae.getSource();
        if (a == tryAgain) {
            _gameMgr.getStateMgr().transitionGameScreen();
        }
        else if (a == exit){
            _gameMgr.getStateMgr().transitionExit();
        }
    }
    public void initializeGameOverScreen()
    {
        gameOverScreen = new JFrame("Game Over");
        tryAgain = new JButton("Play Again");
        exit = new JButton("Exit");
        tryAgain.setPreferredSize(new Dimension(100, 50));
        tryAgain.setBounds(50, 100, 100, 50);
        tryAgain.addActionListener(this);
        exit.setPreferredSize(new Dimension(100, 50));
        exit.setBounds(150, 100, 100, 50);
        exit.addActionListener(this);
        gameOverScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = gameOverScreen.getContentPane();
        this.setLayout(null);
        this.add(tryAgain);
        this.add(exit);
        c.add(this);
        c.setPreferredSize(new Dimension(300,300));
        gameOverScreen.pack();
    }
    @Override
    public void activate()
    {
        gameOverScreen.setVisible(true);
    }

    @Override
    public void repaintScreen()
    {
        repaint();
    }

    @Override
    public void deactivate()
    {
        gameOverScreen.setVisible(false);
    }
}
