package com.game.demo;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.demo.screens.AbstractScreen;
import com.game.demo.screens.MenuScreen;

/**
 * Created by Tomaž Ravljen, Drugi Vid d.o.o.
 */

public class GameMain implements ApplicationListener {

    private Screen currentScreen;
    private SpriteBatch batch;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        setScreen(new MenuScreen());
    }

    private void setScreen(AbstractScreen screen) {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        if (batch == null) {
            batch = new SpriteBatch();
        }

        currentScreen = screen;
        currentScreen.show();
        currentScreen.resize(w, h);
        currentScreen.render(0);
    }

    @Override
    public void resize(int width, int height) {
        if (currentScreen != null) {
            currentScreen.resize(width, height);
        }
    }

    @Override
    public void render() {
        if (currentScreen != null) {
            float deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f);
            currentScreen.render(deltaTime);
        }
    }

    @Override
    public void pause() {
        if (currentScreen != null) {
            currentScreen.pause();
        }
    }

    @Override
    public void resume() {
        if (currentScreen != null) {
            currentScreen.resume();
        }
    }

    @Override
    public void dispose() {
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }
}
