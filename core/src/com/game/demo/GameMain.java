package com.game.demo;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.game.demo.screens.AbstractGame;
import com.game.demo.screens.GameScreen;

/**
 * Created by Tomaž Ravljen, Drugi Vid d.o.o.
 */

public class GameMain extends AbstractGame {

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        setScreen(new GameScreen());
    }
}
