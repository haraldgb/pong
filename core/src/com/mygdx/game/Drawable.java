package com.mygdx.game;

// Implemented as an AbstractFactory
public interface Drawable {
    public void draw();
    public void update(float dt);
    public void reverseDirection(boolean x, boolean y);
    public void stepOnce(float dt);
    public float getX();
    public float getY();
    public float getWidth();
    public float getHeight();

}
