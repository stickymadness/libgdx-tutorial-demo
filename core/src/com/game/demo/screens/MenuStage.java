package com.game.demo.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.game.demo.GameMain;

/**
 * Created by Tomaž Ravljen, Drugi Vid d.o.o.
 */

public class MenuStage extends Stage {

    private GameMain game;

    public MenuStage(GameMain game) {
        this.game = game;

        initPlayButton();
        initLogo();
    }

    private void initPlayButton() {
        BitmapFont font = new BitmapFont();
        font.getRegion().getTexture().
                setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(1.5f);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        TextButton button = new TextButton("PLAY", style);

        addActor(button);
        button.setPosition(
                getWidth() / 2 - button.getWidth() / 2,
                getHeight() * 0.25f);
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
        addActor(logo);

        logo.setPosition(
                getWidth() / 2 - logo.getWidth() / 2,
                getHeight() - logo.getHeight() * 1.1f);
    }
}
