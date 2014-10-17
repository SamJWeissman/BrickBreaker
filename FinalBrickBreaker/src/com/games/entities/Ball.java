package com.games.entities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import com.games.math.Point;

/**
 * Created by SJWeissman on 1/24/14.
 */
public class Ball extends Entity implements Collidable {
    private int xv, yv;
    private final int WIDTH, HEIGHT;
    private Color ballColor;


    public Ball(int xLoc, int yLoc, int screenWidth, int screenHeight){
        WIDTH = screenWidth;
        HEIGHT = screenHeight;
        ballColor = Color.GRAY;
        _position = new Point(xLoc, yLoc);
        xv = 0;
        yv = 0;
        size = 20;
    }

    public void move()
    {
        if (!deadBall())
        {
            _position.set_x(_position.get_x() + xv);
            _position.set_y(_position.get_y() + yv);
            if(_position.get_x() < 0){
                xv *= -1;
            }
            if(_position.get_y() < 0){
                yv *= -1;
            }
            if(_position.get_x() + size > WIDTH){
                xv *= -1;
            }
        }
    }
    public void reset()
    {
        _position.set_x(WIDTH/2);
        _position.set_y(HEIGHT/2);
        xv = 0;
        yv = 0;

    }

    public boolean deadBall()
    {
        if(_position.get_y() + size > HEIGHT){
            return true;
        }
            return false;
    }
    public void changeVertDir()
    {
            yv *= -1;
    }
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(ballColor);
        g2d.fillOval(_position.get_x(),_position.get_y(), size, size);
    }

    @Override
    public void update()
    {
        move();
    }
    @Override
    public boolean detectCollision(Entity entity)
    {
        if (((entity.getX() >= _position.get_x()) &&
                (entity.getX() <= (_position.get_x() + width)) &&
                ((entity.getY() <= (_position.get_y() + height) &&
                        entity.getY() >= _position.get_y()))) ||
                entity.getX() + entity.getSize() == _position.get_x() &&
                        ((entity.getY() <= (_position.get_y() + height) &&
                                entity.getY() >= _position.get_y()))){
            return true;
        }
        else if (((entity.getX() >= _position.get_x()) &&
                (entity.getX() <= (_position.get_x()+ width)) &&
                ((entity.getY() + entity.getSize() <= (_position.get_y() + height) &&
                        entity.getY() + entity.getSize() >= _position.get_y()))) ||
                entity.getX() + entity.getSize() == _position.get_x() &&
                        ((entity.getY() <= (_position.get_y()+ height) &&
                                entity.getY() >= _position.get_y()))){
            return true;
        }
        return false;
    }
    public int getSpeed() {
        return xv;
    }
    public void setSpeed(int speed)
    {
        xv = speed;
        yv = speed;
    }
}
