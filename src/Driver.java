// league
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class Driver extends JPanel implements ActionListener, KeyListener,
		MouseListener, MouseMotionListener {

	int screen_width = 1600;
	int screen_height = 1600;
	int targetX = 0;
	int targetY = 0;
	double speed = 5.0;
	double dirX = 1;
	double dirY = 1;

	Ezreal ezreal;
	Background background;
	ArrayList<Teemo> teemos = new ArrayList<Teemo>();

	public void paint(Graphics g) {

		super.paintComponent(g);

		g.setFont(font);
		g.setColor(Color.RED);
		g.setFont(font2);
		g.setColor(Color.CYAN);

		// paint sprite

		background.paint(g);
		ezreal.paint(g); // paint sprite

		ArrayList bullets = ezreal.getBullets();

		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = (Bullet) bullets.get(i);
			g.drawRect(b.getInitialX(), b.getInitialY(), 60, 60);

			// b.setxVelocity(ezreal.getEzQX());
			// b.setyVelocity(ezreal.getEzQY());
			b.paint(g);
			if (b.getInitialX() > 1600 || b.getInitialY() > 1600
					|| b.getInitialX() < 0 || b.getInitialY() < 0) {
				bullets.remove(b);
			}

		}
		for (int i = 0; i < teemos.size(); i++) {
			Teemo t = (Teemo) teemos.get(i);
			g.drawRect(t.getX(), t.getY(), 105, 105);
			t.paint(g);
			if (t.getX() > 1600 || t.getY() > 1600
					|| t.getX() < 0 || t.getY() < 0) {
				bullets.remove(t);
			}

		}
		if(teemos.size()<5){
		Teemo t1 = new Teemo("teemo.png", (int) ((Math.random()) * 1600),
				ezreal.getX() + (double) 145 / 2,
				(int) ((Math.random()) * 1600), ezreal.getY() + (double) 145
						/ 2);
		teemos.add(t1);

		double vecX = (double) ezreal.getX() - (t1.getX() + (double) 105 / 2);
		double vecY = (double) ezreal.getY() - (t1.getY() + (double) 105 / 2);

		double d = Math.sqrt((vecX*vecX)
				+ (vecY*vecY));

		double directionX = vecX / d;
		double directionY = vecY / d;

		t1.setVx(directionX / 100);
		t1.setVy(directionY / 100);
		}
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < teemos.size(); j++) {
				Teemo t1 = (Teemo) teemos.get(i);
				Bullet b = (Bullet) bullets.get(i);
				t1 = (Teemo) teemos.get(j);
				if (b.collided(t1.getX(), t1.getY(), t1.getWidth(),
						t1.getHeight())) {
					teemos.remove(t1);
					bullets.remove(b);
				}
			}
		}
	}

	// g.drawRect(ezreal.getX(), ezreal.getY(), 145, 170);
	// g.drawRect(teemo.getX(), teemo.getY(), 105, 105);

	Font font = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);

	public void update() {
		ezreal.move();
		if ((ezreal.getX() + (double) 145 / 2 > targetX - (double) 145 / 2 && ezreal
				.getX() + (double) 145 / 2 < targetX + (double) 145 / 2)
				&& (ezreal.getY() + (double) 170 / 2 < targetY + (double) 170
						/ 2 && ezreal.getY() + (double) 170 / 2 > targetY
						- (double) 170 / 2)) {
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

		// player.addMouseListener(this);
		// bg = new Background("background.png");
		// do not add to frame, call paint on
		// these objects in paint method

		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		ArrayList bullets = ezreal.getBullets();
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = (Bullet) bullets.get(i);
			if (b.isVisible() == true) {
				b.update();
			} else {
				bullets.remove(i);
			}
		}

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

		double coolDownInMillis = 600;
		if ((System.currentTimeMillis() > lastTime + coolDownInMillis)
				&& e.getKeyCode() == 81) {
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
			ezreal.setVx((dirX * speed));
			ezreal.setVy((dirY * speed));

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void reset() {

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