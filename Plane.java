import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A car that can be moved around.
 */
public class Plane implements MoveableShape {
	private double x;
	private double y;
	private double width;

	/**
	 * Constructs a car item.
	 * 
	 * @param x     the left of the bounding rectangle
	 * @param y     the top of the bounding rectangle
	 * @param width the width of the bounding rectangle
	 */

	public Plane(double x, double y, double width) {
		this.x = x + 25;
		this.y = y;
		this.width = width;
	}
	
	public void up() {
		y = y + -15;
	}
	
	public void down() {
		y = y + 15;
	}

	public void draw(Graphics2D g2) {

		Rectangle2D.Double body = new Rectangle2D.Double(x, y + width / 6, width - 1, width / 6);

		QuadCurve2D.Double headCurve2 = new QuadCurve2D.Double(x + width, y + width / 3 - 1, x + width + width / 3,
				y + width / 3, x + width + width / 3, y + width / 3 - 5);

		QuadCurve2D.Double headCurve = new QuadCurve2D.Double(x + width, y + width / 6, x + width + width / 3,
				y + width / 6, x + width + width / 3, y + width / 3 - 5);

		Line2D.Double tailLine2 = new Line2D.Double(x - 20, y - 10, x - 15, y - 10);

		QuadCurve2D.Double tailCurve = new QuadCurve2D.Double(x, y + width / 3 - 1, x - 18, y + width / 3 - 2, x - 20,
				y - 10);

		Line2D.Double tailLine3 = new Line2D.Double(x - 15, y - 10, x, y + width / 6 - 1);

		Line2D.Double rightWing1 = new Line2D.Double(x + 45, y + width / 3 - 10, x + 25, y + 40);

		Line2D.Double rightWing2 = new Line2D.Double(x + 45, y + width / 3 - 10, x + 65, y + width / 3 - 10);

		Line2D.Double rightWing3 = new Line2D.Double(x + 25, y + 40, x + 35, y + 40);

		Line2D.Double rightWing4 = new Line2D.Double(x + 35, y + 40, x + 65, y + width / 3 - 10);

		g2.draw(body);
		g2.draw(headCurve2);
		g2.draw(headCurve);
		g2.draw(tailCurve);
		g2.draw(tailLine2);
		g2.draw(tailLine3);
		g2.draw(rightWing1);
		g2.draw(rightWing2);
		g2.draw(rightWing3);
		g2.draw(rightWing4);

	}

}