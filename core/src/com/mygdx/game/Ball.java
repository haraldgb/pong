package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Ball implements Drawable {
    public static final Vector2 START_VELOCITY = new Vector2(3, 3);
    public static final float VELOCITY_MULTIPLIER = 1.5f;
    private SpriteBatch batch;
    private ExtendViewport viewport;
    private Vector2 velocity;
    private int frequency = 60;
    Sprite sprite;
    Paddle leftPaddle;
    Paddle rightPaddle;
    Score scoreKeeper;
    float stateTime = 0;
    private boolean velChanged = true;
    float scale;

    public Ball(SpriteBatch batch, ExtendViewport viewport, Sprite sprite, float px, float py, Paddle leftPaddle, Paddle rightPaddle) {
        this.batch = batch;
        this.viewport = viewport;
        scale = Gdx.graphics.getHeight() / Pong.DESKTOP_START_HEIGHT;
        this.sprite = sprite;
        sprite.setPosition(px, py);
        sprite.setScale(scale);
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        velocity = new Vector2(START_VELOCITY);
        velocity.scl(frequency * scale);
        stateTime = 0;
    }

    @Override
    public void draw() {
        sprite.draw(batch);
    }

    @Override
    public void update(float dt) {
        stateTime += dt;
        if (stateTime > 15) {
            velocity.scl(1.5f);
            stateTime = 0;
        }
        stepOnce(dt);
        bounceOffWalls(dt);
        bounceOffPaddles(dt);
        handleGoal();
    }

    @Override
    public void reverseDirection(boolean x, boolean y) {
        if (x) {
            velocity.set(-velocity.x, velocity.y);
        }
        if (y) {
            velocity.set(velocity.x, -velocity.y);
        }
    }

    public void increaseSpeed(float scl) {
        velocity.scl(scl);
    }

    private void bounceOffWalls(float dt) {
        float x = sprite.getX();
        float y = sprite.getY();
        if (y <= 0 ) {
            this.reverseDirection(false, true);
        } else if (y + getHeight() >= viewport.getWorldHeight()) {
            this.reverseDirection(false, true);
        }
    }

    private void bounceOffPaddles(float dt) {
        // ball left off right bound of left paddle
        float x = sprite.getX();
        if (x <= leftPaddle.getWidth()) {
            float centerHeight = sprite.getY() + getHeight()/2;
            if (centerHeight >= leftPaddle.getY() && centerHeight <= leftPaddle.getTop()) {
                if ( x < leftPaddle.getWidth() + velocity.x) {
                    leftPaddle.stopPaddle();
                    reverseDirection(false, true);
                } else {
                    reverseDirection(true, false);
                }
                stepOnce(dt);
            }
        } else if (x + getWidth() >= viewport.getWorldWidth() - rightPaddle.getWidth()) {
            float centerHeight = sprite.getY() + getHeight()/2;
            if (centerHeight >= rightPaddle.getY() && centerHeight <= rightPaddle.getTop()) {
                if (x > viewport.getWorldWidth() - rightPaddle.getWidth() + velocity.x) {
                    rightPaddle.stopPaddle();
                    reverseDirection(false, true);
                } else {
                    reverseDirection(true, false);
                }
                stepOnce(dt);
            }
        }
    }

    private void handleGoal() {
        if (sprite.getX() + getWidth() < 0) {
            scoreKeeper.p2Scored();
            setVelocityToStart();
            setPositionToStart();
            velChanged = !velChanged;
            reverseDirection(false, velChanged);
            stateTime = 0;
        } else if (sprite.getX() > viewport.getWorldWidth()) {
            scoreKeeper.p1Scored();
            setVelocityToStart();
            setPositionToStart();
            velChanged = !velChanged;
            reverseDirection(true, velChanged);
            stateTime = 0;
        }
    }

    private void setVelocityToStart() {
        velocity = new Vector2(START_VELOCITY);
        velocity.scl(frequency * scale);
    }

    private void setPositionToStart() {
        sprite.setPosition((viewport.getWorldWidth() - getWidth()) / 2, (viewport.getWorldHeight() - getHeight()) / 2);
    }

    public void stepOnce(float dt) {
        velocity.scl(dt);
        sprite.setPosition(sprite.getX() + velocity.x, sprite.getY() + velocity.y);
        velocity.scl(1/dt);
    }

    public void setScoreKeeper(Score score) {
        this.scoreKeeper = score;
    }

    public float getWidth() {
        return sprite.getWidth() * scale;
    }

    public float getHeight() {
        return sprite.getHeight() * scale;
    }
}
