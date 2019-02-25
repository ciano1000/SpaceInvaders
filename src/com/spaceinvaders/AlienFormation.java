package com.spaceinvaders;

import java.awt.*;
import java.util.ArrayList;

public class AlienFormation {
    private ArrayList<Alien> aliens;

    private static final int NUMALIENS = Invaders.NUMALIENS;
    private int dir = 1;

    private double startX;
    private double startY;

    private boolean reverseDir = false;

    public AlienFormation(Image img1,Image img2, int windowWidth)
    {
        startX = (windowWidth/2)*Math.random();
        startY = 50;

        aliens = new ArrayList<>();

        for(int i = 0;i<NUMALIENS;i++)
        {
            Alien alien = new Alien(img1,img2,windowWidth);
            if(i<5)
                alien.setPosition((startX+(75*(i+1))),startY);
            if(i>4)
                alien.setPosition((startX+(75*(i-4))),startY+75);
            if(i>9)
                alien.setPosition((startX+(75*(i-9))),startY+150);
            if(i>14)
                alien.setPosition((startX+(75*(i-14))),startY+225);
            if(i>19)
                alien.setPosition((startX+(75*(i-19))),startY+300);
            if(i>24)
                alien.setPosition((startX+(75*(i-24))),startY+375);

            alien.setxSpeed(1.0);
            aliens.add(alien);
        }
    }

    public void move()
    {
        if(reverseDir)
        {
            dir = dir*(-1);
            for(int i=0;i<NUMALIENS;i++)
            {
                Alien alien = aliens.get(i);
                double x = alien.getX();
                double y = alien.getY();
                alien.setPosition(x,y+50);
                alien.move(dir);
            }
            reverseDir = false;
        }
        else{
            for(int i=0;i<NUMALIENS;i++)
            {
                Alien alien = aliens.get(i);
                double x = alien.getX();
                double y = alien.getY();
                alien.move(dir);
                if(alien.isOutOfBounds())
                    reverseDir = true;
            }
        }
    }

    public ArrayList<Alien> getAliens()
    {
        return aliens;
    }

}
