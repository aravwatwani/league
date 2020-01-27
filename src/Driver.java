// league
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.awt.geom.AffineTransform;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    int screen_width = 1600;
    int screen_height = 1600;
    int targetX = 0;
    int targetY = 0;
    double speed = 5.0;
    double dirX = 1;
    double dirY = 1;
    public Boolean end = false;

    
    Ezreal ezreal;
    Background background;
  //  Gameover gameover;
    ArrayList < Teemo > teemos = new ArrayList < Teemo > ();
    double score = 0.0;
    double deaths = 0.0;
    double totalscore = 0.0;

    public void paint(Graphics g) {

        super.paintComponent(g);

        g.setFont(font);
        g.setColor(Color.RED);
        g.setFont(font2);
        g.setColor(Color.CYAN);
        Font myFont = new Font("Ubuntu", 1, 30);
        g.setColor(Color.white);
        g.setFont(myFont);
        background.paint(g);
        ezreal.paint(g); // paint sprite
      //  gameover.paint(g);
        g.drawString("Score: " + score, 30, 50);
        g.drawString("Deaths: " + deaths, 30, 80);
        g.drawString("Total Score: " + totalscore, 30, 110);
        g.drawString("Score per Death: " + totalscore/deaths, 30, 140);
        ArrayList bullets = ezreal.getBullets();

        Teemo t1 = new Teemo("teemo.png", Math.random(), ezreal.getX() + (double) 145 / 2,
            (int)((Math.random()) * 1600), ezreal.getY() + (double) 145 / 2);
        if (teemos.size() < 5) {
            teemos.add(t1);
        }

        for (int i = 0; i < teemos.size(); i++) {

            Teemo t = (Teemo) teemos.get(i);
            // g.drawRect((int) t.getX(), (int) t.getY(), 105, 105);
            t.paint(g);

            double distX = (double)((double) ezreal.getX() + ((double)(140 / 2))) - (t.getX() + ((double)(105 / 2)));
            t.setVx(distX / 10000);
            double distY = (double)((double) ezreal.getY() + ((double)(170 / 2))) - (t.getY() + ((double)(105 / 2)));

            t.setVy(distY / 10000);

            for (int j = 0; j < bullets.size(); j++) {
                Bullet b = (Bullet) bullets.get(j);
                if (t.collided(b.getInitialX(), b.getInitialY(), 60, 60)) {
                    teemos.remove(i);
                    bullets.remove(j);
                    score++;
                    totalscore++;
                }
            }

        }

        
        for (int k = 0; k < teemos.size(); k++) {
            Teemo t = (Teemo) teemos.get(k);
            // g.drawRect((int) t.getX(), (int) t.getY(), 105, 105);
            t.paint(g);
            double distX = (double)((double) ezreal.getX() + ((double)(140 / 2))) - (t.getX() + ((double)(105 / 2)));
            t.setVx(distX / 150);
            double distY = (double)((double) ezreal.getY() + ((double)(170 / 2))) - (t.getY() + ((double)(105 / 2)));
            t.setVy(distY / 150);
            
            if (ezreal.collided((int) t.getX(), (int) t.getY(), t.getWidth(), t.getHeight())) {
            	
            //	Background.img = getImage("gameover.png");
            	deaths++;
            	teemos.clear();
            	score = 0;
            	
            }
           
          //  if (lives < 0) {
            	
          //  	break;
          //  }

        }


        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = (Bullet) bullets.get(i);
            // g.drawRect(b.getInitialX(), b.getInitialY(), 60, 60);
            // b.setxVelocity(ezreal.getEzQX());
            // b.setyVelocity(ezreal.getEzQY());
            b.paint(g);
            if (b.getInitialX() > 1600 || b.getInitialY() > 1600 || b.getInitialX() < 0 || b.getInitialY() < 0) {
                bullets.remove(b);
            }

        }

    }

    public Boolean getEnd() {
		return end;
	}

	public void setEnd(Boolean end) {
		this.end = end;
	}

	private Image getImage(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	// g.drawRect(ezreal.getX(), ezreal.getY(), 145, 170);
    // g.drawRect(teemo.getX(), teemo.getY(), 105, 105);

    Font font = new Font("Courier New", 1, 50);
    Font font2 = new Font("Courier New", 1, 30);

    public void update() {
        ezreal.move();
        if ((ezreal.getX() + (double) 145 / 2 > targetX - (double) 145 / 2 &&
                ezreal.getX() + (double) 145 / 2 < targetX + (double) 145 / 2) &&
            (ezreal.getY() + (double) 170 / 2 < targetY + (double) 170 / 2 &&
                ezreal.getY() + (double) 170 / 2 > targetY - (double) 170 / 2)) {
            ezreal.setVx(0);
            ezreal.setVy(0);
        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        update();
        repaint();
    }

    public static void main(String[] arg) {
        Driver d = new Driver();
    }

    public Driver() {
        JFrame f = new JFrame();
        f.setTitle("League of Legends");
        f.setSize(screen_width, screen_height);
        f.setResizable(false);
        f.addKeyListener(this);
        f.addMouseListener(this);

        // sprite instantiation
        background = new Background("summonersrift.jpg");
        ezreal = new Ezreal("ezreal.png");
     //   gameover = new Gameover("gameover.png");
        f.add(this);
        t = new Timer(17, this);
        t.start();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        repaint();

    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    Timer t;
    double lastTime = 0;

    @Override
    public void keyPressed(KeyEvent e) {
        double coolDownInMillis = 300;
       	if ((System.currentTimeMillis() > lastTime + coolDownInMillis) && e.getKeyCode() == 81) {
            ezreal.fire();
          	lastTime = System.currentTimeMillis();
		}
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (SwingUtilities.isRightMouseButton(e)) {
            // Point target = MouseInfo.getPointerInfo().getLocation();
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            targetX = (int) b.getX();
            targetY = (int) b.getY();

            double vx = (double) targetX - (ezreal.getX() + (double) 145 / 2);
            double vy = (double) targetY - (ezreal.getY() + (double) 170 / 2);

            // / System.out.println(targetY - ezreal.getY());
            // System.out.println(targetX - ezreal.getX());

            double distance = Math.sqrt((vx * vx) + (vy * vy));

            System.out.println(distance);

            double dirX = vx / distance;
            double dirY = vy / distance;
            ezreal.setVx((dirX * speed * 3));
            ezreal.setVy((dirY * speed * 3));
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub

}
    
    
    
}