import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import com.sun.javafx.scene.traversal.Direction;

public class Teemo {
	private int x, y; // position of a frog
	private double vx =1;
	private double vy=1;; // velocity
	private boolean alive; // alive or not
	private int width, height;
	private Image img; // frog image
	private Direction direction;
	private ArrayList<Teemo> teemos = new ArrayList<Teemo>();

	public Teemo(String filename, int x, double finalX, int y, double finalY) {
		width = 105;
		height = 105;
		this.x = x;
		this.y = y;
		img = getImage("teemo.png");
		init(x, y);
	}

//	public void move() { // move function to be called in driver
//		x += vx;
//		y += vy;
//		init(x, y);
//	}

	public ArrayList getTeemos() {
		return teemos;
	}

	public boolean collided(int ox, int oy, int ow, int oh) {
		Rectangle obs = new Rectangle(ox, oy, ow, oh);
		Rectangle teemo = new Rectangle(x, y, width, height);
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
		init(x,y);

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
