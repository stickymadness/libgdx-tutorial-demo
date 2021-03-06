package com.game.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tomaž Ravljen, Drugi Vid d.o.o.
 */

public class Coin {
    private static final float MAX_DURATION = 1f;

    private float animationTime;
    private Animation<TextureRegion> anim;

    private float movementDuration;
    private float maxDuration;

    public Vector2 dimension;
    public Vector2 position;
    public Vector2 velocity;

    public Rectangle bounds;

    public Coin() {
        float height = Gdx.graphics.getHeight() * 0.2f;
        float width = height * (Gdx.graphics.getWidth() / Gdx.graphics.getHeight());
        float scale = MathUtils.random(0.8f, 1.1f);

        width *= scale;
        height *= scale;
        animationTime = 0;
        movementDuration = 0;
        maxDuration = getMaxDuration();

        anim = new Animation<TextureRegion>(23 / 1000f, new TextureAtlas("Coin-fly_1.atlas").getRegions());
        velocity = new Vector2();
        velocity.x = velocity.y = Gdx.graphics.getHeight() * 0.25f;
        dimension = new Vector2(width, height);
        position = new Vector2();

        bounds = new Rectangle();
        bounds.set(position.x, position.y, dimension.x, dimension.y);
    }

    private float getMaxDuration() {
        return MathUtils.random(MAX_DURATION / 2) + MAX_DURATION / 2;
    }

    public void update(float deltaTime) {
        animationTime += deltaTime;
        movementDuration += deltaTime;

        updateVelocity();
        position.x += deltaTime * velocity.x;
        position.y += deltaTime * velocity.y;

        if (maxDuration < movementDuration) {
            movementDuration = 0;
            maxDuration = getMaxDuration();
            setRandomVelocity();
        }
    }

    private void updateVelocity() {
        if (position.x + dimension.x > Gdx.graphics.getWidth()) {
            velocity.x *= -1;
        } else if (position.x < 0) {
            velocity.x *= -1;
        }

        if (position.y + dimension.y > Gdx.graphics.getHeight()) {
            velocity.y *= -1;
        } else if (position.y < 0) {
            velocity.y *= -1;
        }
    }

    private void setRandomVelocity() {
        velocity.x *= MathUtils.randomBoolean() ? -1 : 1;
        velocity.y *= MathUtils.randomBoolean() ? -1 : 1;
    }

    public void render(SpriteBatch batch) {
        batch.draw(anim.getKeyFrame(animationTime, true),
                position.x, position.y, dimension.x, dimension.y);
    }

    public Rectangle getCollider() {
        bounds.setPosition(position.x, position.y);
        return bounds;
    }
}
