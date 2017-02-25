package com.game.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.game.demo.Coin;

/**
 * Created by Tomaž Ravljen, Drugi Vid d.o.o.
 */

public class GameInput extends InputAdapter {

    public GameScreen gameScreen;

    public GameInput(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        screenY = Gdx.graphics.getHeight() - screenY;
        Coin removeCoin = null;
        for (Coin coin : gameScreen.getCoins()) {
            if (isClicked(coin, screenX, screenY)) {
                removeCoin = coin;
            }
            Gdx.app.debug("GameInput", "isClicked: " + isClicked(coin, screenX, screenY));
        }

        if (removeCoin != null) {
            gameScreen.removeCoin(removeCoin);
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }

    private boolean isClicked(Coin coin, float x, float y) {

        Rectangle coinCollider = coin.getCollider();
        Rectangle click = new Rectangle(x - 1, y - 1, 2, 2);

        return click.overlaps(coinCollider);
    }
}
