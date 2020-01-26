import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Ezreal {
    private int x, y; // position of a frog
    private double vx, vy; // velocity
    private int width, height;
    private Image img;
    private ArrayList < Bullet > bullets = new ArrayList < Bullet > ();
    private double ezQX = 1;
    private double ezQY = 1;

    // write the constructor for the frog which
    // takes in a String fileName that will be used for the image setup.

    public Ezreal(String fileName) {
        x = 450;
        y = 450;
        width = 145;
        height = 170;

        // helper functions - okay to be blackboxed and not explain

        img = getImage("ezreal.png");
        init(x, y);
    }

    public void setX(int newX) { // set x position
        x = newX;
    }

    public void setY(int newY) { // set y position
        y = newY;
    }

    public void setVx(double newVx) { // set new x velocity
        vx = newVx;
    }

    public void setVy(double newVy) { // set new y velocity
        vy = newVy;
    }

    public void move() { // move function to be called in driver

        x += vx;
        y += vy;
        init(x, y);

    }

    public void fire() {

        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();

        int targetX = (int) b.getX();
        int targetY = (int) b.getY();
        double vx = (double) targetX - (x + (double) 145 / 2);
        double vy = (double) targetY - (y + (double) 170 / 2);

        double distance = Math.sqrt((vx * vx) + (vy * vy));

        double dirX = vx / distance;
        double dirY = vy / distance;

        Bullet b1 = new Bullet("bullet.png", x, getX() + (double) 145 / 2, y, getY() + (double) 145 / 2);
        bullets.add(b1);
        b1.setxVelocity(dirX * 15);
        b1.setyVelocity(dirY * 15);

    }

    public ArrayList getBullets() {
        return bullets;
    }

    public boolean collided(int ox, int oy, int ow, int oh) {
        Rectangle obs = new Rectangle(ox, oy, ow, oh);
        Rectangle ezreal = new Rectangle(x, y, width, height);
        return obs.intersects(ezreal);
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
        init(x, y);
    }

    private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

    // draw the affinetransform
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, tx, null);
    }

    private void init(double a, double b) {
        tx.setToTranslation(a, b);
    }

    // converts image to make it drawable in paint
    private Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = Ezreal.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }

    public int getY() { // get y position
        // TODO Auto-generated method stub
        return y;
    }

    public int getX() { // get x position
        // TODO Auto-generated method stub
        return x;
    }

    public double getVy() { // get y position
        // TODO Auto-generated method stub
        return vy;
    }

    public double getVx() { // get x position
        // TODO Auto-generated method stub
        return vx;
    }

    public double getEzQX() {
        return ezQX;
    }

    public void setEzQX(double ezQX) {
        this.ezQX = ezQX;
    }

    public double getEzQY() {
        return ezQY;
    }

    public void setEzQY(double ezQY) {
        this.ezQY = ezQY;
    }

}