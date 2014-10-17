package com.games.entities;

import com.games.managers.EntityManager;
import com.games.managers.GameManager;
import com.games.math.Point;

import javax.swing.*;
import java.awt.*;

/**
 * Created by SJWeissman on 2/17/14.
 */
public class HeadsUpDisplay extends Entity{
    private int _score;
    private int _bricksLeft;
    private int _ballsLeft;
    private EntityManager _entityMgr;

    public HeadsUpDisplay(int xLoc, int yLoc, EntityManager em){
        _position = new Point(xLoc, yLoc);
        _entityMgr = em;
        _score = 0;
        _bricksLeft = 20;
        _ballsLeft = 3;

        width = 199;
        height = 400;
    }
    public void paint(Graphics g)
    {
        g.drawString("Score: " + _score, 340, 180);
        g.drawString("Bricks Left: " + _bricksLeft, 340, 260);
        g.drawString("Balls Left: " + _ballsLeft, 340, 340);
    }
    @Override
    public void update() {
        _score = (20 - _entityMgr.getNumBricks()) * 20;
        _bricksLeft = _entityMgr.getNumBricks();
        _ballsLeft = _entityMgr.getNumOfBalls();
    }
}
