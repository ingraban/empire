package name.saak.empire.model;

import static name.saak.empire.util.MilepostLocator.DISTANCE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import lombok.Data;
import name.saak.empire.util.MilepostLocator;

@Data
public class Milepost {

	/**
	 * Der Durchmesser eines clear mileposts
	 */
	public static final int MILEPOST_WIDTH = 10;
	/**
	 * Position auf der Karte beginnend bei 0,0
	 */
	private Point location;

	public Milepost(int x, int y) {
		location = new Point(x, y);
	}

	/**
	 * paints the milepost image on the graphics context
	 * 
	 * @param g     graphics context which has to be used for painting
	 * @param board
	 */
	public void paint(Graphics2D g, Gameboard board) {
		Point gl = MilepostLocator.getGraphicsLocation(location);

		g.setColor(Color.BLACK);
		g.fillOval((int) (gl.x - DISTANCE * 0.05), (int) (gl.y - DISTANCE * 0.05), (int) (DISTANCE * 0.1), (int) (DISTANCE * 0.1));
	}
}