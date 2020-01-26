import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Teemo {
    private double x, y;

    private double vx = 0;
    private double vy = 0; // velocity
    private int width = 105;
    private int height = 105;
    private Image img; // image
    private ArrayList < Teemo > teemos = new ArrayList <Teemo> ();

    public Teemo(String filename, double x, double finalX, double y, double finalY) {
        width = 105;
        height = 105;
        if (x < 0.25) {
            this.x = 0;
            this.y = Math.random() * 1600;
        }
        if (x < 0.5 && x > 0.25) {
            this.x = 1600;
            this.y = Math.random() * 1600;
        }
        if (x > 0.5 && x < 0.75) {
            this.x = Math.random() * 1600;
            this.y = 0;
        }
        if (x > 0.75) {
            this.x = Math.random() * 1600;
            this.y = 1600;
        }

        img = getImage("teemo.png");

    }

    //	public void move() { // move function to be called in driver
    //		x += vx;
    //		y += vy;
    //		init(x, y);
    //	}

    public ArrayList getTeemos() {
        return teemos;
    }

    public boolean collided(double ox, double oy, int ow, int oh) {
        Rectangle obs = new Rectangle((int) ox, (int) oy, ow, oh);
        Rectangle teemo = new Rectangle((int) x, (int) y, width, height);
        return obs.intersects(teemo);
    }

    //	public void spawn() {
    //		Random rand = new Random();
    //		if (rand.nextInt(100) == 1) {
    //
    //			int x = Teemo
    //			int y = Teemo
    //
    //		}
    //	}

    private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, tx, null);
        x += vx;
        y += vy;
        init(x, y);

    }

    private void init(double a, double b) {
        tx.setToTranslation(a, b);
    }

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

    public double getY() { // get y position
        // TODO Auto-generated method stub
        return y;
    }

    public double getX() { // get x position
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

    public int getWidth() {
        // TODO Auto-generated method stub
        return width;
    }

    public int getHeight() {
        // TODO Auto-generated method stub
        return height;
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

}