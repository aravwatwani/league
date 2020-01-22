import java.awt.Color;
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

	public Bullet(String filename, int initialX, double finalX, int initialY,
			double finalY) {
		width = 100;
		height = 100;
		this.initialX = initialX;
		this.initialY = initialY;
		img = getImage("bullet.png");
		init(initialX, initialY);
	}

	public void shootBullet() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		finalX = (int) b.getX();
		finalY = (int) b.getY();

	//	double bulletVelocity = 100; // however fast you want your bullet to
										// travel
										// double angle = Math.atan2(finalX -
										// initialX, finalY - initialY);
		// xVelocity = (bulletVelocity) * Math.cos(angle);
		// yVelocity = (bulletVelocity) * Math.sin(angle);

		double radians = Math.toRadians(Math.atan2(finalY - initialY, finalX
				- initialX));

		if (radians < 0) {
			radians += 2 * Math.PI;
		}

		if (finalX < initialX && finalY < initialY) {

			while (initialX > 0 || initialY > 0) {

				initialX -= Math.cos(radians);
				initialY -= Math.sin(radians);

			}
		}

		if (finalX > initialX && finalY < initialY) {

			while (initialX < 1600 || initialY > 0) {

				initialX += Math.cos(radians);
				initialY -= Math.sin(radians);

			}
		}

		if (finalX < initialX && finalY > initialY) {

			while (initialX > 0 || initialY < 1600) {

				initialX -= Math.cos(radians);
				initialY += Math.sin(radians);

			}
		}
	}

	public void update() {
		initialX += 1;
		initialY += 1;
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

	public boolean isVisible() {
		return visible;
	}

}
