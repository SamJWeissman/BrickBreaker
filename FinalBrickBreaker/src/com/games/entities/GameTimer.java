package com.games.entities;

import com.games.events.EventAggregator;
import com.games.events.TimeDepletedEvent;
import com.games.math.Point;
import java.awt.*;

/**
 * Created by SJWeissman on 2/19/14.
 */
public class GameTimer extends Entity {
    private int _min = 2;
    private int _sec = 0;
    private long timer;
    private boolean running = false;

    public GameTimer(int xLoc, int yLoc){
        _position = new Point(xLoc, yLoc);
    }
    public void startTimer(){
        timer = System.currentTimeMillis();
        running = true;
    }
    public int getMin(){
        return _min;
    }
    public int getSec(){
        return _sec;
    }
    public void paint(Graphics g){
        if (_sec < 10) {
            g.drawString("Time: " + _min + ":0" + _sec, 340, 100);
        }
        if (_sec == 0) {
            g.drawString("Time: " + _min + ":" + _sec + "0", 340, 100);
        }
        g.drawString("Time: " + _min + ":" + _sec, 340, 100);
    }
    @Override
    public void update() {
        if (running == true){
            if (System.currentTimeMillis() - timer >= 1000){
                if (_min == 2 && _sec == 0){
                    _min = 1;
                    _sec = 59;
                }
                else if (_min == 1 && _sec == 0){
                    _min = 0;
                    _sec = 59;
                }
                else{
                    _sec--;
                }
                timer = System.currentTimeMillis();
            }
        }
        if (_min == 0 && _sec == 0)
        {
            running = false;
            EventAggregator.getEventAggregator().publish(new TimeDepletedEvent());
        }
    }
}