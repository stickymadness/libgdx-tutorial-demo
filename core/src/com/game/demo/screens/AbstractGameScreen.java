package com.game.demo.screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

/**
 * Created by Tomaž Ravljen, Drugi Vid d.o.o.
 */

public abstract class AbstractGameScreen implements Screen {

    public abstract InputProcessor getInputProcessor();

    @Override
    public void hide() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
