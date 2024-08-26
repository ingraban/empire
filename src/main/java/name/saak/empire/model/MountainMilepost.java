package name.saak.empire.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class MountainMilepost extends Milepost {

	protected MountainMilepost(int x, int y) {
		super(x, y);
	}

	@Override
	public void paint(Graphics2D g) {
		Point gl = getGraphicsLocation();

		g.setColor(Color.BLACK);
		g.fillPolygon(new int[] { gl.x, gl.x + DISTANCE / 10, gl.x - DISTANCE / 10 }, //
				new int[] { gl.y - DISTANCE / 12, gl.y + DISTANCE / 12, gl.y + DISTANCE / 12 }, //
				3);
	}
}
