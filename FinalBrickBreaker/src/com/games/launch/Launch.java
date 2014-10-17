package com.games.launch;

import com.games.managers.GameManager;

/**
 * Created by SJWeissman on 2/13/14.
 */
public class Launch {
    public static void main(String[] args){
        GameManager gm = new GameManager();
        gm.getStateMgr().transitionTitle();
    }
}


