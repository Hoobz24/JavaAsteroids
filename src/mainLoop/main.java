package mainLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objects.*;

public class main extends JPanel implements KeyListener {

    public JFrame frame;
    final int HEIGHT = 720;
    final int WIDTH = 1080;

    boolean wd;
    boolean ad;
    boolean sd;
    boolean dd;



    public ship shipPlayer;



    main(){

        frame = new JFrame();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Asteroids");
        frame.add(this);

        shipPlayer = new ship(WIDTH /2, HEIGHT /2 , 10, 100, WIDTH, HEIGHT);





        while(true){

            try{
                Thread.sleep(10);

            } catch(Exception e){


            }

            repaint();
            update();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {



    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_W){
            wd = true;


        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            sd = true;


        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            ad = true;


        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            dd = true;


        }

        if(e.getKeyCode() == KeyEvent.VK_F){
            shipPlayer.shoot();
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_W){
            wd = false;


        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            sd = false;


        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            ad = false;


        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            dd = false;


        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        shipPlayer.paint(g);


    }

    public void update(){

        if(ad == true){
            shipPlayer.xv -= 1;

        }
        if(sd == true){
            shipPlayer.yv += 1;

        }
        if(dd == true){
            shipPlayer.xv += 1;

        }
        if(wd == true){
            shipPlayer.yv -= 1;

        }

        /*

        THIS DOES NOT WORK RN

        for (int i = 0; i < shipPlayer.bullets.size(); i++) {
            if(shipPlayer.bullets.get(i).getX() > WIDTH){
                shipPlayer.bullets.remove(i);
            }

            if(shipPlayer.bullets.get(i).getX() < 0){
                shipPlayer.bullets.remove(i);
            }

            if(shipPlayer.bullets.get(i).getY() > HEIGHT){
                shipPlayer.bullets.remove(i);
            }

            if(shipPlayer.bullets.get(i).getY() < 0){
                shipPlayer.bullets.remove(i);
            }
        }

         */

        shipPlayer.update(frame);


    }

    public static void main(String args[]){

        new main();
    }
}
