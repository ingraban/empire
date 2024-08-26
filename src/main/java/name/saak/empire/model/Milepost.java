package name.saak.empire.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import lombok.Data;

@Data
public class Milepost {

	/**
	 * Der Durchmesser eines clear mileposts
	 */
	public static final int MILEPOST_WIDTH = 10;
	/**
	 * Der Abstand zwischen 2 Mileposts in einer Zeile.<br/>
	 * diese Zahl muss durch 4 teilbar sein, damit die Zeilen passen.
	 */
	public static final int DISTANCE = 100;

	/**
	 * Position auf der Karte beginnend bei 0,0
	 */
	private Point location;

	protected Milepost(int x, int y) {
		location = new Point(x, y);
	}

	public void paint(Graphics2D g) {
		Point gl = getGraphicsLocation();

		g.setColor(Color.BLACK);
		g.fillOval(gl.x - 5, gl.y - 5, 10, 10);
	}

	protected Point getGraphicsLocation() {
		return new Point(location.x * DISTANCE / 2 + DISTANCE / 4, location.y * DISTANCE / 4 + DISTANCE / 4);
	}

}