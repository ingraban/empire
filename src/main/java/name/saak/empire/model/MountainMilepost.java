package name.saak.empire.model;

import static name.saak.empire.util.MilepostLocator.DISTANCE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import name.saak.empire.util.MilepostLocator;

public class MountainMilepost extends Milepost {

	public MountainMilepost(int x, int y) {
		super(x, y);
	}

	@Override
	public void paint(Graphics2D g, Gameboard board) {
		Point gl = MilepostLocator.getGraphicsLocation(getLocation());

		g.setColor(Color.BLACK);
		g.fillPolygon(new int[] { gl.x, (int) (gl.x + DISTANCE * 0.1), (int) (gl.x - DISTANCE * 0.1) }, //
				new int[] { (int) (gl.y - DISTANCE * 0.08), (int) (gl.y + DISTANCE * 0.08), (int) (gl.y + DISTANCE * 0.08) }, //
				3);
	}
}
