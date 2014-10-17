package com.games.entities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import com.games.math.Point;

/**
 * Created by SJWeissman on 1/24/14.
 */
public class PowerBar extends Entity implements Collidable {

    private final int WIDTH, HEIGHT;
    private boolean left = false, right = false;

    public PowerBar(int screenWidth, int screenHeight){

        WIDTH = screenWidth;
        HEIGHT = screenHeight;
        height = 20;
        width = 50;
        size = 50;
        _position = new Point((WIDTH/2 - 20),(HEIGHT - 30));
    }
    public void move(){
        if(left){
            _position.set_x(_position.get_x() - 3);
        }
        else if(right){
            _position.set_x(_position.get_x() + 3);
        }
        if(_position.get_x() + width > WIDTH){
            _position.set_x(WIDTH - width);
        }
        if(_position.get_x() < 0){
            _position.set_x(0);
        }
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
        move();
    }

    public void paint(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(_position.get_x(), _position.get_y(), width, height);
    }

    public void setLeft(boolean b){
        left = b;
    }
    public void setRight(boolean b){
        right = b;
    }
}
