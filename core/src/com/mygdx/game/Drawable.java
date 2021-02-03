package com.mygdx.game;

public interface Drawable {
    // Coulda made this an abstract class instead.
    public void draw();
    public void update(float dt);
    public void reverseDirection(boolean x, boolean y);
    public void stepOnce(float dt);
}
