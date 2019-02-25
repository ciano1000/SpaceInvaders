package com.spaceinvaders;

import javax.swing.*;
import java.awt.*;

public class Alien extends Sprite2D{

    private boolean outOfBounds = false;

    public boolean isAlive = true;

    private int windowWidth;

    public Alien(Image i1, Image i2, int windowWidth)
    {
        super(i1,i2);
        this.windowWidth = windowWidth;
    }
    @Override
    public void move(int dir) {
        outOfBounds = false;

        if((int)x>0 && (int)x<(windowWidth-50))
        {
            x+=(xSpeed*dir);
        }
        else if(isAlive){
            double dist2Right = windowWidth-x;
            double dist2Left = x;

            if(dist2Left>dist2Right)
            {
                if(dir == -1)
                {
                    outOfBounds = false;
                    x+=(xSpeed*dir);
                }
                else{
                    outOfBounds = true;
                }
            }
            else {
                if(dir == 1)
                {
                    outOfBounds = false;
                    x+=(xSpeed*dir);
                }
                else {
                    outOfBounds = true;
                }
            }
        }
    }

    public boolean isOutOfBounds() {
        return  outOfBounds;
    }
}
