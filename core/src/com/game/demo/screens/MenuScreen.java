package com.game.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.demo.GameMain;

public class MenuScreen extends AbstractScreen {

    GameMain game;
    Stage stage;

    public MenuScreen(GameMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new MenuStage(game);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(.24f, 0.22f, 0.52f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        if (stage != null) {
            stage.dispose();
        }
    }
}
