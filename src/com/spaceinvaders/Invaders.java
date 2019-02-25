package com.spaceinvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

public class Invaders extends JFrame implements Runnable, KeyListener {

    public static final int NUMALIENS = 30;

    private boolean isInitialised = false;
    private boolean isGameRunning = true;
    private int score = 0;
    private int bestScore = 0;

    private AlienFormation formation;
    private ArrayList<Alien> aliens;
    private ArrayList<Bullet> bullets;
    private Spaceship player;

    private ImageIcon bulletIcon = new ImageIcon(workingDir+"\\bullet.png");
    private Image bulletImage = bulletIcon.getImage();

    private static final Dimension WindowSize = new Dimension(800,800);
    private static String workingDir = "C:\\Users\\ciano\\IdeaProjects\\SpaceInvaders\\src\\com\\spaceinvaders\\ct255-images";
    private BufferStrategy strategy;

    public Invaders()
    {
        //set up window
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width/2-WindowSize.width/2;
        int y = screensize.height/2-WindowSize.height/2;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(x,y,WindowSize.width,WindowSize.height);
        setVisible(true);

        //Images for game objects
        ImageIcon alienIcon1 = new ImageIcon(workingDir+"\\alien_ship_1.png");
        ImageIcon alienIcon2 = new ImageIcon(workingDir+"\\alien_ship_2.png");
        ImageIcon playerIcon = new ImageIcon(workingDir+"\\player_ship.png");
        Image alienImage1 = alienIcon1.getImage();
        Image alienImage2 = alienIcon2.getImage();
        Image playerImage = playerIcon.getImage();

        //set up aliens
        formation = new AlienFormation(alienImage1,alienImage2,WindowSize.width);
        aliens = formation.getAliens();

        bullets = new ArrayList<>();

        //set up player
        player = new Spaceship(playerImage,WindowSize.width);
        player.setPosition(363,750);
        //key listener
        addKeyListener(this);

        //double buffering
        createBufferStrategy(2);
        strategy = getBufferStrategy();

        //everything is now initialised
        isInitialised = true;

        //Multithreading
        Thread t = new Thread(this);
        t.start();
    }

    public void run()
    {
        while(true)
        {
            try{
                Thread.sleep(20);
            }catch (InterruptedException e){}
            formation.move();
            bulletCollision();

            this.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch(keyCode)
        {
            case KeyEvent.VK_RIGHT:
                player.move(1);
                break;
            case KeyEvent.VK_LEFT:
                player.move(-1);
                break;
            case KeyEvent.VK_SPACE:
                shoot();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void shoot()
    {
        if(bullets.size()<7) {
            Bullet bullet = new Bullet(bulletImage, WindowSize);
            bullet.setPosition(player.getX()+24, player.getY());
            bullets.add(bullet);
        }
    }

    public void bulletCollision()
    {
        Iterator iterator = bullets.iterator();
        Iterator alienIterator = aliens.iterator();
        while(iterator.hasNext())
        {
           Bullet b = (Bullet)iterator.next();
           b.move(1);
           if(b.getY()<(0-16))
           {
               iterator.remove();
           }
           else{
               double x2 = b.getX(), y2 = b.getY();
               double w1 = 50, h1 = 32;
               double w2 = 6, h2 = 16;
               for(int i=0;i<NUMALIENS;i++)
               {
                   Alien alien = aliens.get(i);
                   if(alien.isAlive)
                   {
                       double x1 = alien.getX();
                       double y1 = alien.getY();

                       if(((x1<x2 && x1+w1>x2)||(x2<x1&&x2+w2>x1)) &&((y1<y2 && y1+h1>y2)||(y2<y1&&y2+h2>y1)))
                       {
                           alien.isAlive = false;
                           score+=50;
                           iterator.remove();
                           break;
                       }
                   }
               }
           }
        }
    }

    public void paint(Graphics g)
    {
        if(!isInitialised)
            return;

        g = strategy.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WindowSize.width,WindowSize.height);

        for (Alien alien:
                aliens) {
            if(alien.isAlive)
                alien.paint(g);
        }

        if(bullets.size()>0)
        {
            for(int i=0;i<bullets.size();i++)
            {
                bullets.get(i).paint(g);
            }
        }

        //string drawing
        g.setColor(Color.GREEN);
        Font courier = new Font ("Courier", Font.BOLD, 24);
        g.setFont(courier);
        g.drawString("Score: "+score, 263, 75);
        g.drawString("High Score: "+bestScore,463,75);
        player.paint(g);
        strategy.show();
    }
    public static void main(String[] args)
    {
        Invaders invaders = new Invaders();
    }
}
