package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Paddle implements Drawable {
    private SpriteBatch batch;
    private ExtendViewport viewport;
    private Vector2 velocity;
    private int frequency = 60;
    Sprite sprite;
    int fingerIndex;
    boolean idleAI;
    float scale;

    public Paddle(SpriteBatch batch, ExtendViewport viewport, Sprite sprite, int inputIndex, float px, float py, boolean idleAI) {
        this.batch = batch;
        this.viewport = viewport;
        scale = Gdx.graphics.getHeight() / Pong.DESKTOP_START_HEIGHT;
        System.out.println("scale: " + scale);
        System.out.println("Gdx.graphics.getHeight(): " + Gdx.graphics.getHeight());
        System.out.println("viewport.getWorldHeight(): " + viewport.getWorldHeight());
        System.out.println("Gdx.graphics.getWidth():  " + Gdx.graphics.getWidth() );
        System.out.println("viewport.getWorldWidth(): " + viewport.getWorldWidth());

        this.sprite = sprite;
        sprite.setPosition(px, py);
        sprite.setScale(scale);
//        sprite.setSize(10*scale, 80*scale);
        fingerIndex = inputIndex;
        this.idleAI = idleAI;
        velocity = new Vector2(0, 4 * frequency * scale);
    }

    @Override
    public void draw() {
        sprite.draw(batch);
    }

    @Override
    public void update(float dt) {
        stepOnce(dt);
        bounceOffWalls(dt);
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

    public void stepOnce(float dt) {
        if (Gdx.input.isTouched(fingerIndex)) {
            float inputX = viewport.getWorldHeight() - Gdx.input.getY(fingerIndex);
            if(sprite.getX() > 0) {
                if (inputX > viewport.getWorldHeight() - getHeight()) {
                    sprite.setPosition(viewport.getWorldWidth() - getWidth(), viewport.getWorldHeight() - getHeight());
                } else if (inputX < 0) {
                    sprite.setPosition(viewport.getWorldWidth() - getWidth(), 0);
                } else {
                    sprite.setPosition(viewport.getWorldWidth() - getWidth(), inputX);
                }
            } else {
                if (inputX > viewport.getWorldHeight() - getHeight()) {
                    sprite.setPosition(0, viewport.getWorldHeight() - getHeight());
                } else if (inputX < 0) {
                    sprite.setPosition(0, 0);
                } else {
                    sprite.setPosition(0, inputX);
                }
            }
        } else if (idleAI){
            velocity.scl(dt);
            if(sprite.getX() > 0) {
                sprite.setPosition(viewport.getWorldWidth() - getWidth(), sprite.getY() + velocity.y);
            } else {
                sprite.setPosition(0, sprite.getY() + velocity.y);
            }
            velocity.scl(1/dt);
        } else {
            return ;
        }
    }

    public float getWidth() {
        return sprite.getWidth() * scale;
    }

    public float getHeight() {
        return sprite.getHeight() * scale;
    }

    public float getY() {
        return sprite.getY();
    }

    public float getTop() {
        return sprite.getY() + getHeight();
    }

    public void stopPaddle() {
        velocity.set(0,0);
    }

    private void bounceOffWalls(float dt) {
        float y = sprite.getY();
        if (y <= 0 ) {
            this.reverseDirection(false, true);
            stepOnce(dt);
        } else if (y + getHeight() >= viewport.getWorldHeight()) {
            this.reverseDirection(false, true);
        }
    }
}
