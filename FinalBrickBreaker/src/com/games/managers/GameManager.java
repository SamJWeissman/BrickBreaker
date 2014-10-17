package com.games.managers;

import com.games.entities.Entity;
import com.games.events.*;
import com.games.screens.BreakOutScreen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by SJWeissman on 2/13/14.
 */
public class GameManager extends Manager implements Runnable, EventHandler {
    private Thread thread;
    private int updates = 0, fps = 0;
    private ScreenManager _screenMgr;
    private StateManager _stateMgr;
    private EntityManager _entityMgr;
    private boolean running = false;


    public GameManager()
    {

        _screenMgr = new ScreenManager(this);
        _entityMgr = new EntityManager(300,_screenMgr.getBreakoutScreen().getHeight());
        _stateMgr = new StateManager(_screenMgr,_entityMgr);

        start();
        
        EventAggregator.getEventAggregator().register(this);

    }
    
    public void start()
    {
        thread = new Thread(this);
        thread.start();
    }

    public ScreenManager getScreenMgr(){ return _screenMgr; }
    public StateManager getStateMgr() { return _stateMgr; }
    public EntityManager getEntityMgr() { return _entityMgr; }

    public void run()
    {
        System.out.println("Waiting for Enter Key");
        long timer = System.currentTimeMillis();
        running = true;
        while(true)
        {
            if(running == true)
            {
                updates++;
                fps++;

                update();
                if(_screenMgr.getActiveScreen() instanceof BreakOutScreen)
                {
                    _screenMgr.repaintScreens();
                }

                if ((System.currentTimeMillis() - timer ) >= 1000 ){
                    System.out.println("Updates: " + updates + " FPS: " + fps);
                    timer = System.currentTimeMillis();
                    updates = 0;
                    fps = 0;
                }
                try{
                    Thread.sleep(17);
                } catch (InterruptedException e){
                    e.printStackTrace();
                    }
            }
            else{
                _entityMgr = new EntityManager(300,_screenMgr.getBreakoutScreen().getHeight());
                _stateMgr.setEntityMgr(_entityMgr);
                running = true;
                }
        }
    }
    public void update()
    {
        _entityMgr.update();
    }
    public void paintGame(Graphics g){
        _entityMgr.paintEntities(g);
    }

    public void handleEvent(com.games.events.Event event)
    {
        if(event instanceof DeadBallEvent)
        {
            DeadBallEvent deadballEvent = (DeadBallEvent)event;
            _stateMgr.transitionAccountForDeadBall();
        }
        if(event instanceof BallsDepletedEvent)
        {
            BallsDepletedEvent ballsDepleted = (BallsDepletedEvent)event;
            running = false;
            _stateMgr.transitionGameOver();

        }
        if(event instanceof BricksFinishedEvent)
        {
            BricksFinishedEvent bricksFinished = (BricksFinishedEvent)event;
            running = false;
            _stateMgr.transitionGameOver();

        }
        if(event instanceof TimeDepletedEvent)
        {
            TimeDepletedEvent timeDepleted = (TimeDepletedEvent)event;
            running = false;
            _stateMgr.transitionGameOver();

        }
    }
}
