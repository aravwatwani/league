import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background {
	private int x, y; // position of background
	private Image img; // image

	public Background(String fileName) {
		x = 0;
		y = 0;

		// helper functions - okay to be blackboxed and not explain

		img = getImage("summonersrift.jpg");
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
}
