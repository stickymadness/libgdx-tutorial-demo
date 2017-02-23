package com.game.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen extends AbstractScreen {

	private SpriteBatch batch;
	private Animation animation;
	private float animationTime = 0;

	public GameScreen() {
		animation = new Animation(23 / 1000f, new TextureAtlas("Coin-fly_1.atlas").getRegions());
		batch = new SpriteBatch();
	}

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		animationTime += deltaTime;
		batch.begin();
		batch.draw((TextureRegion)animation.getKeyFrame(animationTime, true), 0, 0);
		batch.end();
	}

	@Override
	public void dispose() {

	}
}
