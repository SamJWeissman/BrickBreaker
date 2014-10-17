package com.games.entities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import com.games.math.Point;

/**
 * Created by SJWeissman on 1/25/14.
 */
public class Brick extends Entity implements Collidable{

    public Brick(int xLoc, int yLoc){

        _position = new Point(xLoc, yLoc);
        width = 50;
        height = 20;
        size = 50;

    }

    @Override
    public void paint(Graphics g){

        if (_position.get_y() > 0 && _position.get_y() < 25){
            g.setColor(Color.RED);
        }
        if (_position.get_y() > 25 && _position.get_y() < 50){
            g.setColor(Color.GREEN);
        }
        if (_position.get_y() > 50 && _position.get_y() < 75){
            g.setColor(Color.BLUE);
        }
        if (_position.get_y() > 75 && _position.get_y() < 100){
            Color purple = new Color(147, 0, 219);
            g.setColor(purple);
        }
        g.fillRect(_position.get_x(), _position.get_y(), width, height);
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
    @Override
    public void update() {
    }
}
