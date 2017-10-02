package com.game.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.game.demo.Coin;

import java.util.ArrayList;

public class GameScreen extends AbstractScreen {

	private final float SPAWN_TIME_MAX_DELAY = 1;
	private final float SPAWN_TIME_MIN_DELAY = 0.5f;

	private BitmapFont font;
	private SpriteBatch batch;
	private ArrayList<Coin> coinList;
	private float spawnTimer = 0;
	private float spawnTime = getRandomSpawnTime();
	private int killCount = 0;
	private OrthographicCamera camera;

	public GameScreen() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		camera.update();

		batch = new SpriteBatch();
		coinList = new ArrayList<Coin>();

		font = new BitmapFont();
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		font.getData().setScale(1.5f);

		Gdx.input.setInputProcessor(new GameInput(this));
	}

	private void createCoin() {
		Coin coin = new Coin();
		coin.position.x = MathUtils.random(0, Gdx.graphics.getWidth() - coin.dimension.x);
		coin.position.y = MathUtils.random(0, Gdx.graphics.getHeight() - coin.dimension.y);
		coinList.add(coin);
	}

	@Override
	public void show() {
		createCoin();
		createCoin();
	}

	@Override
	public void render(float deltaTime) {
		batch.setProjectionMatrix(camera.combined);

		spawnTimer += deltaTime;
		if (spawnTime < spawnTimer) {
			spawnTimer = 0;
			spawnTime = getRandomSpawnTime();
			createCoin();
		}

		if (coinList.size() > 20) {
			restartGame();
		}

		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		renderCoins(deltaTime);
		renderLabels();
		batch.end();
	}

	@Override
	public void dispose() {

	}

	public ArrayList<Coin> getCoins() {
		return coinList;
	}

	private void renderLabels() {
		font.draw(batch, "Kills: " + killCount, 25, 35);

		if (coinList.size() >= 10) {
			font.setColor(com.badlogic.gdx.graphics.Color.RED);
		}
		font.draw(batch, "Active: " + coinList.size(), Gdx.graphics.getWidth() - 150, 35);

		font.setColor(com.badlogic.gdx.graphics.Color.WHITE);
	}

	private void restartGame() {
		coinList.clear();
		killCount = 0;
		createCoin();
		createCoin();
	}

	private void renderCoins(float deltaTime) {
		for (Coin coin : coinList) {
			coin.update(deltaTime);
			coin.render(batch);
		}
	}

	private float getRandomSpawnTime() {
		return MathUtils.random(SPAWN_TIME_MIN_DELAY, SPAWN_TIME_MAX_DELAY);
	}

	public void removeCoin(Coin removeCoin) {
		coinList.remove(removeCoin);
		killCount++;
	}
}