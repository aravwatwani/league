import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Bullet {
    private int x, y; // position of a frog
    private double xVelocity, yVelocity; // velocity
    private int width, height;
    private Image img; // frog image
    private int initialX;
    private double finalX;
    private int initialY;
    private double finalY;

    private boolean visible;

    public Bullet(String filename, int initialX, double finalX, int initialY, double finalY) {
        width = 60;
        height = 60;
        this.initialX = initialX;
        this.initialY = initialY;
        img = getImage("bullet.png");

        init(initialX, initialY);
    }

    public void setX(int newX) { // set x position
        x = newX;
    }

    public void setY(int newY) { // set y position
        y = newY;
    }

    public void setPos(int x, int y) { // used to reset position when frog
        // collides with objects
        this.x = x;
        this.y = y;
        init(x, y);
    }

    private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

    // draw the affinetransform
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, tx, null);
        initialX += xVelocity;
        initialY += yVelocity;
        init(initialX, initialY);
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getInitialX() {
        return initialX;
    }

    public void setInitialX(int initialX) {
        this.initialX = initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    public void setInitialY(int initialY) {
        this.initialY = initialY;
    }

    private void init(double a, double b) {
        tx.setToTranslation(a, b);
    }

    public boolean collided(int ox, int oy, int ow, int oh) {
        Rectangle obs = new Rectangle(ox, oy, ow, oh);
        Rectangle bullet = new Rectangle(x, y, width, height);
        return obs.intersects(bullet);
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

    public boolean isVisible() {
        return visible;
    }

    public double getFinalX() {
        return finalX;
    }

    public void setFinalX(double finalX) {
        this.finalX = finalX;
    }

    public double getFinalY() {
        return finalY;
    }

    public void setFinalY(double finalY) {
        this.finalY = finalY;
    }

}