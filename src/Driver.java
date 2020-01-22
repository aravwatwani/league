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
	double speed = 10.0;
	Ezreal ezreal;
	Background background;

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
			b.paint(g);
		}
		g.drawRect(ezreal.getX(), ezreal.getY(), 145, 170);
		
	}

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

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 81) { // shoot
			ezreal.fire();
			System.out.println(targetX + targetY);
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
