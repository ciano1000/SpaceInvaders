package com.spaceinvaders;

import java.awt.*;

public class Spaceship extends Sprite2D{

    private int windowWidth;

    public Spaceship(Image i, int windowWidth)
    {
        super(i,windowWidth);
        this.windowWidth = windowWidth;
    }

    @Override
    public void move(int dir) {
        if(x<=20)
        {
            xSpeed = 0;
            if(dir>0)
            {
                setxSpeed(20);
                x+=dir*xSpeed;
            }
            return;
        }
        else if(x>=(windowWidth-71))
        {
            xSpeed = 0;
            if(dir<0)
            {
                setxSpeed(20);
                x+=dir*xSpeed;
            }
            return;
        }
        setxSpeed(20);
        x+=dir*xSpeed;
    }
}
