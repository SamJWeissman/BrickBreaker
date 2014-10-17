package com.games.entities;

import com.games.math.Point;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by SJWeissman on 2/7/14.
 */
public abstract class Entity
{
    protected Point _position;

    protected int height;
    protected int width;
    protected int size;

    public void paint(Graphics g) {}
    public boolean detectCollision(Entity entity){
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
    public abstract void update();
    public Point position() { return _position; }
    public int getX(){ return _position.get_x() ; }
    public int getY(){ return _position.get_y() ; }
    public int getSize() { return size ; }
    //public abstract void input(ArrayList<Integer> keysPressed);
}
