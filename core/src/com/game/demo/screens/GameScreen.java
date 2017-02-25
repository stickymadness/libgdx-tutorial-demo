package com.game.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.game.demo.Coin;

import java.util.ArrayList;

public class GameScreen extends AbstractScreen {

	private final float SPAWN_TIME_MAX_DELAY = 1;
	private final float SPAWN_TIME_MIN_DELAY = 0.5f;

	private SpriteBatch batch;
	private ArrayList<Coin> coinList;
	private float spawnTimer = 0;
	private float spawnTime = getRandomSpawnTime();

	public GameScreen() {
		batch = new SpriteBatch();
		coinList = new ArrayList<Coin>();

		Gdx.input.setInputProcessor(new GameInput(this));
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
		createCoin();
		createCoin();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render(float deltaTime) {
		spawnTimer += deltaTime;
		if (spawnTime < spawnTimer) {
			spawnTimer = 0;
			spawnTime = getRandomSpawnTime();
			createCoin();
		}

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


	ArrayList<Coin> getCoins() {
		return coinList;
	}

	private float getRandomSpawnTime() {
		return MathUtils.random(SPAWN_TIME_MIN_DELAY, SPAWN_TIME_MAX_DELAY);
	}
}