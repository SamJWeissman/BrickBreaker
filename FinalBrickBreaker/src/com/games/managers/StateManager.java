package com.games.managers;

/**
 * Created by SJWeissman on 2/13/14.
 */
public class StateManager extends Manager {

    private ScreenManager _screenManager;
    private EntityManager _entityManager;

    private gameState _gameState;

    public StateManager(ScreenManager screenManager, EntityManager entityManager)
    {
        _screenManager = screenManager;
        _entityManager = entityManager;       
    }

    public void setEntityMgr(EntityManager em){
        _entityManager = em;
    }

    public gameState getState()
    {
        return _gameState;
    }

    public void transitionTitle()
    {
        _screenManager.showTitle();
        _gameState = gameState.atTitleScreen;
    }

    public void transitionGameScreen()
    {
        _screenManager.startGame();
        _gameState = gameState.atGameScreen;
    }

    public void transitionGameOver()
    {
        _screenManager.showGameOver();
        _gameState = gameState.gameOver;
    }

    public void transitionExit()
    {
        _screenManager.exit();
        _gameState = gameState.exiting;
    }

    public void transitionAccountForDeadBall()
    {
        _entityManager.resetBall();
        _entityManager.decrementBalls();
    }
    public void transitionStartBallMotion()
    {
        _entityManager.setBallSpeed(3);
    }
    public void transitionStartClock()
    {
        _entityManager.startGameTimer();
        _gameState = gameState.playing;
    }

}

