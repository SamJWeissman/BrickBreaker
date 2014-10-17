package com.games.managers;

import com.games.screens.BreakOutScreen;
import com.games.screens.GameOverScreen;
import com.games.screens.Screen;
import com.games.screens.TitleScreen;

import javax.swing.*;

/**
 * Created by SJWeissman on 2/7/14.
 */
public class ScreenManager extends Manager {

    private GameOverScreen _gameOverScreen;
    private BreakOutScreen _breakoutScreen;
    private TitleScreen _titleScreen;
    private GameManager _gameMgr;
    private Screen _activeScreen;

    public ScreenManager(GameManager gm)
    {
        _gameMgr = gm;
        _titleScreen = new TitleScreen(_gameMgr);
        _breakoutScreen = new BreakOutScreen(_gameMgr);
        _gameOverScreen = new GameOverScreen("Breakout", _gameMgr);

    }

    public void showTitle()
    {
        _activeScreen = _titleScreen;
        activateScreen();
    }

    public void startGame()
    {
        deactivateScreen();
        _activeScreen = _breakoutScreen;
        activateScreen();
    }

    public void showGameOver()
    {
        deactivateScreen();
        _activeScreen = _gameOverScreen;
        activateScreen();
    }

    private void activateScreen()
    {
        _activeScreen.activate();
    }

    private void deactivateScreen()
    {
        _activeScreen.deactivate();
    }

    public void repaintScreens()
    {
        _activeScreen.repaintScreen();
    }

    public Screen getActiveScreen()
    {
        return _activeScreen;
    }

    public void exit()
    {
        System.exit(0);
    }
    public JPanel getBreakoutScreen(){
        return _breakoutScreen;
    }

}
