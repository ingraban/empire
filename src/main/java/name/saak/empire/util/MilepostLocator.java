package name.saak.empire.util;

import java.awt.Point;

/**
 * This class maps locations between map location and graphics location
 */
public class MilepostLocator {
	private static final int MARGIN = 100;
	/**
	 * Horizontaler Abstand zwischen 2 Mileposts in einer Zeile.
	 */
	public static final int DISTANCE = 100;
	/**
	 * Vertikaler Abstand zwischen 2 Spalten (die nicht in der gleichen Zeile liegen
	 * können)
	 */
	public static final int COLUMN_DISTANCE = DISTANCE / 2;
	/**
	 * Vertikaler Abstand zwischen 2 Zeilen
	 */
	public static final int ROW_DISTANCE = DISTANCE / 4;
	/**
	 * Radius eines Milepost
	 */
	public static final int MILEPOST_RADIUS = DISTANCE / 20;
	/**
	 * true, if sum of coordinates must be odd to hit possible milepost
	 */
	private static boolean odd;

	private MilepostLocator() {
	}

	/**
	 * calculates the locations for the map paint function
	 * 
	 * @param point coordinates in the map model
	 * @return coordinates for the paint function
	 */
	public static Point getGraphicsLocation(Point mapLocation) {
		return new Point(mapLocation.x * COLUMN_DISTANCE + MARGIN, mapLocation.y * ROW_DISTANCE + MARGIN);
	}

	/**
	 * calculate the map location from graphics location
	 * 
	 * @param p location in JPanel (without zoom)
	 * @return map location
	 */
	public static Point getMapLocation(Point p) {
		if ((p.x - MARGIN + COLUMN_DISTANCE) % (COLUMN_DISTANCE) > COLUMN_DISTANCE / 2) p.x += COLUMN_DISTANCE / 2;
		if ((p.y - MARGIN + ROW_DISTANCE) % (ROW_DISTANCE) > ROW_DISTANCE / 2) p.y += ROW_DISTANCE / 2;

		Point ml = new Point((p.x - MARGIN) / COLUMN_DISTANCE, (p.y - MARGIN) / ROW_DISTANCE);
		if (((ml.x + ml.y) % 2 == 0) && !odd) return ml;
		else if (((ml.x + ml.y) % 2 != 0) && odd) return ml;
		else return null;
	}

	/**
	 * 
	 * @param odd if true, the sum of x and y coordinate for mileposts is odd. If
	 *            false this sum is even for valid milepost coordinates.
	 */
	public static void setOdd(boolean odd) {
		MilepostLocator.odd = odd;
	}

}
