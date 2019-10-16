package objects;

import java.awt.*;
import java.util.ArrayList;

import mainLoop.*;

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

    public ArrayList<bullet> bullets;


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

       bullets = new ArrayList<>();
    }


    public void shoot(){

        PointerInfo info = MouseInfo.getPointerInfo();
        Point a = info.getLocation();
        bullets.add(new bullet(x, y, new Point(this.x, this.y), a, 5, winWIDTH, winHEIGHT));
    }

    public void update(){
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

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            System.out.println(bullets.get(i).x);
        }

    }

    public void paint(Graphics g){

        g.setColor(Color.white);
        g.fillOval(x, y, size, size);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
    }


}
