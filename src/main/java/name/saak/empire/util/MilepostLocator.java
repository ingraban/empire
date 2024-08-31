package name.saak.empire.util;

import java.awt.Point;

/**
 * This class maps locations between map location and graphics location
 */
public class MilepostLocator {
	private static final int MARGIN = 25;
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

}
