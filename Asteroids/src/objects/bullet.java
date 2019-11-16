package objects;

import java.awt.*;
import java.util.ArrayList;

public class bullet {

    double x;
    double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    double speed;
    Point cPoint;
    Point tPoint;
    int winWidth;
    int winHeight;
    Rectangle rec;


    public bullet(double x, double y, Point cPoint, Point tPoint, double speed, int winWidth, int winHeight){
        this.x = x;
        this.y = y;
        this.cPoint = cPoint;
        this.tPoint = tPoint;
        this.speed = speed;
        this.winHeight = winHeight;
        this.winWidth = winWidth;

        rec = new Rectangle((int)this.x, (int)this.y, 4, 4);

    }


    public void update(){

        double dx = cPoint.x - tPoint.x;
        double dy = cPoint.y - tPoint.y;

        double len = Math.sqrt(dx*dx+dy*dy);


        x += -(dx / len * speed);
        y += -(dy / len * speed);

        rec.x = (int)x;
        rec.y = (int)y;

    }

    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval((int)x, (int)y, 4, 4);
    }
}
