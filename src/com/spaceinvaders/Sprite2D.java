package com.spaceinvaders;

import java.awt.*;

public abstract class Sprite2D {

    protected double x,y;
    protected double xSpeed;

    protected Image image1;
    protected  Image image2;

    private int framesDrawn = 0;

    public Sprite2D(Image i, int windowWidth)
    {
        this.image1 = i;
    }

    public Sprite2D(Image i1,Image i2)
    {
        this.image1 = i1;
        this.image2 = i2;
    }

    public abstract void move(int dir);

    public void paint(Graphics g)
    {
        if(image2 == null)
        {
            g.drawImage(image1,(int)x,(int)y,null);
        }
        else{
            framesDrawn++;
            if(framesDrawn%100<50)
                g.drawImage(image1,(int)x,(int)y,null);
            else
                g.drawImage(image2,(int)x,(int)y,null);
        }
    }

    public void setxSpeed(double xSpeed)
    {
        this.xSpeed = xSpeed;
    }

    public void setPosition(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return  x;
    }

    public double getY()
    {
        return y;
    }
}
