package objects;

import java.awt.*;
import java.util.ArrayList;

import mainLoop.main;

import javax.swing.*;

public class ship {

    int x;
    int y;
    public float xv;
    public float yv;
    int size;
    int health;
    float speed;
    int winWIDTH;
    int winHEIGHT;
    int offY;
    int offX;
    int waitTime = 10;
    public int kills;
    Rectangle shipRec;

    public ArrayList<bullet> bullets;
    public ArrayList<asteroid> asteroids;


    public ship(int x, int y, int size, int health, int winWIDTH, int winHEIGHT){
       this.x = x;
       this.y = y;
       this.size = size;
       this.health = health;
       this.xv = 0;
       this.yv = 0;
       this.speed = .1f;
       this.winHEIGHT = winHEIGHT;
       this.winWIDTH = winWIDTH;
       this.kills = 0;

       bullets = new ArrayList<>();
       asteroids = new ArrayList<>();
       shipRec = new Rectangle();
       shipRec.x = this.x;
       shipRec.y = this.y;
       shipRec.width = this.size;
       shipRec.height = this.size;
       asteroids.add(new asteroid(0, (int)(Math.random() * 500), 2));
       asteroids.add(new asteroid(500, (int)(Math.random() * 500), 2));
       asteroids.add(new asteroid((int)(Math.random() * 500), 0, 2));
       asteroids.add(new asteroid((int)(Math.random() * 500), 500, 2));
       asteroids.add(new asteroid(0, (int)(Math.random() * 500), 2));
       asteroids.add(new asteroid(500, (int)(Math.random() * 500), 2));
       asteroids.add(new asteroid((int)(Math.random() * 500), 0, 2));
       asteroids.add(new asteroid((int)(Math.random() * 500), 500, 2));

    }


    public void shoot(){

        PointerInfo info = MouseInfo.getPointerInfo();
        Point a = info.getLocation();
        a.x -= offX;
        a.y -= offY;

        bullets.add(new bullet(x, y, new Point(this.x, this.y), a, 5, winWIDTH, winHEIGHT));
    }

    public void update(JFrame frame){
        winWIDTH = frame.getWidth();
        winHEIGHT = frame.getHeight();

        offX = frame.getLocation().x;
        offY = frame.getLocation().y;

        System.out.println(offX);

        shipRec.x = this.x;
        shipRec.y = this.y;

       if(asteroids.size() < 8){
           int rand = (int)(Math.random() * 3);
           if(rand == 1){
               asteroids.add(new asteroid(0, (int)(Math.random() * frame.getHeight()), 2));
           } else {
               asteroids.add(new asteroid((int)(Math.random() * frame.getHeight()), 0, 2));
           }
       }

        waitTime += 1;


        x += xv * speed;
        y += yv * speed;

        if(x < 0){
            x = winWIDTH;
        }
        if(x > winWIDTH){
            x = 0;
        }
        if(y > winHEIGHT){
            y = 0;
        }
        if( y < 0){
            y = winHEIGHT;
        }


        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).update(frame);
            if(isOverlapping(asteroids.get(i).rec, shipRec)){
                System.out.println("DEAD");
                this.x = frame.getWidth() / 2;
                this.y = frame.getHeight() / 2;

                for (int j = 0; j < asteroids.size(); j++) {
                    asteroids.remove(j);
                }

                for (int j = 0; j < bullets.size(); j++) {
                    bullets.remove(j);
                }

                asteroids.add(new asteroid(0, (int)(Math.random() * 500), 2));
                asteroids.add(new asteroid(500, (int)(Math.random() * 500), 2));
                asteroids.add(new asteroid((int)(Math.random() * 500), 0, 2));
                asteroids.add(new asteroid((int)(Math.random() * 500), 500, 2));
                asteroids.add(new asteroid(0, (int)(Math.random() * 500), 2));
                asteroids.add(new asteroid(500, (int)(Math.random() * 500), 2));
                asteroids.add(new asteroid((int)(Math.random() * 500), 0, 2));
                asteroids.add(new asteroid((int)(Math.random() * 500), 500, 2));

                kills = 0;
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();

            if(waitTime > 10) {
                for (int i2 = 0; i2 < asteroids.size(); i2++) {
                    if (isOverlapping(bullets.get(i).rec, asteroids.get(i2).rec)) {
                        if (asteroids.get(i2).vari == 2) {
                            asteroids.add(new asteroid(asteroids.get(i2).x, asteroids.get(i2).y, 1));
                            asteroids.add(new asteroid(asteroids.get(i2).x, asteroids.get(i2).y, 1));
                        }

                        asteroids.remove(i2);

                        kills++;

                        waitTime = 0;

                    }
                }
            }
        }

    }

    public boolean isOverlapping(Rectangle a, Rectangle b) {

        return (Math.abs(a.x - b.x) * 2 < (a.width + b.width)) &&
                (Math.abs(a.y - b.y) * 2 < (a.height + b.height));

    }

    public void paint(Graphics g){

        g.setColor(Color.white);
        g.fillOval(x, y, size, size);
        String score = "Score: " + kills * 1000;
        g.drawString(score, 20, 20);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).draw(g);
        }
    }


}
