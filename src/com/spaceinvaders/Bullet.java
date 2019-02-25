package com.spaceinvaders;

import java.awt.*;

public class Bullet extends Sprite2D {

    private int windowHeight;

    public boolean isAlive = true;

    public Bullet(Image i,Dimension windowSize)
    {
        super(i, (int) windowSize.getWidth());
        windowHeight = (int) windowSize.getHeight();
    }
    @Override
    public void move(int dir) {
        y-=10;
    }
}
