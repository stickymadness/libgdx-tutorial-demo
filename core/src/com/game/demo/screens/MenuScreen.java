package com.game.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.game.demo.GameMain;

public class MenuScreen extends AbstractScreen {

    GameMain game;
    Stage stage;

    public MenuScreen(GameMain game) {
        stage = new Stage();
        this.game = game;
    }

    @Override
    public void show() {
        initLogo();
        initPlayButton();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {

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

    private void initPlayButton() {
        BitmapFont font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(1.5f);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        TextButton button = new TextButton("PLAY", style);

        stage.addActor(button);
        button.setPosition(
                stage.getWidth() / 2 - button.getWidth() / 2,
                stage.getHeight() * 0.25f);
        button.addListener(playPressed());
    }

    private ChangeListener playPressed() {
        return new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen());
            }
        };
    }

    private void initLogo() {
        Image logo = new Image(new Texture("badlogic.jpg"));
        stage.addActor(logo);

        logo.setPosition(
                stage.getWidth() / 2 - logo.getWidth() / 2,
                stage.getHeight() - logo.getHeight() * 1.1f);
    }
}
