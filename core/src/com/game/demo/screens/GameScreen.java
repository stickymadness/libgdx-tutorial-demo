package com.game.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.game.demo.Coin;

import java.util.ArrayList;

public class GameScreen extends AbstractScreen {

	private SpriteBatch batch;
	private ArrayList<Coin> coinList;

	public GameScreen() {
		batch = new SpriteBatch();
		coinList = new ArrayList<Coin>();

		createCoin();
		createCoin();
	}

	private void createCoin() {
		Coin coin = new Coin();
		coin.position.x = MathUtils.random(0, Gdx.graphics.getWidth() - coin.dimension.x);
		coin.position.y = MathUtils.random(0, Gdx.graphics.getHeight() - coin.dimension.y);
		coinList.add(coin);
		Gdx.app.debug("Coin", "position: " + coin.position.toString());
	}

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		renderCoins(deltaTime);
		batch.end();
	}

	@Override
	public void dispose() {

	}

	private void renderCoins(float deltaTime) {
		for (Coin coin : coinList) {
			coin.update(deltaTime);
			coin.render(batch);
		}
	}
}
