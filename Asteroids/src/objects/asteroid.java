package objects;

import javax.swing.*;
import java.awt.*;

public class asteroid {

    int x;
    int y;
    int xv;
    int yv;
    int size;
    int speed;

    Rectangle rec;
    int vari;

    public asteroid(int x, int y, int vari){
        this.x = x;
        this.y = y;
        this.vari = vari;
        if (vari == 2) {
            this.size = 50 + (int)(Math.random() * 40);
        }
        if (vari == 1) {
            this.size = 40 + (int)(Math.random() * 20);
        }

        int rand1 = (int)(Math.floor(Math.random() * 3));
        int rand2 = (int)(Math.floor(Math.random() * 3));

        speed = (int)(Math.random() * 2) + 1;

        if(rand1 == 1){
            xv = -1;
        } else{
            xv = 1;
        }

        if(rand2 == 2){
            yv = -1;
        } else{
            yv = 1;
        }

        rec = new Rectangle(this.x, this.y, this.size, this.size);


        xv = xv * speed;
        yv = yv * speed;


    }

    public void update(JFrame frame){
        x += xv;
        y += yv;

        if(x > frame.getWidth()){
            x = 0;
        }
        if(x < 0){
            x = frame.getWidth();

        }
        if(y < 0){
            y = frame.getHeight();
        }
        if(y > frame.getHeight()){
            y = 0;
        }

        rec.x = x;
        rec.y = y;

    }

    public void draw(Graphics g){

        g.setColor(Color.gray);
        g.fillOval(x, y, size, size);
    }
}
