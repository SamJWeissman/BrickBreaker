package com.games.managers;

import com.games.entities.*;
import com.games.events.BallsDepletedEvent;
import com.games.events.BricksFinishedEvent;
import com.games.events.DeadBallEvent;
import com.games.events.EventAggregator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by SJWeissman on 2/13/14.
 */
public class EntityManager extends Manager{
    private Ball ball;
    private PowerBar playerBar;
    private Brick[][] bricks;
    private HeadsUpDisplay hud;
    private GameTimer gTimer;
    private ArrayList<Entity> _entities;
    private final int WIDTH, HEIGHT;
    private int numOfBalls = 3;
    private int rows = 5, columns = 4,
            xOffSet = 8, yOffSet = 5,
            brickWidth = 50, brickHeight = 20,
            numBricks = rows * columns;

    public EntityManager(int screenWidth, int screenHeight){
        WIDTH = screenWidth;
        HEIGHT = screenHeight;
        _entities = new ArrayList<Entity>();
        _entities.add(playerBar = new PowerBar(WIDTH, HEIGHT));
        _entities.add(ball = new Ball(screenWidth/2 - 10, screenHeight/2, screenWidth, screenHeight));
        _entities.add(gTimer = new GameTimer((screenWidth + 5), 0));
        _entities.add(hud = new HeadsUpDisplay((screenWidth + 5), 100, this));
        bricks = new Brick[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                bricks[i][j] = new Brick((xOffSet + (brickWidth * i) + (xOffSet * i)), (yOffSet + (brickHeight * j) + yOffSet * j));
                _entities.add(bricks[i][j]);
            }
        }
    }
    public void setBallSpeed(int speed)
    {
        ball.setSpeed(speed);
    }

    public void resetBall()
    {
        ball.reset();
    }
    public void decrementBalls(){
        numOfBalls -= 1;
        if (numOfBalls == 0){
            EventAggregator.getEventAggregator().publish(new BallsDepletedEvent());
        }
    }
    public void startGameTimer()
    {
        gTimer.startTimer();
    }

    public void update()
    {
        if(ball.deadBall()){
            EventAggregator.getEventAggregator().publish(new DeadBallEvent());
        }
        handleCollisions();
        for(Iterator<Entity> i = _entities.iterator(); i.hasNext(); )
        {
            Entity entity = i.next();
            entity.update();
        }
    }

    public void handleCollisions(){
        for (int i = 0; i < _entities.size(); i++){
            if ((_entities.get(i) != ball) && (_entities.get(i) != null)){
                if ((_entities.get(i) instanceof Collidable) && _entities.get(i).detectCollision(ball)){
                    ball.changeVertDir();
                    if(_entities.get(i) instanceof Brick ){
                        _entities.remove(i);
                        numBricks--;
                        if (numBricks == 0)
                        {
                            EventAggregator.getEventAggregator().publish(new BricksFinishedEvent());
                        }
                    }
                }

            }
        }
    }

    public void paintEntities(Graphics g)
    {
        for(Iterator<Entity> i = _entities.iterator(); i.hasNext(); )
        {
            Entity entity = i.next();
            entity.paint(g);
        }
    }
    public int getNumBricks() { return numBricks; }
    public int getNumOfBalls() { return numOfBalls; }
    public GameTimer getTimer() { return gTimer; }
    public Ball getBall(){ return ball; }
    public PowerBar getPlayerBar(){ return playerBar; }

    public Brick[][] getBricks(){ return bricks; }
    public ArrayList<Entity> getEntities(){ return _entities; }
    public int ballX(){ return ball.getX(); }
    public int ballY(){ return ball.getY(); }
    public int playerBarX(){ return playerBar.getX(); }
    public int playerBarY(){ return playerBar.getY(); }
}
