package com.games.screens;

import com.games.managers.GameManager;
import com.games.managers.gameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by SJWeissman on 1/24/14.
 */
public class BreakOutScreen extends JPanel implements Screen, KeyListener
{
    private JFrame gameScreen;
    private String stats;
    private Container c;
    private GameManager _gameMgr;

    public BreakOutScreen(GameManager gm)
    {
        _gameMgr = gm;
        stats = "Breakout Stats";
        initializeBreakoutScreen();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawLine(getWidth() - 200, 0, getWidth() - 200, getHeight());
        g.setFont(new Font(stats, 10, 18));
        g.setColor(Color.red);
        g.drawString(stats, 340, 20);
        _gameMgr.paintGame(g);

    }

    private void initializeBreakoutScreen()
    {
        gameScreen = new JFrame("Breakout");
        c = gameScreen.getContentPane();
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen.setSize(500, 400);
        this.setPreferredSize(new Dimension(500, 400));
        c.add(this);
        gameScreen.pack();
        this.addKeyListener(this);
        this.requestFocusInWindow();
    }
    public void keyPressed(KeyEvent ke){
        int keyCode = ke.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){
            _gameMgr.getEntityMgr().getPlayerBar().setLeft(true);
        }
        else if(keyCode == KeyEvent.VK_RIGHT){
            _gameMgr.getEntityMgr().getPlayerBar().setRight(true);
        }
    }
    @Override
    public void keyReleased(KeyEvent ke){
        int keyCode = ke.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){
            _gameMgr.getEntityMgr().getPlayerBar().setLeft(false);
        }
        else if(keyCode == KeyEvent.VK_RIGHT){
            _gameMgr.getEntityMgr().getPlayerBar().setRight(false);
        }
        if(keyCode == KeyEvent.VK_ENTER){
            if (_gameMgr.getStateMgr().getState() == gameState.atGameScreen)
            {
                _gameMgr.getStateMgr().transitionStartClock();
                _gameMgr.getStateMgr().transitionStartBallMotion();
            }
            if (_gameMgr.getStateMgr().getState() == gameState.playing)
            {
                _gameMgr.getStateMgr().transitionStartBallMotion();
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void repaintScreen()
    {
        repaint();
    }

    @Override
    public void activate()
    {
        gameScreen.setVisible(true);
    }

    @Override
    public void deactivate()
    {
        gameScreen.setVisible(false);
    }

}
